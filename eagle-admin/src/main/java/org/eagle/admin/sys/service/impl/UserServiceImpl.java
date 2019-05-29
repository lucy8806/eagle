package org.eagle.admin.sys.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.hutool.core.util.RandomUtil;
import org.eagle.admin.sys.dao.UserMapper;
import org.eagle.admin.sys.dao.UserRoleMapper;
import org.eagle.admin.sys.entity.SysUser;
import org.eagle.admin.sys.entity.SysUserRole;
import org.eagle.admin.sys.service.UserService;
import org.eagle.admin.sys.vo.UserReqVo;
import org.eagle.core.mybatis.service.impl.BaseServiceImpl;
import org.eagle.core.utils.UsingAesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public PageInfo<SysUser> findPageByCondition(UserReqVo vo) {
		PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
		List<SysUser> sysUsers = userMapper.findPageByCondition(vo);
		if (CollectionUtils.isEmpty(sysUsers)) {
			return null;
		}
		PageInfo<SysUser> list = new PageInfo<>(sysUsers);
		return list;
	}

	@Override
	public List<SysUser> listUsersByRoleId(Integer roleId) {
		return userMapper.listUsersByRoleId(roleId);
	}

	@Override
	public SysUser selectUserByName(String userName) {
		return userMapper.selectUserByName(userName);
	}

	@Override
	public void updateUserLastLoginInfo(SysUser sysUser) {
		userMapper.updateLastLoginTime(sysUser);
	}

	@Override
	public boolean deleteBatchByIds(String[] ids) {
		Arrays.stream(ids).forEach(id ->{
			userMapper.deleteByPrimaryKey(Integer.valueOf(id));
		});
		return true;
	}

	@Override
	public boolean insert(SysUser entity) {
		entity.setUserId(RandomUtil.randomUUID().substring(0, 7));
		try {
			entity.setPassword(UsingAesUtil.encrypt(SysUser.DEFAULT_PASSWORD, entity.getUsername()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		//保存用户数据
		super.insert(entity);

		//保存用户角色关系
		String[] roleIds = entity.getRoleId().split(",");
		Arrays.stream(roleIds).forEach(roleId -> {
			SysUserRole userRole = new SysUserRole();
			userRole.setUserId(String.valueOf(entity.getId()));
			userRole.setRoleId(roleId);
			userRoleMapper.insert(userRole);
		});

		return true;
	}

	@Override
	public boolean updateSelectiveById(SysUser user) {
		//更新用户角色关系, 先删除，后新增
		String[] roleIds = user.getRoleId().split(",");
		SysUserRole userRole = new SysUserRole();
		userRole.setUserId(String.valueOf(user.getId()));
		userRoleMapper.delete(userRole);
		Arrays.stream(roleIds).forEach(roleId -> {
			userRole.setId(null);
			userRole.setRoleId(roleId);
			userRoleMapper.insert(userRole);
		});

		//更新用户信息
		return super.updateSelectiveById(user);
	}
}
