package org.eagle.admin.sys.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.eagle.admin.sys.entity.SysResource;
import org.eagle.admin.sys.vo.ResourceReqVo;
import org.eagle.core.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: ResourceMapper.java
 * @Description: ResourceMapper
 * @Author lucy
 * @Date 2019年5月8日 下午9:42:02
 * @Version 1.0
 */
@Mapper
@Repository
public interface ResourceMapper extends BaseMapper<SysResource> {
	List<SysResource> listUrlAndPermission();
	Set<String> findPermsByUserId(Integer userId);
	List<SysResource> findResourceByUserId(Integer userId);
	List<SysResource> selectResourceList(ResourceReqVo reqVo);

	/**
	 * 根据Id删除菜单以及所有的子菜单
	 * @param id
	 * @return
	 */
	int deleteResources(String id);
}
