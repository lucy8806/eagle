package org.eagle.admin.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.eagle.core.model.BaseEntity;

/**
 * @ClassName: SysDept
 * @Description: 部门实体
 * @Author lucy
 * @Date 2019/5/17 15:44
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "部门数据对象")
public class SysDept extends BaseEntity {
    private static final long serialVersionUID = 5940904302764973454L;
    /** 扩展部门id */
    @ApiModelProperty(value = "扩展部门id")
    private String deptId;
    /** 上级部门id */
    @ApiModelProperty(value = "上级部门id")
    private Integer parentId;
    /** 部门名称 */
    @ApiModelProperty(value = "部门名称")
    private String deptName;
    /** 排序 */
    @ApiModelProperty(value = "排序")
    private Integer orderNum;
}
