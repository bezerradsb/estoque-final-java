package com.springapp.projeto;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DataConfig implements WebMvcConfigurer{

	
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/db_estoque");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		return adapter;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path produtoUploadDir = Paths.get("./src/main/resources/static/produto-logos/");
		String produtoUploadPath = produtoUploadDir.toFile().getAbsolutePath();
			if (!registry.hasMappingForPattern("./src/main/resources/static/produto-logos/**")) {
		registry.addResourceHandler("./src/main/resources/static/produto-logos/**").addResourceLocations(
						"classpath:/META-INF/resources/static/produto-logos/");
			}
			if (!registry.hasMappingForPattern("/**")) {
				registry.addResourceHandler("/**").addResourceLocations("file:src/main/resources/static/" + produtoUploadPath + "/");
			}
	}
}
