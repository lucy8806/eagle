package org.eagle.admin.sys.service.impl;

import java.util.Set;

import org.eagle.admin.sys.entity.SysRole;
import org.eagle.admin.sys.service.RoleService;
import org.eagle.core.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: RoleServiceImpl.java
 * @Description: 角色Service实现类
 * @Author lucy
 * @Date 2019年5月8日 下午10:26:58
 * @Version 1.0
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole> implements RoleService {

	@Autowired
	private RoleService roleService;

	@Override
	public Set<String> findRoleByUserId(Integer userId) {
		return roleService.findRoleByUserId(userId);
	}

}