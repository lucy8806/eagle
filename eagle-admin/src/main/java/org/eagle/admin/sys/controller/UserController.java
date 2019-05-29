package org.eagle.admin.sys.controller;

import cn.hutool.core.util.RandomUtil;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.eagle.admin.sys.entity.SysUser;
import org.eagle.admin.sys.service.UserService;
import org.eagle.admin.sys.vo.UserReqVo;
import org.eagle.core.model.PageResultVo;
import org.eagle.core.model.ResponseVo;
import org.eagle.core.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;

import java.util.Date;

@Api(value = "用户", description = "用户管理api", position = 30, produces = "http")
@RestController
@RequestMapping("/sys/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 查询用户
	 *
	 * @param vo
	 * @return
	 */
	@ApiOperation(value = "用户列表", notes = "用户列表，传入参数只需要pageNum和pageSize", response = SysUser.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "数据条数", required = true, dataType = "String", paramType = "query") })
	//@RequiresPermissions("user:list")
	@GetMapping("/list")
	public PageResultVo list(UserReqVo vo) {
		PageInfo<SysUser> pageInfo = userService.findPageByCondition(vo);
		return new PageResultVo(pageInfo.getTotal(), pageInfo.getList());
	}

	@ApiOperation(value = "新增用户", notes = "新增用户")
	@ApiResponses({
			@ApiResponse(code = 200, message = "操作成功"),
			@ApiResponse(code = 500, message = "操作失败，返回错误原因"),
	})
	@PostMapping("/add")
	public ResponseVo add(SysUser user){
		if (userService.insert(user)){
			return ResultUtil.success("新增用户成功！");
		}else {
			return ResultUtil.error("新增用户失败！");
		}
	}

	@ApiOperation(value = "校验用户名是否存在", notes = "校验用户名是否存在")
	@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query")
	@GetMapping("/check/{userName}")
	public ResponseVo checkUserName(@PathVariable String userName){
		SysUser user = userService.selectUserByName(userName);
		if (user == null)
			return ResultUtil.success(true);
		else
			return ResultUtil.success(false);
	}

	@ApiOperation(value = "修改用户", notes = "修改用户")
	@ApiResponses({
			@ApiResponse(code = 200, message = "操作成功"),
			@ApiResponse(code = 500, message = "操作失败，返回错误原因"),
	})
	@PutMapping("/update")
	public ResponseVo update(SysUser user){
		if(userService.updateSelectiveById(user))
			return ResultUtil.success("修改用户成功！");
		else
			return  ResultUtil.error("修改用户失败！");
	}

	@ApiOperation(value = "删除用户", notes = "删除用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userIds", value = "用户ID数组", required = true, dataType = "String[]", paramType = "query")
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "操作成功"),
			@ApiResponse(code = 500, message = "操作失败，返回错误原因"),
	})
	@DeleteMapping("/delete/{userIds}")
	public ResponseVo delete(@PathVariable String[] userIds){
		if(userService.deleteBatchByIds(userIds))
			return ResultUtil.success("删除用户成功！");
		else
			return ResultUtil.success("删除用户失败！");
	}
}
