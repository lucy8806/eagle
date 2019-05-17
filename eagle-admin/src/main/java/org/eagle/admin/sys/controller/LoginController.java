package org.eagle.admin.sys.controller;

import cn.hutool.core.date.DateUtil;
import com.google.code.kaptcha.Constants;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.eagle.admin.sys.entity.SysUser;
import org.eagle.admin.sys.entity.UserConfig;
import org.eagle.admin.sys.service.ResourceService;
import org.eagle.admin.sys.service.RoleService;
import org.eagle.admin.sys.service.UserService;
import org.eagle.admin.sys.shiro.jwt.JwtToken;
import org.eagle.core.consts.CommonConst;
import org.eagle.core.model.ActiveUser;
import org.eagle.core.model.ResponseVo;
import org.eagle.core.properties.EagleProperties;
import org.eagle.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 登录登出
 */
@Api(value = "登录", description = "登录管理api", position = 40, produces = "http")
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private EagleProperties properties;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "后台登录", notes = "后台登录,200成功，500失败",produces="application/json, application/xml", consumes="application/json, application/xml")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "verification", value = "验证码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "rememberme", value = "记住登录", required = false, dataType = "Boolean", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @PostMapping("/login")
    public ResponseVo login(HttpServletRequest request, String username, String password, String verification,
                            @RequestParam(name = "rememberme", defaultValue = "false") Boolean rememberme) throws Exception {
        log.info("[进入登录方法....]");

        SysUser user = userService.selectUserByName(username);
        if(user == null){
            return ResultUtil.error("账号不存在！");
        }

        String dbPwd = user.getPassword();
        dbPwd = UsingAesUtil.decrypt(dbPwd, username);
        if(!dbPwd.equals(password)){
            return ResultUtil.error("密码错误！");
        }

        //生成token
        String token = EagleUtil.encryptToken(JwtUtil.sign(username, user.getPassword()));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getShiro().getJwtTimeOut());
        String expireTimeStr = DateUtils.format(expireTime);
        JwtToken jwtToken = new JwtToken(token, expireTimeStr);

        //token存储到redis
        saveTokenToRedis(request, user, jwtToken);

        //生成前端需要的用户信息
        Map<String, Object> userInfo = generateUserInfo(jwtToken, user);

        //判断验证码
        /*String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(rightCode) && verification.equals(rightCode)) {
            log.info("[验证码通过]");
        } else {
            return ResultUtil.error("验证码错误！");
        }*/
        /*UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberme);
        try {
            Subject subject = SecurityUtils.getSubject();
            //登录验证
            subject.login(token);
        } catch (Exception e) {
            token.clear();
            log.info("[登录内部错误！请联系管理员检查！]-[{}]", DateUtil.date());
            return ResultUtil.error(e.getMessage());
        }*/
        log.info("[登录成功]-[{}]", DateUtil.date());
        return ResultUtil.success("登录成功！", userInfo);
    }

    @GetMapping("/logout/{userId}")
    public void logout(@NotBlank(message = "{required}") @PathVariable String userId){
        String now = DateUtils.format(LocalDateTime.now());
        Set<ActiveUser> activeUsers = redisTemplate.opsForZSet().range(CommonConst.ACTIVE_USERS_ZSET_PREFIX, 0, -1);
        ActiveUser kickoutUser = null;
        for (ActiveUser activeUser : activeUsers){
            if(userId.equals(activeUser.getId())){
                kickoutUser = activeUser;
                break;
            }
        }
        //删除redis存储的用户信息
        redisTemplate.opsForZSet().remove(CommonConst.ACTIVE_USERS_ZSET_PREFIX, kickoutUser);
        //删除redis存储的token
        redisTemplate.delete(CommonConst.TOKEN_CACHE_PREFIX + kickoutUser.getToken() + "." + kickoutUser.getIp());
    }

    @GetMapping("/index/{username}")
    public ResponseVo index(@NotBlank(message = "{required}") @PathVariable String username){
        Map<String, Object> data = new HashMap<>();
        // 获取系统访问记录
       // Long totalVisitCount = loginLogMapper.findTotalVisitCount();
        data.put("totalVisitCount", 20);
        //Long todayVisitCount = loginLogMapper.findTodayVisitCount();
        data.put("todayVisitCount", 10);
       // Long todayIp = loginLogMapper.findTodayIp();
        data.put("todayIp", 5);
        // 获取近期系统访问记录
        //List<Map<String, Object>> lastSevenVisitCount = loginLogMapper.findLastSevenDaysVisitCount(null);
       // data.put("lastSevenVisitCount", lastSevenVisitCount);
       // User param = new User();
       // param.setUsername(username);
       // List<Map<String, Object>> lastSevenUserVisitCount = loginLogMapper.findLastSevenDaysVisitCount(param);
       // data.put("lastSevenUserVisitCount", lastSevenUserVisitCount);
        return ResultUtil.success(data);
    }

    private void saveTokenToRedis(HttpServletRequest request, SysUser user, JwtToken jwtToken) {
        // 构建在线用户
        String ip = EagleUtil.getIpAddr(request);
        ActiveUser activeUser = new ActiveUser();
        activeUser.setId(String.valueOf(user.getId()));
        activeUser.setUsername(user.getUsername());
        activeUser.setIp(ip);
        activeUser.setToken(jwtToken.getToken());
        //activeUser.setLoginAddress(AddressUtil.getCityInfo(DbSearcher.BTREE_ALGORITHM, ip));
        // zset 存储登录用户，score 为过期时间戳
        redisTemplate.opsForZSet().add(CommonConst.ACTIVE_USERS_ZSET_PREFIX, activeUser , Double.valueOf(jwtToken.getExipreAt()));
        //redis 中存储这个加密 token，key = 前缀 + 加密 token + .ip
        redisTemplate.opsForValue().set(CommonConst.TOKEN_CACHE_PREFIX + jwtToken.getToken() + "." + ip, jwtToken.getToken());
        //设置token有效期
        redisTemplate.expire(CommonConst.TOKEN_CACHE_PREFIX + jwtToken.getToken() + "." + ip, properties.getShiro().getJwtTimeOut(), TimeUnit.SECONDS);
    }

    private Map<String, Object> generateUserInfo(JwtToken token, SysUser user) {
        String username = user.getUsername();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("token", token.getToken());
        userInfo.put("exipreTime", token.getExipreAt());

        Set<String> roles = roleService.findRoleByUserId(user.getId());
        userInfo.put("roles", roles);

        Set<String> permissions = resourceService.findPermsByUserId(user.getId());
        userInfo.put("permissions", permissions);

        //UserConfig userConfig = this.userManager.getUserConfig(String.valueOf(user.getUserId()));
        UserConfig userConfig = new UserConfig();
        userInfo.put("config", userConfig);

        user.setPassword("it's a secret");
        userInfo.put("user", user);
        return userInfo;
    }
}
