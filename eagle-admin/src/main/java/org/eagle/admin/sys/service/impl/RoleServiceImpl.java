package org.eagle.admin.sys.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.eagle.admin.sys.constant.SysConstant;
import org.eagle.admin.sys.dao.RoleMapper;
import org.eagle.admin.sys.dao.RoleResourceMapper;
import org.eagle.admin.sys.entity.SysRole;
import org.eagle.admin.sys.entity.SysRoleResource;
import org.eagle.admin.sys.service.RoleService;
import org.eagle.admin.sys.vo.RoleReqVo;
import org.eagle.core.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: RoleServiceImpl.java
 * @Description: 角色Service实现类
 * @Author lucy
 * @Date 2019年5月8日 下午10:26:58
 * @Version 1.0
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Override
	public Set<String> findRoleByUserId(Integer userId) {
		List<SysRole> roleList = roleMapper.findRoleByUserId(userId);
		return roleList.stream().map(SysRole::getRole).collect(Collectors.toSet());
	}

	@Override
	public PageInfo<SysRole> findPageByCondition(RoleReqVo reqVo) {
		PageHelper.startPage(reqVo.getPageNum(), reqVo.getPageSize());
		List<SysRole> roleList = roleMapper.findPageByCondition(reqVo);
		PageInfo<SysRole> list = new PageInfo<>(roleList);
		return list;
	}

	@Override
	public List<String> getRoleResourceByRoleId(String roleId) {
		SysRoleResource roleResource = new SysRoleResource();
		roleResource.setRoleId(roleId);
		List<SysRoleResource> roleResourceList = roleResourceMapper.select(roleResource);
		List<String> resourceIds = roleResourceList.stream().map(sysRoleResource -> sysRoleResource.getResourceId()).collect(Collectors.toList());
		return resourceIds;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean insert(SysRole role) {
		//保存角色信息
		role.setRoleId(RandomUtil.randomUUID().substring(0, 7));
		role.setStatus(SysConstant.ROLE_STATUS_VALID);
		super.insert(role);

		//保存角色和资源关联数据
		String resourceIdsStr = role.getResourceIds();
		String[] resourceIds = resourceIdsStr.split(",");
		Arrays.stream(resourceIds).forEach(resourceId -> {
			SysRoleResource roleResource = new SysRoleResource();
			roleResource.setRoleId(role.getId().toString());
			roleResource.setResourceId(resourceId);
			roleResourceMapper.insert(roleResource);
		});

		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateSelectiveById(SysRole role) {
		//更新角色信息
		super.updateSelectiveById(role);

		//更新角色资源关联数据，先删除后新增
		SysRoleResource roleResource = new SysRoleResource();
		roleResource.setRoleId(role.getId().toString());
		roleResourceMapper.delete(roleResource);
		String resourceIdsStr = role.getResourceIds();
		String[] resourceIds = resourceIdsStr.split(",");
		Arrays.stream(resourceIds).forEach(resourceId -> {
			roleResource.setId(null);
			roleResource.setRoleId(role.getId().toString());
			roleResource.setResourceId(resourceId);
			roleResourceMapper.insert(roleResource);
		});

		return true;
	}
}
