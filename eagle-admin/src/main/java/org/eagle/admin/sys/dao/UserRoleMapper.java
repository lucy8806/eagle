package org.eagle.admin.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.eagle.admin.sys.entity.SysUserRole;
import org.eagle.core.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleMapper extends BaseMapper<SysUserRole> {
}
