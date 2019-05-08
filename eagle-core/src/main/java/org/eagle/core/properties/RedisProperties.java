package org.eagle.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RedisProperties
 * @Description: Redis属性
 * @Author lucy
 * @Date 2019/5/8 17:20
 * @Version 1.0
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
@EqualsAndHashCode(callSuper = false)
@Order(-1)
public class RedisProperties {
    private Integer database;
    private String host;
    private Integer port;
    private String password;
    /** 链接超时(毫秒) */
    private Integer timeout;
    /** 数据过期时间，默认30天 */
    private Integer expire = 2592000;
}
