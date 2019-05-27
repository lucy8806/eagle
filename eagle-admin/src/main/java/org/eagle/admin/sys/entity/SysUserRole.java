package org.eagle.admin.sys.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @ClassName: SysUserRole
 * @Description: 用户角色关联实体
 * @Author lucy
 * @Date 2019/5/21 16:04
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "用户角色关联实体")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 3971955379056653069L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    private String roleId;
}
