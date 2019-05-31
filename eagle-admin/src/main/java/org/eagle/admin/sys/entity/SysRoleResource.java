package org.eagle.admin.sys.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @ClassName: SysRoleResource
 * @Description: 角色资源关联数据对象
 * @Author lusai
 * @Date 2019/5/30 18:59
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "角色资源关联实体")
public class SysRoleResource implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleId;
    private String resourceId;
}
