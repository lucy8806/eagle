package org.eagle.core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eagle.core.context.SpringContextHolder;
import org.eagle.core.properties.EagleProperties;

import java.util.Date;

/**
 * @ClassName: JwtUtil
 * @Description: TODO
 * @Author lucy
 * @Date 2019/5/14 18:12
 * @Version 1.0
 */
@Slf4j
public class JwtUtil {

    private static final long EXPIRE_TIME = SpringContextHolder.getBean(EagleProperties.class).getShiro().getJwtTimeOut() * 1000;

    /**
     * 生成 token
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return token
     */
    public static String sign(String username, String secret) {
        try {
            username = StringUtils.lowerCase(username);
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            log.error("error：{}", e);
            return null;
        }
    }

    /**
     * 从 token中获取用户名
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 校验 token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            verifier.verify(token);
            log.info("token is valid");
            return true;
        } catch (Exception e) {
            log.info("token is invalid{}", e.getMessage());
            return false;
        }
    }
}
