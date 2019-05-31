package org.eagle.admin.sys.service;

import java.util.List;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import org.eagle.admin.sys.entity.SysRole;
import org.eagle.admin.sys.vo.RoleReqVo;
import org.eagle.core.mybatis.service.BaseService;

public interface RoleService extends BaseService<SysRole> {
	/**
     * 根据用户id查询角色集合
     *
     * @param userId 用户id
     * @return set
     */
    Set<String> findRoleByUserId(Integer userId);

    /**
     * 分页查询角色
     * @param reqVo
     * @return
     */
    PageInfo<SysRole> findPageByCondition(RoleReqVo reqVo);

    /**
     * 根据角色ID获取资源ID列表
     * @param roleId
     * @return
     */
    List<String> getRoleResourceByRoleId(String roleId);
}
