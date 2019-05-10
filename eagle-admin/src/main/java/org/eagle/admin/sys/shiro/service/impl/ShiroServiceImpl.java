package org.eagle.admin.sys.shiro.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.eagle.admin.sys.entity.SysResource;
import org.eagle.admin.sys.entity.SysUser;
import org.eagle.admin.sys.service.ResourceService;
import org.eagle.admin.sys.service.UserService;
import org.eagle.admin.sys.shiro.ShiroRealm;
import org.eagle.admin.sys.shiro.service.ShiroService;
import org.eagle.core.context.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @ClassName: ShiroServiceImpl.java
 * @Description: Shiro服务实现类
 * @Author lucy
 * @Date 2019年5月8日 下午9:21:19
 * @Version 1.0
 */
@Service
public class ShiroServiceImpl implements ShiroService {

	private static final Logger log = LoggerFactory.getLogger(ShiroServiceImpl.class);

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private UserService userService;

	@Override
	public Map<String, String> loadFilterChainDefinitions() {
		/***
		 * 配置访问权限 anon:所有url都都可以匿名访问 authc:
		 * 需要认证才能进行访问（此处指所有非匿名的路径都需要登陆才能访问），支付等,建议使用authc权限 user:配置记住我或认证通过可以访问
		 */
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		// 开放资源文件
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/docs/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/plugins/**", "anon");
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/verificationCode", "anon");

		// 配置过滤器
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/error1", "anon");
		filterChainDefinitionMap.put("/kickout", "anon");

		// 开发环境开放
		/*
		 * filterChainDefinitionMap.put("/login2","anon");
		 * filterChainDefinitionMap.put("/index","anon");
		 * filterChainDefinitionMap.put("/role/**","anon");
		 * //filterChainDefinitionMap.put("/user/**","anon");
		 * filterChainDefinitionMap.put("/resource/**","anon");
		 */

		// 开放druid
		filterChainDefinitionMap.put("/druid/**", "anon");

		// 开放swagger
		filterChainDefinitionMap.put("/swagger-resources/**", "anon");
		filterChainDefinitionMap.put("/webjars/**", "anon");
		filterChainDefinitionMap.put("/v2/**", "anon");
		filterChainDefinitionMap.put("/swagger-ui.html/**", "anon");

		// 加载数据库中配置的资源权限列表
		List<SysResource> resourcesList = resourceService.listUrlAndPermission();
		int a = 0;
		for (SysResource resource : resourcesList) {
			if (StrUtil.isNotBlank(resource.getUrl()) && StrUtil.isNotBlank(resource.getPermission())) {
				String permission = "perms[" + resource.getPermission() + "]";
				filterChainDefinitionMap.put(resource.getUrl(), permission);
				a += 1;
			}
		}

		// authc:所有url都必须认证通过才可以访问;
		// anon:所有url都都可以匿名访问,这里我使用user操作即可，如果安全要求比较高，建议使用authc
		filterChainDefinitionMap.put("/**", "user");

		log.info("[eagle初始化资源成功,数据库资源条数]-[{}],初始化数据库资源条数-[{}]", resourcesList.size(), a);
		return filterChainDefinitionMap;
	}

	@Override
	public void updatePermission() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = SpringContextHolder.getBean(ShiroFilterFactoryBean.class);
		synchronized (shiroFilterFactoryBean) {
			AbstractShiroFilter abstractShiroFilter = null;
			try {
				abstractShiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
			} catch (Exception e) {
				throw new RuntimeException("Get AbstractShiroFilter error");
			}
			PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) abstractShiroFilter
					.getFilterChainResolver();
			DefaultFilterChainManager defaultFilterChainManager = (DefaultFilterChainManager) pathMatchingFilterChainResolver
					.getFilterChainManager();
			// 清空老的权限控制
			defaultFilterChainManager.getFilterChains().clear();
			shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
			shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
			// 重新构建生成
			Map<String, String> map = shiroFilterFactoryBean.getFilterChainDefinitionMap();
			for (Map.Entry<String, String> stringEntry : map.entrySet()) {
				String url = stringEntry.getKey();
				String chainDefinition = stringEntry.getValue().trim().replace(" ", "");
				defaultFilterChainManager.createChain(url, chainDefinition);
			}
		}
		log.info("[eagle权限重新加载成功]");

	}

	@Override
	public void reloadAuthorizingByUserId(SysUser user) {
		RealmSecurityManager realmSecurityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		ShiroRealm shiroReam = (ShiroRealm) realmSecurityManager.getRealms().iterator().next();
		Subject subject = SecurityUtils.getSubject();
		String realmName = subject.getPrincipals().getRealmNames().iterator().next();
		SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection(user, realmName);
		subject.runAs(simplePrincipalCollection);
		shiroReam.getAuthorizationCache().remove(subject.getPrincipals());
		subject.releaseRunAs();
		log.info("[以下用户权限更新成功！]-[{}]", user.getUsername());
	}

	@Override
	public void reloadAuthorizingByRoleId(Integer roleId) {
		List<SysUser> userList = userService.listUsersByRoleId(roleId);
		if (CollectionUtils.isEmpty(userList)) {
			return;
		}
		for (SysUser user : userList) {
			reloadAuthorizingByUserId(user);
		}
	}

}
