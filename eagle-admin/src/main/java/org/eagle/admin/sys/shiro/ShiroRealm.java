package org.eagle.admin.sys.shiro;

import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.eagle.admin.sys.entity.SysUser;
import org.eagle.admin.sys.service.ResourceService;
import org.eagle.admin.sys.service.RoleService;
import org.eagle.admin.sys.service.UserService;
import org.eagle.admin.sys.shiro.jwt.JwtToken;
import org.eagle.core.consts.CommonConst;
import org.eagle.core.enums.SysUserStatusEnum;
import org.eagle.core.utils.EagleUtil;
import org.eagle.core.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @ClassName: ShiroRealm
 * @Description: 权限控制
 * @Author lucy
 * @Date 2019/5/9 17:05
 * @Version 1.0
 */
public class ShiroRealm extends AuthorizingRealm {

    private  static final Logger log = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisSessionDAO redisSessionDAO;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 給当前登录的用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection == null) {
            throw new AuthorizationException("principals should not be null");
        }

        //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> roles = new HashSet<String>();
        Set<String> resources = new HashSet<String>();

        //根据用户id获取角色，资源
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        //String token = principalCollection.toString();
        //String userName = JwtUtil.getUsername(token);
        //SysUser sysUser = userService.selectUserByName(userName);

        roles = roleService.findRoleByUserId(sysUser.getId());
        resources = resourceService.findPermsByUserId(sysUser.getId());

        //将角色，权限添加到SimpleAuthorizationInfo认证对象中
        info.setRoles(roles);
        info.setStringPermissions(resources);
        log.info("[当前登录用户授权完成,用户id]-[{}]", sysUser.getId());
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户账号 此处有坑，需谨慎
        //UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;

        // 这里的token是从 JwtFilter 的 executeLogin 方法传递过来的，已经经过了解密
        String token = (String) authenticationToken.getCredentials();

        // 获取redis中存储的token
        HttpServletRequest request = EagleUtil.getHttpServletRequest();
        String ip = EagleUtil.getIpAddr(request);
        String encryptToken = EagleUtil.encryptToken(token);
        String encryptTokenInRedis = null;
        try {
            encryptTokenInRedis = (String)redisTemplate.opsForValue().get(CommonConst.TOKEN_CACHE_PREFIX + encryptToken + "." + ip);
        } catch (Exception ignore) {
        }

        //校验token
        if(StringUtils.isBlank(encryptTokenInRedis)){
            throw new AuthenticationException("token已过期");
        }
        String username = JwtUtil.getUsername(token);
        if(StringUtils.isBlank(username)){
            throw new AuthenticationException("token校验不通过");
        }
        //根据用户名查询用户信息
        SysUser sysuser = userService.selectUserByName(username);
        if (ObjectUtil.isNull(sysuser)) {
            throw new UnknownAccountException("帐号不存在！");
        }
        if(!JwtUtil.verify(token, username, sysuser.getPassword())){
            throw new AuthenticationException("token校验不通过");
        }
        if (SysUserStatusEnum.DISABLE.getCode().equals(sysuser.getStatus())) {
            throw new LockedAccountException("账号锁定，禁止登录系统！");
        }

        return new SimpleAuthenticationInfo(token, token, "eagle_shiro_realm");
        /*return new SimpleAuthenticationInfo(
                sysuser,
                sysuser.getPassword(),
                ByteSource.Util.bytes(username),
                getName()
        );*/
    }

    /***
     * 清除认证信息
     * @param userIds
     */
    public void removeCachedAuthenticationInfo(List<String> userIds) {
        if (null == userIds || userIds.size() == 0) {
            return;
        }
        List<SimplePrincipalCollection> list = getSpcListByUserIds(userIds);
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm shiroReam = (ShiroRealm) securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection collection : list) {
            shiroReam.clearCachedAuthenticationInfo(collection);
        }
    }

    /**
     * 清除授权信息
     *
     * @param userIds
     */
    public void clearAuthorizationByUserId(List<String> userIds) {
        if (null == userIds || userIds.size() == 0) {
            return;
        }
        List<SimplePrincipalCollection> list = getSpcListByUserIds(userIds);
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm realm = (ShiroRealm) securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection collection : list) {
            realm.clearCachedAuthorizationInfo(collection);
        }
        log.info("[用户权限缓存更新成功]");
    }

    /***
     * 通过ids获取所有spc
     * @param userIds
     * @return
     */
    private List<SimplePrincipalCollection> getSpcListByUserIds(List<String> userIds) {
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session : sessions) {
            //获取session登录信息
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != obj && obj instanceof SimplePrincipalCollection) {
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
                //判断用户，匹配用户id
                obj = spc.getPrimaryPrincipal();
                if (null != obj && obj instanceof SysUser) {
                    SysUser user = (SysUser) obj;
                    if (null != user && userIds.contains(user.getId())) {
                        list.add(spc);
                    }
                }
            }
        }
        return list;
    }

}
