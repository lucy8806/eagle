package org.eagle.admin.sys.service;

import java.util.Set;

import org.eagle.admin.sys.entity.SysRole;
import org.eagle.core.mybatis.service.BaseService;

public interface RoleService extends BaseService<SysRole> {
	/**
     * 根据用户id查询角色集合
     *
     * @param userId 用户id
     * @return set
     */
    Set<String> findRoleByUserId(Integer userId);
}
