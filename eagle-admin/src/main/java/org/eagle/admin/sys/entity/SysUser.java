package org.eagle.admin.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.eagle.core.model.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "用户实体类")
public class SysUser extends BaseEntity {

	private static final long serialVersionUID = -3500255936724139952L;

	/** 默认密码 */
	public static final String DEFAULT_PASSWORD = "123456";

	/**
	 * 扩展的id
	 **/
	@ApiModelProperty(value = "扩展的id", name = "userId", required = true)
	@Column(name = "userId")
	private String userId;
	/**
	 * 用户名
	 **/
	@ApiModelProperty(value = "用户名", name = "username", required = true)
	private String username;
	/**
	 * 密码
	 **/
	@ApiModelProperty(value = "密码", name = "password", required = true)
	private String password;
	/**
	 * 盐
	 **/
	@ApiModelProperty(value = "盐", name = "salt")
	private String salt;

	@ApiModelProperty(value = "部门id", name = "deptId")
	private Integer deptId;

	@ApiModelProperty(value = "部门名称", name = "deptName")
	@Transient
	private String deptName;

	/**
	 * 邮箱
	 **/
	@ApiModelProperty(value = "邮箱", name = "email")
	private String email;
	/**
	 * 联系方式
	 **/
	@ApiModelProperty(value = "联系方式", name = "phone")
	private String phone;
	/**
	 * 性别：1男2女3未知
	 **/
	@ApiModelProperty(value = "性别：0男1女2保密", name = "sex")
	private String sex;
	/**
	 * 年龄
	 **/
	@ApiModelProperty(value = "年龄", name = "age")
	private Integer age;
	/**
	 * 用户状态：1有效2删除
	 **/
	@ApiModelProperty(value = "用户状态：0锁定1有效", name = "status", required = true)
	private String status;
	/**
	 * 最后登陆时间
	 **/
	@ApiModelProperty(value = "最后登陆时间", name = "lastLoginTime")
	private Date lastLoginTime;

	@Transient
	private String roleId;
	@Transient
	private String roleName;
}
