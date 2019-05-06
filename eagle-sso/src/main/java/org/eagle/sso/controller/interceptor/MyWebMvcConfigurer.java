package org.eagle.sso.controller.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web mvc config
 *
 * @author lucy 2019-05-06 20:48:20
 */
@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}