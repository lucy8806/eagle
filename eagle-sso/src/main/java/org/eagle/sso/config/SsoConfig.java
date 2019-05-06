package org.eagle.sso.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.xxl.sso.core.store.SsoLoginStore;
import com.xxl.sso.core.util.JedisUtil;

/**
 * 
 * @author lucy
 * @date:2019年5月6日 下午8:57:27
 *
 */
@Configuration
public class SsoConfig implements InitializingBean, DisposableBean {

	@Value("${sso.redis.address}")
	private String redisAddress;

	@Value("${sso.redis.expire.minite}")
	private int redisExpireMinite;

	@Override
	public void afterPropertiesSet() throws Exception {
		SsoLoginStore.setRedisExpireMinite(redisExpireMinite);
		JedisUtil.init(redisAddress);
	}

	@Override
	public void destroy() throws Exception {
		JedisUtil.close();
	}

}
