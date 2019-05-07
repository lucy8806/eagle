package org.eagle.admin.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.eagle.admin.sys.entity.SysUser;
import org.eagle.admin.sys.vo.UserReqVo;
import org.eagle.core.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<SysUser> {
	List<SysUser> findPageByCondition(UserReqVo vo);
}
