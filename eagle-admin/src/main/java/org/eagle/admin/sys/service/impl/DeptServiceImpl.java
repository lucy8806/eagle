package org.eagle.admin.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eagle.admin.sys.dao.DeptMapper;
import org.eagle.admin.sys.entity.SysDept;
import org.eagle.admin.sys.service.DeptService;
import org.eagle.admin.sys.vo.DeptReqVo;
import org.eagle.core.model.Tree;
import org.eagle.core.mybatis.service.impl.BaseServiceImpl;
import org.eagle.core.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DeptServiceImpl
 * @Description: 部门管理Service
 * @Author lucy
 * @Date 2019/5/17 15:53
 * @Version 1.0
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<SysDept> implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public PageInfo<SysDept> findPageByCond(DeptReqVo vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysDept> list = deptMapper.findPageByCond(vo);
        PageInfo<SysDept> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Map<String, Object> findDepts(DeptReqVo vo) {
        Map<String, Object> result = new HashMap<>();
        List<SysDept> depts = deptMapper.findPageByCond(vo);
        List<Tree<SysDept>> trees = new ArrayList<>();
        buildTrees(trees, depts);
        Tree<SysDept> deptTree = TreeUtil.build(trees);
        result.put("rows", deptTree);
        result.put("total", depts.size());
        return result;
    }

    private void buildTrees(List<Tree<SysDept>> trees, List<SysDept> depts) {
        depts.forEach(dept -> {
            Tree<SysDept> tree = new Tree<>();
            tree.setId(dept.getDeptId().toString());
            tree.setKey(tree.getId());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getDeptName());
            tree.setCreateTime(dept.getCreatetime());
            tree.setModifyTime(dept.getUpdatetime());
            tree.setOrder(dept.getOrderNum());
            tree.setTitle(tree.getText());
            tree.setValue(tree.getId());
            trees.add(tree);
        });
    }
}
