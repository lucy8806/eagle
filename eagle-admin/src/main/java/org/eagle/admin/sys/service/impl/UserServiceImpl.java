package org.eagle.admin.sys.service.impl;

import java.util.List;

import org.eagle.admin.sys.dao.UserMapper;
import org.eagle.admin.sys.entity.SysUser;
import org.eagle.admin.sys.service.UserService;
import org.eagle.admin.sys.vo.UserReqVo;
import org.eagle.core.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements UserService {

	@Autowired
	private UserMapper userMapper;

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
}
