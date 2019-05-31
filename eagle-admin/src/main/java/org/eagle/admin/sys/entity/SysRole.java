package org.eagle.admin.sys.entity;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.eagle.core.model.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "角色数据对象类")
public class SysRole extends BaseEntity {

	private static final long serialVersionUID = -5408680364121627429L;

	/** 扩展的id */
	@ApiModelProperty(value = "扩展的id", name = "roleId", required = true)
	@Column(name = "roleId")
	private String roleId;

	/** 角色名称 */
	@ApiModelProperty(value = "角色名称", name = "role", required = true)
	private String role;

	/** 角色描述 */
	@ApiModelProperty(value = "角色描述", name = "description")
	private String description;

	/** 是否可用：1有效2删除 */
	@ApiModelProperty(value = "是否可用：1有效2删除", name = "status", required = true)
	private Integer status;

	/** 是否选中*/
	@Transient
	private Integer selected;

	/** 角色关联的资源ID */
	@Transient
	private String resourceIds;
}
