package org.eagle.core.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SpringContextHolder.java
 * @Description: 可以通过这个类直接获取spring 配置文件中 所有引用（注入）到的bean对象
 * @Author lucy
 * @Date 2019年5月8日 下午10:03:38
 * @Version 1.0
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContextEagle = null;

	/***
	 * 根据name获取bean
	 * 
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return applicationContextEagle.getBean(name);
	}

	/***
	 * 根据class获取bean
	 * 
	 * @param tClass
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(Class<T> tClass) {
		return applicationContextEagle.getBean(tClass);
	}

	/***
	 * 根据name，指定class返回Bean
	 * 
	 * @param name
	 * @param tClass
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(String name, Class<T> tClass) {
		return applicationContextEagle.getBean(name, tClass);
	}

	/***
	 * 重写
	 * 
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (applicationContextEagle == null) {
			applicationContextEagle = applicationContext;
		}
	}

}
