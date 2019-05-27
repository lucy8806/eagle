package org.eagle.admin.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eagle.admin.sys.entity.SysRole;
import org.eagle.admin.sys.service.RoleService;
import org.eagle.core.model.ResponseVo;
import org.eagle.core.utils.Result;
import org.eagle.core.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
