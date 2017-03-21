package io.github.xiaobaxi.boot.dubbo;

import io.github.xiaobaxi.boot.dubbo.endpoint.DubboEndpoint;
import io.github.xiaobaxi.boot.dubbo.endpoint.DubboOperationEndpoint;
import io.github.xiaobaxi.boot.dubbo.health.DubboHealthIndicator;
import io.github.xiaobaxi.boot.dubbo.metrics.DubboMetrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * Dubbo common configuration
 *
 * @author fangzhibin
 */
@Configuration
@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfiguration {
	@Autowired
	private DubboProperties properties;

	@Bean
	@ConditionalOnMissingBean
	public ApplicationConfig dubboApplicationConfig() {
		ApplicationConfig appConfig = new ApplicationConfig();
		appConfig.setName(this.properties.getAppname());
		return appConfig;
	}

	@Bean
	@ConditionalOnMissingBean
	public ProtocolConfig dubboProtocolConfig() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setName(this.properties.getProtocol());
		protocolConfig.setPort(this.properties.getPort());
		protocolConfig.setThreads(this.properties.getThreads());
		return protocolConfig;
	}

	@Bean
	@ConditionalOnMissingBean
	public RegistryConfig dubboRegistryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress(this.properties.getRegistry());
		return registryConfig;
	}

	@Bean
	public DubboHealthIndicator dubboHealthIndicator() {
		return new DubboHealthIndicator();
	}

	@Bean
	public DubboEndpoint dubboEndpoint() {
		return new DubboEndpoint();
	}

	@Bean
	public DubboMetrics dubboConsumerMetrics() {
		return new DubboMetrics();
	}

	@Bean
	public DubboOperationEndpoint dubboOperationEndpoint() {
		return new DubboOperationEndpoint();
	}

}
