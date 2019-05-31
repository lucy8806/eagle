package org.eagle.admin.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.eagle.admin.sys.entity.SysRoleResource;
import org.eagle.core.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleResourceMapper extends BaseMapper<SysRoleResource> {
}
