package com.globallogic.push_service_poc.demo.config;

/**
 * Created by VladyslavPrytula on 4/12/14.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DataBaseConfig {

    @Bean(name = "entitymanager1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
      /*  entityManagerFactoryBean.setPackagesToScan(new String[]{this.environment
                        .getProperty("db.packagesToScan")});*/
        entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        //entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter());

        entityManagerFactoryBean.setPersistenceUnitName("mongoDBUnit2");

        Map<String, Object> jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("eclipselink.weaving", "false");
        jpaProperties.put("eclipselink.logging.level", "INFO");
        jpaProperties.put("eclipselink.logging.parameters", "true");
        entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);

        return entityManagerFactoryBean;
    }




/*
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }*/


}
