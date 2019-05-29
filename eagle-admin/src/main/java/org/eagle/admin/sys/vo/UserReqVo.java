package org.eagle.admin.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import org.eagle.admin.sys.entity.SysUser;
import org.eagle.core.model.PageInfoVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserReqVo extends PageInfoVo {
	@ApiModelProperty(value = "用户名", name = "username", required = true)
	private String username;
}
