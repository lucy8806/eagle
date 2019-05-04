package org.eagel.admin.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.eagel.admin.sys.entity.SysUser;
import org.eagel.admin.sys.service.UserService;
import org.eagel.admin.sys.vo.UserReqVo;
import org.eagle.core.model.PageResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户", description = "用户管理api", position = 30, produces = "http")
@RestController
@RequestMapping("/admin/sys/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 查询用户
	 * 
	 * @param vo
	 * @return
	 */
	@ApiOperation(value = "用户列表", notes = "用户列表，传入参数只需要pageNum和pageSize", produces = "application/json, application/xml", consumes = "application/json, application/xml", response = SysUser.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "数据条数", required = true, dataType = "String", paramType = "query") })
	@RequiresPermissions("user:list")
	@PostMapping("/list")
	public PageResultVo list(UserReqVo vo) {
		PageInfo<SysUser> pageInfo = userService.findPageByCondition(vo);
		return new PageResultVo(pageInfo.getTotal(), pageInfo.getList());
	}
}
