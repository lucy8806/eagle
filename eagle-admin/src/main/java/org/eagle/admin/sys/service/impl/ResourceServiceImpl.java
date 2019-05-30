package org.eagle.admin.sys.service.impl;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.eagle.admin.sys.dao.ResourceMapper;
import org.eagle.admin.sys.entity.SysResource;
import org.eagle.admin.sys.service.ResourceService;
import org.eagle.admin.sys.vo.ResourceReqVo;
import org.eagle.core.model.Tree;
import org.eagle.core.model.router.RouterMeta;
import org.eagle.core.model.router.VueRouter;
import org.eagle.core.mybatis.service.impl.BaseServiceImpl;
import org.eagle.core.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		List<SysResource> menus = resourceMapper.findResourceByUserId(userId);
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

	@Override
	public Map<String, Object> findResourceTree(ResourceReqVo reqVo) {
		Map<String, Object> result = new HashMap<>();
		//组建树形结构数据
		List<SysResource> resourceList = resourceMapper.selectResourceList(reqVo);
		List<Tree<SysResource>> trees = new ArrayList<>();
		List<String> ids = new ArrayList<>();
		buildTrees(trees, resourceList, ids);
		//设置返回数据
		result.put("ids", ids);
		Tree<SysResource> resourceTree = TreeUtil.build(trees);
		result.put("rows", resourceTree);
		result.put("total", resourceList.size());
		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteResources(String[] ids) {
		Arrays.stream(ids).forEach(id -> resourceMapper.deleteResources(id));
	}

	private void buildTrees(List<Tree<SysResource>> trees, List<SysResource> resources, List<String> ids) {
		resources.forEach(resource -> {
			ids.add(resource.getId().toString());
			Tree<SysResource> tree = new Tree<>();
			tree.setId(resource.getId().toString());
			tree.setKey(tree.getId());
			tree.setParentId(resource.getParentId().toString());
			tree.setText(resource.getName());
			tree.setTitle(tree.getText());
			tree.setIcon(resource.getIcon());
			tree.setComponent(resource.getComponent());
			tree.setCreateTime(resource.getCreatetime());
			tree.setModifyTime(resource.getUpdatetime());
			tree.setPath(resource.getUrl());
			tree.setOrder(resource.getPriority());
			tree.setPermission(resource.getPermission());
			tree.setType(resource.getType().toString());
			trees.add(tree);
		});
	}

}
