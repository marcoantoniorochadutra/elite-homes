package com.elitehomes.service.config;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(value = "com.elitehomes.domain.repository")
@ComponentScan(basePackages = {
		"com.elitehomes.service.impl",
		"com.elitehomes.domain.config",
		"com.elitehomes.core.database.repository",
		"com.elitehomes.domain.repository.gcp",
		"com.elitehomes.service.converter",
		"com.elitehomes.domain.config"
})
@EntityScan(value = "com.elitehomes.domain.entity")
@EnableAutoConfiguration(exclude = { QuartzAutoConfiguration.class })
public class ServiceConfig {

	@Bean
	public Validator localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return JsonMapper.builder()
				.addModule(new JavaTimeModule())
				.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
				.build();
	}

	@Bean
	public DozerBeanMapperFactoryBean dozerMapper(ResourcePatternResolver resourcePatternResolver) throws IOException {
		DozerBeanMapperFactoryBean factoryBean = new DozerBeanMapperFactoryBean();
		factoryBean.setMappingFiles(resourcePatternResolver.getResources("classpath*:/*mapping.xml"));
		return factoryBean;
	}

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@Bean
	public RestTemplate restTemplateNoHeaders(ClientHttpRequestFactory clientHttpRequestFactory) {
		RestTemplate restTemplateNoHeaders = new RestTemplate(clientHttpRequestFactory);
		List<ClientHttpRequestInterceptor> noHeadersInterceptors = new ArrayList<>();
		restTemplateNoHeaders.setInterceptors(noHeadersInterceptors);
		return restTemplateNoHeaders;
	}

	@Bean
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		return new BufferingClientHttpRequestFactory(requestFactory);
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource,
			JpaProperties jpaProperties,
			ConfigurableListableBeanFactory beanFactory,
			MultiTenantConnectionProvider<String> multiTenantConnectionProviderImpl,
			CurrentTenantIdentifierResolver<String> currentTenantIdentifierResolverImpl) {
		Map<String, Object> properties = new HashMap<>(jpaProperties.getProperties());
		properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProviderImpl);
		properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolverImpl);
		properties.put(Environment.HBM2DDL_AUTO, System.getProperty("HIBERNATE_DDL_OPTION"));
		properties.put(Environment.PHYSICAL_NAMING_STRATEGY, "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.elitehomes.domain.entity", "com.elitehomes.core.domain.entity");
		em.setJpaVendorAdapter(jpaVendorAdapter());
		em.setJpaPropertyMap(properties);
		em.getJpaPropertyMap().put(AvailableSettings.BEAN_CONTAINER, new SpringBeanContainer(beanFactory));
		return em;

	}
}
