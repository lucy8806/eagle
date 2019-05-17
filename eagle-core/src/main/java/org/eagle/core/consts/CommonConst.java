package org.eagle.core.consts;

/**
 * @ClassName: CommonConst
 * @Description: 公共常量
 * @Author lucy
 * @Date 2019/5/815:56
 * @Version 1.0
 */
public class CommonConst {
    /** 程序错误状态码 */
    public static final int DEFAULT_ERROR_CODE = 500;
    /** 程序成功状态码 */
    public static final int DEFAULT_SUCCESS_CODE = 200;
    /** session key */
    public static final String USER_SESSION_KEY = "user_session_key";
    /** uuid盐 */
    public static final String ZYD_SECURITY_KEY = "929123f8f17944e8b0a531045453e1f1";

    /** token缓存前缀 */
    public static final String TOKEN_CACHE_PREFIX = "eagle.cache.token.";

    /** 存储在线用户的 zset前缀 */
    public static final String ACTIVE_USERS_ZSET_PREFIX = "eagle.user.active";

}
