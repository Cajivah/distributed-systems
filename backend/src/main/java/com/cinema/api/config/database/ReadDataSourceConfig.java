package com.cinema.api.config.database;

import com.cinema.api.config.properties.DatabaseProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.cinema.api.cinema.repository.read" },
     entityManagerFactoryRef = "entityManagerFactory")
public class ReadDataSourceConfig {

     @Bean(name = "dataSource")
     @ConfigurationProperties(prefix = "read.datasource")
     public DataSource dataSource() {
          return DataSourceBuilder.create().build();
     }

     @Bean(name = "entityManagerFactory")
     public LocalContainerEntityManagerFactoryBean entityManagerFactory(
          EntityManagerFactoryBuilder builder,
          @Qualifier("dataSource") DataSource dataSource,
          DatabaseProperties databaseProperties) {
          Map<String, String> properties =
               Map.ofEntries(Map.entry("hibernate.dialect", databaseProperties.getProperties().getHibernate().getDialect()),
                    Map.entry("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName()),
                    Map.entry("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName()));

          return builder
               .dataSource(dataSource)
               .packages("com.cinema.api.cinema.model.entity")
               .persistenceUnit("read")
               .properties(properties)
               .build();
     }

     @Bean(name = "transactionManager")
     public PlatformTransactionManager transactionManager(
          @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
          return new JpaTransactionManager(entityManagerFactory);
     }
}
