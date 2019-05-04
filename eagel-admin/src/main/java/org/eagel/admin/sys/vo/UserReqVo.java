package org.eagel.admin.sys.vo;

import org.eagel.admin.sys.entity.SysUser;
import org.eagle.core.model.PageInfoVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserReqVo extends PageInfoVo {
	private SysUser user;
}
