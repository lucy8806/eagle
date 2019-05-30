package org.eagle.admin.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: ResourceReqVo
 * @Description: 资源查询请求参数对象
 * @Author lusai
 * @Date 2019/5/30 10:19
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceReqVo {
    @ApiModelProperty(value = "资源名称")
    private String name;
    @ApiModelProperty(value = "创建开始日期")
    private String createTimeFrom;
    @ApiModelProperty(value = "创建结束日期")
    private String createTimeTo;
}
