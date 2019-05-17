package org.eagle.admin.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eagle.admin.sys.dao.ResourceMapper;
import org.eagle.admin.sys.entity.SysResource;
import org.eagle.admin.sys.service.ResourceService;
import org.eagle.core.model.router.RouterMeta;
import org.eagle.core.model.router.VueRouter;
import org.eagle.core.mybatis.service.impl.BaseServiceImpl;
import org.eagle.core.utils.TreeUtil;
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

	@Override
	public Set<String> findPermsByUserId(Integer userId) {
		return resourceMapper.findPermsByUserId(userId);
	}

	@Override
	public ArrayList<VueRouter<SysResource>> getUserRouters(Integer userId) {
		List<VueRouter<SysResource>> routes = new ArrayList<>();
		List<SysResource> menus = this.resourceMapper.findResourceByUserId(userId);
		menus.forEach(menu -> {
			VueRouter<SysResource> route = new VueRouter<>();
			route.setId(menu.getResourceId().toString());
			route.setParentId(menu.getParentId().toString());
			route.setIcon(menu.getIcon());
			route.setPath(menu.getUrl());
			route.setComponent(menu.getComponent());
			route.setName(menu.getName());
			route.setMeta(new RouterMeta(true, null));
			routes.add(route);
		});
		return TreeUtil.buildVueRouter(routes);
	}

}
