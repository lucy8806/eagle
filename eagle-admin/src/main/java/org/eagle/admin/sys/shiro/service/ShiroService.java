package org.eagle.admin.sys.shiro.service;

import java.util.Map;

import org.eagle.admin.sys.entity.SysUser;

/**
 * @ClassName: ShiroService.java
 * @Description: Shiro服务接口类
 * @Author lucy
 * @Date 2019年5月8日 下午9:03:43
 * @Version 1.0
 */
public interface ShiroService {
	/***
	 * 初始化权限
	 * 
	 * @return
	 */
	Map<String, String> loadFilterChainDefinitions();

	/***
	 * 重新加载权限
	 */
	void updatePermission();

	/***
	 * 重新加载用户权限
	 * 
	 * @param user
	 */
	void reloadAuthorizingByUserId(SysUser user);

	/***
	 * 重新加载所有拥有roleId角色的用户权限
	 * 
	 * @param roleId
	 */
	void reloadAuthorizingByRoleId(Integer roleId);
}
