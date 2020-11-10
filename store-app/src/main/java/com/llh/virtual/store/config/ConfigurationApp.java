/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.virtual.store.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 *
 * @author lorenzo
 */
@Configuration
@EnableJpaRepositories(basePackages = {
    "com.llh.virtual.store.home.dao.repository",
    "com.llh.virtual.store.login.dao.repository"
}, entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
public class ConfigurationApp {

    @Value("${default.schema}")
    private String schema;
    @Value("${default.dialect}")
    private String dialect;

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[]{"com.llh.virtual.store.home.domain","com.llh.virtual.store.login.domain"});
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.default_schema", schema);
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactory") final EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
         @Bean
    public SpringLiquibase liquibase(@Qualifier("dataSource") DataSource dataSource) throws IllegalArgumentException, NamingException {
        SpringLiquibase liquibase = new SpringLiquibase();
        try {
            String defaultSchema = schema;
            System.setProperty("schema", defaultSchema);
            String changelogFile = "classpath:/database/db.changelog-master.xml";
            liquibase.setChangeLog(changelogFile);
            liquibase.setDefaultSchema(defaultSchema);
            liquibase.setDataSource(dataSource);
            liquibase.setDropFirst(false);
            liquibase.setContexts(defaultSchema);
            liquibase.setShouldRun(true);
            Map<String, String> params = new HashMap<>();
             params.put("verbose", "true");
            liquibase.setChangeLogParameters(params);
        } catch (Exception e) {
            System.out.println(" Exception: "+ e.getMessage());
        }
        return liquibase;
    }
}
