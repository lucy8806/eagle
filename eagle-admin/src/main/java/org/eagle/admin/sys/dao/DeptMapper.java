package org.eagle.admin.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.eagle.admin.sys.entity.SysDept;
import org.eagle.admin.sys.vo.DeptReqVo;
import org.eagle.core.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: DeptMapper
 * @Description: 部门DAO
 * @Author lucy
 * @Date 2019/5/17 15:50
 * @Version 1.0
 */
@Mapper
@Repository
public interface DeptMapper extends BaseMapper<SysDept> {
     List<SysDept> findPageByCond(DeptReqVo vo);
}
