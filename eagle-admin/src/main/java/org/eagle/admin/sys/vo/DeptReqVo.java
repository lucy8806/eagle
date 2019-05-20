package org.eagle.admin.sys.vo;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "部门名称")
    private String deptName;
    @ApiModelProperty(value = "创建开始日期")
    private String createTimeFrom;
    @ApiModelProperty(value = "创建结束日期")
    private String createTimeTo;
}
