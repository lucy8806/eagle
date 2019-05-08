package org.eagle.admin.sys.service.impl;

import java.util.List;

import org.eagle.admin.sys.dao.ResourceMapper;
import org.eagle.admin.sys.entity.SysResource;
import org.eagle.admin.sys.service.ResourceService;
import org.eagle.core.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ResourceServiceImpl.java
 * @Description: 资源Service实现类
 * @Author lucy
 * @Date 2019年5月8日 下午9:48:38
 * @Version 1.0
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<SysResource> implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List<SysResource> listUrlAndPermission() {
		return resourceMapper.listUrlAndPermission();
	}

}
