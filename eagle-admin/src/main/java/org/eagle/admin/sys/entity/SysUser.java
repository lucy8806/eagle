package org.eagle.admin.sys.entity;

import java.util.Date;

import javax.persistence.Column;

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
	@ApiModelProperty(value = "性别：1男2女3未知", name = "sex")
	private Integer sex;
	/**
	 * 年龄
	 **/
	@ApiModelProperty(value = "年龄", name = "age")
	private Integer age;
	/**
	 * 用户状态：1有效2删除
	 **/
	@ApiModelProperty(value = "用户状态：1有效2删除", name = "status", required = true)
	private Integer status;
	/**
	 * 最后登陆时间
	 **/
	@ApiModelProperty(value = "最后登陆时间", name = "lastLoginTime")
	private Date lastLoginTime;
}
