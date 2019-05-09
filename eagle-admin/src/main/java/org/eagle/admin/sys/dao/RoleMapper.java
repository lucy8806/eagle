package org.eagle.admin.sys.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.eagle.admin.sys.entity.SysRole;
import org.eagle.core.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<SysRole> {
	/**
     * 根据用户id查询角色集合
     *
     * @param userId 用户id
     * @return set
     */
    Set<String> findRoleByUserId(Integer userId);
}