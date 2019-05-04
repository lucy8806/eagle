package org.eagle.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description:API文档生成配置
 * @author Administrator
 * @date:2019年5月4日 下午8:23:55
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// 包路径
				.apis(RequestHandlerSelectors.basePackage("org.eagel.admin.sys.controller")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("hope-admin权限管理系统API操作文档").description("后台管理中心 API 1.0 操作文档")
				// 服务条款网址
				.termsOfServiceUrl("https://aodeng.cc").version("2.0")
				.contact(new Contact("Lucy", "https://aodeng.cc", "java@aodeng.cc")).build();
	}
}