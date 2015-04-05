package edu.harvard.cscie99.adam.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "edu.harvard.cscie99.adam"})
@ImportResource({ "classpath:hibernate.cfg.xml" })
public class PersistenceXmlConfig {

    public PersistenceXmlConfig() {
        super();
    }

}