package org.eagle.admin.sys.entity;

import java.util.Date;

import org.eagle.core.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser extends BaseEntity {

	private static final long serialVersionUID = -3500255936724139952L;

	private String userId;
	private String username;
	private String password;
	private String salt;
	private String email;
	private String phone;
	private int sex;
	private int age;
	private int status;
	private Date createtime;
	private Date updatetime;
	private Date lastLoginTime;
}
