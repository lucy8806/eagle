package org.eagle.admin.sys.service;

import org.eagle.admin.sys.entity.SysUser;
import org.eagle.admin.sys.vo.UserReqVo;
import org.eagle.core.mybatis.service.BaseService;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService extends BaseService<SysUser> {
	/**
	 * 分页查询用户
	 * @param vo
	 * @return
	 */
	PageInfo<SysUser> findPageByCondition(UserReqVo vo);

	/***
	 * 根据角色id查询用户列表
	 * @return
	 */
	List<SysUser> listUsersByRoleId(Integer roleId);

	/**
	 * 根据用户姓名查询用户
	 * @param userName
	 * @return
	 */
	SysUser selectUserByName(String userName);

	void updateUserLastLoginInfo(SysUser sysUser);

	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 */
	boolean deleteBatchByIds(String[] ids);
}
