package org.eagle.admin.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.eagle.core.model.PageInfoVo;

/**
 * @ClassName: RoleReqVo
 * @Description: 角色查询参数对象
 * @Author lusai
 * @Date 2019/5/30 17:58
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleReqVo extends PageInfoVo {
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "创建开始日期")
    private String createTimeFrom;
    @ApiModelProperty(value = "创建结束日期")
    private String createTimeTo;
}
