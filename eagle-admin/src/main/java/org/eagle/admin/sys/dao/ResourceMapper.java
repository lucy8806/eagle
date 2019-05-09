package org.eagle.admin.sys.dao;

import java.util.List;
import java.util.Set;

import org.eagle.admin.sys.entity.SysResource;
import org.eagle.core.mybatis.mapper.BaseMapper;

/**
 * @ClassName: ResourceMapper.java
 * @Description: ResourceMapper
 * @Author lucy
 * @Date 2019年5月8日 下午9:42:02
 * @Version 1.0
 */
public interface ResourceMapper extends BaseMapper<SysResource> {
	List<SysResource> listUrlAndPermission();
	Set<String> findPermsByUserId(Integer userId);
}
