package org.eagle.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: EagleProperties
 * @Description: 自定义系统配置
 * @Author lucy
 * @Date 2019/5/14 17:59
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "eagle")
public class EagleProperties {

    private ShiroProperties shiro = new ShiroProperties();

    private boolean openAopLog = true;

}
