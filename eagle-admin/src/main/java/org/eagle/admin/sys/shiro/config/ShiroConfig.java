package org.eagle.admin.sys.shiro.config;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.*;
import org.eagle.admin.sys.shiro.ShiroRealm;
import org.eagle.admin.sys.shiro.credentials.RetryLimitCredentialsMatcher;
import org.eagle.admin.sys.shiro.filter.JwtFilter;
import org.eagle.admin.sys.shiro.filter.KickoutSessionControlFilter;
import org.eagle.admin.sys.shiro.service.ShiroService;
import org.eagle.core.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: ShiroConfig
 * @Description: Shiro配置类
 * @Author lucy
 * @Date 2019/5/10 10:45
 * @Version 1.0
 */
@Configuration
@Order(-1)
public class ShiroConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private ShiroService shiroService;

    /***
     * 管理shirobean生命周期
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /***
     * 创建SecurityManager
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置realm
        defaultWebSecurityManager.setRealm(shiroReam());
        //注入记住我的管理器
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        // 自定义缓存实现 使用redis
        defaultWebSecurityManager.setCacheManager(redisCacheManager());
        //使用redis自定义session管理
        defaultWebSecurityManager.setSessionManager(sessionManager());
        return defaultWebSecurityManager;
    }

    /**
     * 自定义ream，认证，授权
     * @return
     */
    @Bean
    public ShiroRealm shiroReam() {
        ShiroRealm shiroReam = new ShiroRealm();
        //匹配器，credentialsMatcher使用RetryLimitCredentialsMatcher
        //hashedCredentialsMatcher使用HashedCredentialsMatcher
        //这里简洁可以使用hashedCredentialsMatcher
        //hopeShiroReam.setCredentialsMatcher(hashedCredentialsMatcher());
        //shiroReam.setCredentialsMatcher(credentialsMatcher());
        return shiroReam;
    }

    /***
     * cookid管理对象，记住我功能
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("1QWLxg+NYmxraMoxAXu/Iw=="));
        return cookieRememberMeManager;
    }

    /***
     * Cookid对象
     * @return
     */
    public SimpleCookie simpleCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name=rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //cookie生效时间30天，单位秒，注释，默认永久不过期
        simpleCookie.setMaxAge(redisProperties.getExpire());
        return simpleCookie;
    }

    /***
     * 凭证匹配器
     * @return
     */
    @Bean(name = "credentialsMatcher")
    public RetryLimitCredentialsMatcher credentialsMatcher() {
        return new RetryLimitCredentialsMatcher();
    }

    /***
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public IRedisManager redisManager() {
        RedisManager redisManager = new RedisManager();

        //集群配置
        //RedisClusterManager redisClusterManager = new RedisClusterManager();
        //redisClusterManager.setHost(redisProperties.getHost());
        //redisClusterManager.setPassword(redisProperties.getPassword());

        //配置地址，端口
        redisManager.setHost(redisProperties.getHost());
        redisManager.setPort(redisProperties.getPort());
        //配置缓存的redis库
        redisManager.setDatabase(redisProperties.getDatabase());
        //链接超时(毫秒)
        redisManager.setTimeout(redisProperties.getTimeout());
        //缓存过期时间
        /*redisManager.setExpire(redisProperties.getExpire());*/
        //配置密码
        redisManager.setPassword(redisProperties.getPassword());

        return redisManager;
    }

    /***
     * Shiro-Session管理
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /***
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        //shiroFilterFactoryBean.setLoginUrl("/login");
        //登陆成功跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权的界面
        //shiroFilterFactoryBean.setUnauthorizedUrl("/error1");
        //自定义拦截器
        Map<String, Filter> filterMap = new LinkedHashMap<String, Filter>();
        //限制同一个账号同时在线的个数
        filterMap.put("kickout", kickoutSessionControlFilter());

        //在 hiro过滤器链上加入JwtFilter
        filterMap.put("jwt", new JwtFilter());

        shiroFilterFactoryBean.setFilters(filterMap);
        //配置数据库中的resource
       // Map<String, String> filterChainDefinitionMap = shiroService.loadFilterChainDefinitions();

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/kickout", "anon");
        filterChainDefinitionMap.put("/login", "anon");

        //放行Swagger2页面
        filterChainDefinitionMap.put("/swagger-ui.html","anon");
        filterChainDefinitionMap.put("/swagger/**","anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**","anon");
        filterChainDefinitionMap.put("/v2/**","anon");

        // 所有请求都要经过 jwt过滤器
        filterChainDefinitionMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(redisCacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(5);
        //被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("/kickout");
        return kickoutSessionControlFilter;
    }

}
