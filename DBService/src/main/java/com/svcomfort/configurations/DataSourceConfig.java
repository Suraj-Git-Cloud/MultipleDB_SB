package com.svcomfort.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
	

	@Bean(name = "savvis2_write")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource2() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "writeTemplate")
	@Primary
	public NamedParameterJdbcTemplate jdbcTemplate2(@Qualifier("savvis2_write") DataSource ds) {
		return new NamedParameterJdbcTemplate(ds);
	}



	@Bean(name = "writeTransactionManager")
	@Autowired
	DataSourceTransactionManager tm2(@Qualifier("savvis2_write") DataSource datasource) {
		return new DataSourceTransactionManager(datasource);
	}
	
	
	@Bean(name = "savvis2_read")
	@ConfigurationProperties(prefix = "spring.secondary-datasource")
	public DataSource dataSource1() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "readTemplate")
	public NamedParameterJdbcTemplate jdbcTemplate1(@Qualifier("savvis2_read") DataSource ds) {
		return new NamedParameterJdbcTemplate(ds);
	} 
	
	@Bean(name = "readTransactionManager")
	@Autowired
	DataSourceTransactionManager tm1(@Qualifier("savvis2_read") DataSource datasource) {
		return new DataSourceTransactionManager(datasource);
	} 

}
