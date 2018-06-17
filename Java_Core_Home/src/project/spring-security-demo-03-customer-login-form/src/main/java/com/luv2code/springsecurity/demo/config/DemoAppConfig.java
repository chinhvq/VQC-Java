package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.luv2code.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource securityDataSource() {
		try {
			ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
			System.out.println(">>> jdbc.url - " + env.getProperty("jdbc.url"));
			System.out.println(">>> jdbc.user - " + env.getProperty("jdbc.user"));
			securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			securityDataSource.setUser(env.getProperty("jdbc.user"));
			securityDataSource.setPassword(env.getProperty("jdbc.password"));

			securityDataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
			securityDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
			securityDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
			securityDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
			return securityDataSource;
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
	}

	// define the Bean for View Resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
