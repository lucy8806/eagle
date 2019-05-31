package org.eagle.admin.sys.controller;

import cn.hutool.core.util.RandomUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.eagle.admin.sys.constant.SysConstant;
import org.eagle.admin.sys.entity.SysDept;
import org.eagle.admin.sys.entity.SysResource;
import org.eagle.admin.sys.service.ResourceService;
import org.eagle.admin.sys.vo.ResourceReqVo;
import org.eagle.core.model.ResponseVo;
import org.eagle.core.model.router.VueRouter;
import org.eagle.core.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName: MenuController
 * @Description: TODO
 * @Author lucy
 * @Date 2019/5/15 17:05
 * @Version 1.0
 */
@Api(value = "菜单管理", description = "菜单管理api", produces = "http")
@RestController
@Slf4j
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "获取菜单路由数据", notes = "获取菜单路由数据")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String")
    @GetMapping("/{userId}")
    public ArrayList<VueRouter<SysResource>> getUserRouters(@NotBlank(message = "{required}") @PathVariable Integer userId) {
        return resourceService.getUserRouters(userId);
    }

    @ApiOperation(value = "资源树形列表", notes = "获取资源树形结构数据", response = SysDept.class)
    @GetMapping("list")
    public Map<String, Object> list(ResourceReqVo reqVo){
        return resourceService.findResourceTree(reqVo);
    }

    @ApiOperation(value = "新增资源", notes = "新增资源")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @PostMapping("/add")
    @RequiresPermissions("resource:add")
    public ResponseVo add(SysResource resource){
        resource.setResourceId(RandomUtil.randomUUID().substring(0, 7));
        resource.setStatus(SysConstant.RESOURCE_STATUS_VALID);
        if (resource.getParentId() == null) {
            resource.setParentId(0);
        }
        if (SysConstant.RESOURCE_TYPE_BUTTON.equals(resource.getType())) {
            resource.setUrl(null);
            resource.setIcon(null);
            resource.setComponent(null);
        }

        if(resourceService.insert(resource)){
            return ResultUtil.success("新增资源成功！");
        }else {
            return  ResultUtil.error("新增资源失败！");
        }
    }

    @ApiOperation(value = "修改资源", notes = "修改资源")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @RequiresPermissions("resource:edit")
    @PutMapping("/update")
    public ResponseVo update(SysResource resource){
        if(resourceService.updateSelectiveById(resource)){
            return ResultUtil.success("修改资源成功！");
        }else {
            return ResultUtil.error("修改资源失败！");
        }
    }

    @ApiOperation(value = "删除资源", notes = "删除资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resourceIds", value = "资源id，多个资源id之间以逗号分隔", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @RequiresPermissions("resource:delete")
    @DeleteMapping("/del/{resourceIds}")
    public ResponseVo delete(@PathVariable String resourceIds){
        String[] ids = resourceIds.split(",");
        resourceService.deleteResources(ids);
        return  ResultUtil.success("删除部门成功！");
    }

}
