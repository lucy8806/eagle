package org.eagle.admin.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eagle.admin.sys.entity.SysResource;
import org.eagle.admin.sys.vo.ResourceReqVo;
import org.eagle.core.model.router.VueRouter;
import org.eagle.core.mybatis.service.BaseService;

/**
 * @ClassName: ResourceService.java
 * @Description: 资源Service接口
 * @Author lucy
 * @Date 2019年5月8日 下午9:45:02
 * @Version 1.0
 */
public interface ResourceService extends BaseService<SysResource> {

	List<SysResource> listUrlAndPermission();

	Set<String> findPermsByUserId(Integer userId);

	/**
	 * 根据用户Id构建Vue路由
	 * @param userId
	 * @return
	 */
	ArrayList<VueRouter<SysResource>> getUserRouters(Integer userId);

	/**
	 * 获取资源树形结构数据
	 * @param reqVo
	 * @return
	 */
	Map<String, Object> findResourceTree(ResourceReqVo reqVo);

	/**
	 * 根据Id删除菜单以及所有的子菜单
	 * @param ids
	 * @return
	 */
	void deleteResources(String[] ids);
}
