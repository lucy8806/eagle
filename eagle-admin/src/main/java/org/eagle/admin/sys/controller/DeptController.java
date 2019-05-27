package org.eagle.admin.sys.controller;

import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.eagle.admin.sys.entity.SysDept;
import org.eagle.admin.sys.service.DeptService;
import org.eagle.admin.sys.vo.DeptReqVo;
import org.eagle.core.model.PageResultVo;
import org.eagle.core.model.ResponseVo;
import org.eagle.core.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName: DeptController
 * @Description: 部门管理控制器
 * @Author lucy
 * @Date 2019/5/17 15:55
 * @Version 1.0
 */
@Api(value = "部门管理", description = "部门管理api")
@RestController
@RequestMapping("/sys/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation(value = "部门树形列表", notes = "获取部门树形结构数据", response = SysDept.class)
    @GetMapping
    public Map<String, Object> deptList(DeptReqVo vo) {
        return deptService.findDepts(vo);
    }

    @ApiOperation(value = "分页查询部门列表", notes = "分页查询部门列表", response = SysDept.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "数据条数", required = true, dataType = "int", paramType = "query")
    })
    @PostMapping("/list")
    public PageResultVo list(DeptReqVo vo){
        PageInfo<SysDept> pageInfo = deptService.findPageByCond(vo);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value = "新增部门", notes = "新增部门")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @PostMapping("/add")
    public ResponseVo add(SysDept dept){
        dept.setDeptId(RandomUtil.randomUUID().substring(0, 7));
        if(dept.getParentId() == null){
            dept.setParentId(0);
        }
        dept.setCreatetime(new Date());
        dept.setUpdatetime(new Date());
        if(deptService.insert(dept)){
            return ResultUtil.success("新增部门成功！");
        }else {
            return  ResultUtil.error("新增部门失败！");
        }
    }

    @ApiOperation(value = "删除部门", notes = "删除部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptIds", value = "部门id，多个部门id之间以逗号分隔", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @DeleteMapping("/del/{deptIds}")
    public ResponseVo delete(@PathVariable String deptIds){
        String[] ids = deptIds.split(",");
        deptService.deleteDepts(ids);
        return  ResultUtil.success("删除部门成功！");
    }

    @ApiOperation(value = "修改部门", notes = "修改部门")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @PutMapping("/update")
    public ResponseVo update(SysDept dept){
        dept.setUpdatetime(new Date());
        if(deptService.updateSelectiveById(dept)){
            return ResultUtil.success("修改部门成功！");
        }else {
            return ResultUtil.error("修改部门失败！");
        }
    }
}
