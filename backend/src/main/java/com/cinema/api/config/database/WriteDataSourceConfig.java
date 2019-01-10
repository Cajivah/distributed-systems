package com.cinema.api.config.database;

import com.cinema.api.config.properties.DatabaseProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(basePackages = { "com.cinema.api.cinema.repository.write",
     "com.cinema.api.user.repository"},
     entityManagerFactoryRef = "writeEntityManagerFactory")
@EnableConfigurationProperties(DatabaseProperties.class)
public class WriteDataSourceConfig {

     @Primary
     @Bean(name = "writeDataSource")
     @ConfigurationProperties(prefix = "spring.datasource")
     public DataSource dataSource() {
          return DataSourceBuilder.create().build();
     }

     @Primary
     @Bean(name = "writeEntityManagerFactory")
     public LocalContainerEntityManagerFactoryBean entityManagerFactory(
          EntityManagerFactoryBuilder builder,
          @Qualifier("writeDataSource") DataSource dataSource, DatabaseProperties databaseProperties) {
          Map<String, String> properties =
               Map.ofEntries(Map.entry("hibernate.hbm2ddl.auto", databaseProperties.getHibernate().getDdlAuto()),
                    Map.entry("hibernate.dialect", databaseProperties.getProperties().getHibernate().getDialect()),
                    Map.entry("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName()),
                    Map.entry("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName()),
                    Map.entry("hibernate.enable_lazy_load_no_trans", "true"));

          return builder
               .dataSource(dataSource)
               .properties(properties)
               .packages("com.cinema.api.cinema.model.entity", "com.cinema.api.user.model.entity")
               .persistenceUnit("write")
               .build();
     }

     @Primary
     @Bean(name = "writeTransactionManager")
     public PlatformTransactionManager transactionManager(
          @Qualifier("writeEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
          return new JpaTransactionManager(entityManagerFactory);
     }
}
