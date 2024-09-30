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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "hoteldbEntityManager",
		transactionManagerRef = "hoteldbTransactionManager", 
		basePackages = "com.svcomfort.hoteldb.repository")
public class HotelDB {
	
	
	@Bean
	@ConfigurationProperties(prefix = "spring.hoteldb.datasource")
	public DataSource hoteldbdataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "hoteldbEntityManager")
	public LocalContainerEntityManagerFactoryBean hoteldbEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(hoteldbdataSource())
				.properties(hibernateProperties())
				.packages("com.svcomfort.hoteldb.model") // vimp these package is to provide entities to entity manager 																										// given
				.persistenceUnit("hoteldb-persistent-init") // not given
				.build();
	}

	
	@Bean(name = "hoteldbTransactionManager")
	public PlatformTransactionManager hoteldbTransactionManager(
			@Qualifier("hoteldbEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	private Map<String, String> hibernateProperties() {

		Map<String, String> hibernateProperties = new HashMap<String, String>();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.put("hibernate.show_sql", "true");	

		return hibernateProperties;

	}
}
