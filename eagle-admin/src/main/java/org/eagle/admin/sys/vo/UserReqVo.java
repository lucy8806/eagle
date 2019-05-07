package org.eagle.admin.sys.vo;

import org.eagle.admin.sys.entity.SysUser;
import org.eagle.core.model.PageInfoVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserReqVo extends PageInfoVo {
	private SysUser user;
}
