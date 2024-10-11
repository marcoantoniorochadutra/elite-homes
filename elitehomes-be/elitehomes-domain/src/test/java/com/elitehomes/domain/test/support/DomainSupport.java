package com.elitehomes.domain.test.support;

import com.elitehomes.domain.entity.State;
import com.elitehomes.domain.repository.CityRepository;
import com.elitehomes.domain.repository.StateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(classes = DomainSupport.TestJpaMultiTenantConfig.class)

public class DomainSupport extends AbstractTestDbSetup {

    @Autowired
    protected CityRepository cityRepository;

    @Autowired
    protected StateRepository stateRepository;


    protected State changeStateStatus(String uf, Boolean active) {
        State state = stateRepository.getReferenceByUf(uf);
        state.setActive(active);
        return stateRepository.saveAndFlush(state);
    }

    @Configuration
    @EnableJpaRepositories(basePackages = "com.elitehomes.domain.repository")
    @EntityScan("com.elitehomes.domain.entity")
    @ComponentScan({
            "com.elitehomes.domain.config",
            "com.elitehomes.domain.converter"})
    @EnableAutoConfiguration(exclude = {QuartzAutoConfiguration.class})
    public static class TestJpaMultiTenantConfig {

        @Bean
        @Primary
        public ObjectMapper objectMapper() {
            return new ObjectMapper().registerModule(new JavaTimeModule());
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
            em.setPackagesToScan("com.elitehomes.domain.entity");
            em.setJpaVendorAdapter(jpaVendorAdapter());
            em.setJpaPropertyMap(properties);
            em.getJpaPropertyMap().put(AvailableSettings.BEAN_CONTAINER, new SpringBeanContainer(beanFactory));
            return em;
        }
    }

}
