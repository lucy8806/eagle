package org.eagel.admin.sys.service;

import org.eagel.admin.sys.entity.SysUser;
import org.eagel.admin.sys.vo.UserReqVo;
import org.eagle.core.mybatis.service.BaseService;

import com.github.pagehelper.PageInfo;

public interface UserService extends BaseService<SysUser> {
	PageInfo<SysUser> findPageByCondition(UserReqVo vo);
}
