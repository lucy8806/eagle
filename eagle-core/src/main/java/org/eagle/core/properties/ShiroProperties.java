package org.eagle.core.properties;

import lombok.Data;

/**
 * @ClassName: ShiroProperties
 * @Description: shiro相关配置
 * @Author lucy
 * @Date 2019/5/15 9:31
 * @Version 1.0
 */
@Data
public class ShiroProperties {

    private String anonUrl;

    /** token默认有效时间 1天 */
    private Long jwtTimeOut = 86400L;

}
