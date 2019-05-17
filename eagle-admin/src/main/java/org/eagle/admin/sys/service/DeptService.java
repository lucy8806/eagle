package org.eagle.admin.sys.service;

import com.github.pagehelper.PageInfo;
import org.eagle.admin.sys.entity.SysDept;
import org.eagle.admin.sys.vo.DeptReqVo;
import org.eagle.core.mybatis.service.BaseService;

import java.util.Map;

public interface DeptService extends BaseService<SysDept> {
    PageInfo<SysDept> findPageByCond(DeptReqVo vo);
    Map<String, Object> findDepts(DeptReqVo vo);
}
