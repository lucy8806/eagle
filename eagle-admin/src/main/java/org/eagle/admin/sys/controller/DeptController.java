package org.eagle.admin.sys.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.eagle.admin.sys.entity.SysDept;
import org.eagle.admin.sys.service.DeptService;
import org.eagle.admin.sys.vo.DeptReqVo;
import org.eagle.core.model.PageResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
