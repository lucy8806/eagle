package org.eagle.admin.sys.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.eagle.admin.sys.entity.SysRole;
import org.eagle.admin.sys.service.RoleService;
import org.eagle.admin.sys.vo.RoleReqVo;
import org.eagle.core.model.PageResultVo;
import org.eagle.core.model.ResponseVo;
import org.eagle.core.utils.Result;
import org.eagle.core.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: RoleController
 * @Description: 角色管理
 * @Author lucy
 * @Date 2019/5/21 10:35
 * @Version 1.0
 */
@Api(value = "角色管理", description = "角色管理api", position = 30, produces = "http")
@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "查询角色列表")
    @GetMapping("/list")
    public ResponseVo list(SysRole role){
        List<SysRole> list = roleService.select(role);
        return ResultUtil.success(list);
    }

    @ApiOperation(value = "分页查询角色", notes = "分页查询角色", response = SysRole.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "数据条数", required = true, dataType = "String", paramType = "query") })
    //@RequiresPermissions("user:list")
    @GetMapping("/listPage")
    public PageResultVo listPage(RoleReqVo reqVo) {
        PageInfo<SysRole> pageInfo = roleService.findPageByCondition(reqVo);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @RequiresPermissions("role:add")
    @PostMapping("/add")
    public ResponseVo add(SysRole role){
        if (roleService.insert(role)){
            return ResultUtil.success("新增角色成功！");
        }else {
            return ResultUtil.error("新增角色失败！");
        }
    }

    @ApiOperation(value = "修改角色", notes = "修改角色")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @RequiresPermissions("role:edit")
    @PutMapping("/update")
    public ResponseVo update(SysRole role){
        if (roleService.updateSelectiveById(role)){
            return ResultUtil.success("修改角色成功！");
        }else {
            return ResultUtil.error("修改角色失败！");
        }
    }

    @ApiOperation(value = "校验角色名称是否存在", notes = "校验角色名称是否存在")
    @ApiImplicitParam(name = "roleName", value = "角色名称", required = true, dataType = "String", paramType = "query")
    @GetMapping("/check/{roleName}")
    public ResponseVo checkRoleName(@PathVariable String roleName){
        SysRole role = new SysRole();
        role.setRole(roleName);
        SysRole sysRole = roleService.selectOne(role);
        if (sysRole == null)
            return ResultUtil.success(true);
        else
            return ResultUtil.success(false);
    }

    @ApiOperation(value = "根据角色ID获取资源ID列表", notes = "根据角色ID获取资源ID列表")
    @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "String", paramType = "query")
    @GetMapping("/resource/{roleId}")
    public ResponseVo getResourceIds(@PathVariable String roleId){
        List<String> resourceIds = roleService.getRoleResourceByRoleId(roleId);
        return ResultUtil.success(resourceIds);
    }
}
