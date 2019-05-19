package org.eagle.admin.sys.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.eagle.admin.sys.entity.SysDept;
import org.eagle.core.model.PageInfoVo;

/**
 * @ClassName: DeptReqVo
 * @Description: 部门查询请求数据对象
 * @Author lucy
 * @Date 2019/5/17 15:57
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptReqVo extends PageInfoVo {
    private String deptName;
    private String createTimeFrom;
    private String createTimeTo;
}
