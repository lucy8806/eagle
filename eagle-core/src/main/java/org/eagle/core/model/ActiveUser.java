package org.eagle.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.eagle.core.utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: ActiveUser
 * @Description: 在线用户
 * @Author lucy
 * @Date 2019/5/15 11:50
 * @Version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActiveUser implements Serializable {

    private static final long serialVersionUID = 6946791638036871529L;

    // 唯一编号
    private String id = RandomStringUtils.randomAlphanumeric(20);
    // 用户名
    private String username;
    // ip地址
    private String ip;
    // token(加密后)
    private String token;
    // 登录时间
    private String loginTime = DateUtils.format(LocalDateTime.now());
    // 登录地点
    private String loginAddress;
}
