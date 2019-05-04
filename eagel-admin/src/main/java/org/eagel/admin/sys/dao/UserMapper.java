package org.eagel.admin.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.eagel.admin.sys.entity.SysUser;
import org.eagel.admin.sys.vo.UserReqVo;
import org.eagle.core.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<SysUser> {
	List<SysUser> findPageByCondition(UserReqVo vo);
}
