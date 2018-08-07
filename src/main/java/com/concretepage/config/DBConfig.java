package com.concretepage.config;

import java.util.Properties;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfig {

	@Bean
	@Autowired
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
		// return new HibernateTemplate(sessionFactory());
		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
		return hibernateTemplate;
	}

	@Bean
	public AnnotationSessionFactoryBean sessionFactory() {
		AnnotationSessionFactoryBean asfb = new AnnotationSessionFactoryBean();
		asfb.setDataSource(getDataSource());
		asfb.setHibernateProperties(getHibernateProperties());
		asfb.setPackagesToScan(new String[] { "com.concretepage.entity" });
		return asfb;
	}

	private Properties getHibernateProperties() {
		Properties pr = new Properties();
		pr.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		pr.setProperty("hibernate.show_sql", "true");
		pr.setProperty("hibernate.connection.release_mode", "after_transaction");
		pr.setProperty("hibernate.transaction.auto_close_session", "true");
		pr.setProperty("hibernate.hbm2ddl.auto", "none");
		pr.setProperty("hibernate.default_batch_fetch_size", "5");
		return pr;
	}

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/concretepage");
		dataSource.setUsername("chintanvi");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager hibTransMan(SessionFactory sessionFactory) {
		// return new HibernateTransactionManager(sessionFactory());
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sessionFactory);
		return htm;
	}
}
