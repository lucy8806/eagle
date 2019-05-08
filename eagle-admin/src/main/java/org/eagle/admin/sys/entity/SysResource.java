package org.eagle.admin.sys.entity;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.eagle.core.model.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: SysResource.java
 * @Description: 资源实体类
 * @Author lucy
 * @Date 2019年5月8日 下午9:27:03
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "资源数据对象")
public class SysResource extends BaseEntity {

	private static final long serialVersionUID = 3298532572726381213L;
	/**
	 * 扩展的id
	 **/
	@ApiModelProperty(value = "扩展的id", name = "resourceId", required = true)
	@Column(name = "resourceId") // 指定不符合第3条规则的字段名
	private String resourceId;
	/**
	 * 资源名称
	 **/
	@ApiModelProperty(value = "资源名称", name = "name", required = true)
	private String name;
	/**
	 * 权限描述
	 **/
	@ApiModelProperty(value = "权限描述", name = "description")
	private String description;
	/**
	 * 权限访问路径
	 **/
	@ApiModelProperty(value = "权限访问路径", name = "url")
	private String url;
	/**
	 * 权限标识
	 **/
	@ApiModelProperty(value = "装逼的id", name = "resourceId")
	private String permission;
	/**
	 * 父编号
	 **/
	@ApiModelProperty(value = "父编号", name = "parentId")
	@Column(name = "parentId")
	private Integer parentId;
	/**
	 * 资源类型:0目录 1菜单 2按钮
	 **/
	@ApiModelProperty(value = "资源类型:0目录 1菜单 2按钮", name = "type")
	private Integer type;
	/**
	 * 显示顺序
	 **/
	@ApiModelProperty(value = "显示顺序", name = "priority")
	private Integer priority;
	/**
	 * 图标
	 **/
	@ApiModelProperty(value = "图标", name = "icon")
	private String icon;
	/**
	 * 是否可用:1有效2删除
	 **/
	@ApiModelProperty(value = "是否可用:1有效2删除", name = "status", required = true)
	private Integer status;
	/**
	 * 父name添加该注解的字段不会作为表字段使用
	 **/
	@ApiModelProperty(value = "父name", name = "parentname")
	@Transient
	private String parentname;
}
