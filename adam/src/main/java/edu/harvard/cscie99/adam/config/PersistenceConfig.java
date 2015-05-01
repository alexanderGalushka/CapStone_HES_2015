package edu.harvard.cscie99.adam.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.base.Preconditions;

/**
 * Spring Persistence Configuration class
 * 
 * Binds the database properties files, and instantiate the Hibernate datasource.
 * Scans all Entity classes in ADAM application
 * Creates the Hibernate sessionFactory object
 * 
 * @author Gerson
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:db.properties" })
@ComponentScan({ "edu.harvard.cscie99.adam" })
public class PersistenceConfig {

	/**
	 * Reads the property file declared in @PropertySource annocation
	 */
    @Autowired
    private Environment env;

    /**
     * Constructor
     */
    public PersistenceConfig() {
        super();
    }

    /**
     * Creates the SessionFactory object and attaches Entity objects
     * @return LocalSessionFactoryBean - sessionFactory implementation
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(restDataSource());
        sessionFactory.setPackagesToScan(new String[] { "edu.harvard.cscie99.adam" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }
    
    /**
     * Creates the datasource
     * Creates a Hibernate datasource using the configuration provided in db.properties as parameters
     * 
     * @return datasource - datasource object
     */
    @Bean
    public DataSource restDataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));

        return dataSource;
    }

    /**
     * transactionManager method
     * 
     * Retrives the persistence context and serves the transaction Manager.
     * This allow transaction support in the application.
     * 
     * @param sessionFactory
     * @return HibernateTransactionManager - transactionManager
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(final SessionFactory sessionFactory) {
        final HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    /**
     * exceptionTranslation method
     * 
     * Translates persistence-side exceptions into JPA-compliant exceptions
     * @return
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * hibernateProperties method
     * 
     * Reads the db.properties file and sets them in HibernateProperties object.
     * This  object is used to instantiate Hibernate sessions
     * 
     * @return properties - object representation of properties file (key:value)
     */
    final Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

        hibernateProperties.setProperty("hibernate.show_sql", "true");
        // hibernateProperties.setProperty("hibernate.format_sql", "true");
        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");

        return hibernateProperties;
    }

}