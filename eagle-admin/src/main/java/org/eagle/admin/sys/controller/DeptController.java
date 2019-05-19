package org.eagle.admin.sys.controller;

import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
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
@Api(value = "部门", description = "部门管理api")
@RestController
@RequestMapping("/sys/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Map<String, Object> deptList(DeptReqVo vo) {
        return deptService.findDepts(vo);
    }

    @PostMapping("/list")
    public PageResultVo list(DeptReqVo vo){
        PageInfo<SysDept> pageInfo = deptService.findPageByCond(vo);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList());
    }

    @PostMapping("/add")
    public ResponseVo add(SysDept dept){
        dept.setDeptId(RandomUtil.randomUUID().substring(0, 7).toString());
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

    @DeleteMapping("/del/{deptIds}")
    public ResponseVo delete(@PathVariable String deptIds){
        String[] ids = deptIds.split(",");
        deptService.deleteDepts(ids);
        return  ResultUtil.success("删除部门成功！");
    }

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
