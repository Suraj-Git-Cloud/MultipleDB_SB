package com.svcomfort.configurations;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "svcomfortdbEntityManager",
		transactionManagerRef = "svcomfortdbTransactionManager", 
		basePackages = "com.svcomfort.svcomfortdb.repository")

public class SvComfortDB {

	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.svcomfortdb.datasource")
	public DataSource svcomfortdbdataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "svcomfortdbEntityManager")
	public LocalContainerEntityManagerFactoryBean svcomfortdbEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(svcomfortdbdataSource())
				.properties(hibernateProperties())
				.packages("com.svcomfort.svcomfortdb.model") // vimp																										// given
				.persistenceUnit("svcomfortdb-persistent-init") // not given
				.build();
	}

	@Primary
	@Bean(name = "svcomfortdbTransactionManager")
	public PlatformTransactionManager svcomfortdbTransactionManager(
			@Qualifier("svcomfortdbEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	
	private Map<String, String> hibernateProperties() {

		Map<String, String> hibernateProperties = new HashMap<String, String>();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.put("hibernate.show_sql", "true");
		

		return hibernateProperties;

	}
	
	/*
	 	@ConfigurationProperties(prefix = "spring.svcomfortdb.jpa")
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.setProperty("hibernate.current_session_context_class", env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
		properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", env.getProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation"));
		properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		return properties;
	}
 */
	 
	
	
}
