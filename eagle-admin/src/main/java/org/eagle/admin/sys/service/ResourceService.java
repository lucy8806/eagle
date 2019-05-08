package org.eagle.admin.sys.service;

import java.util.List;

import org.eagle.admin.sys.entity.SysResource;
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
}
