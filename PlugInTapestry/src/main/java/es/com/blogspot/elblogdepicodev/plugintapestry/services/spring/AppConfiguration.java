package es.com.blogspot.elblogdepicodev.plugintapestry.services.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;

import es.com.blogspot.elblogdepicodev.plugintapestry.services.dao.ProductoDAO;
import es.com.blogspot.elblogdepicodev.plugintapestry.services.dao.ProductoDAOImpl;
import es.com.blogspot.elblogdepicodev.plugintapestry.services.hibernate.ProductoEventAdapter;

@Configuration
@ComponentScan({ "es.com.blogspot.elblogdepicodev.plugintapestry" })
@EnableTransactionManagement
public class AppConfiguration {

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:mem:test");
		ds.setUsername("sa");
		ds.setPassword("sa");
		return ds;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dataSource);
		sf.setPackagesToScan("es.com.blogspot.elblogdepicodev.plugintapestry.entities");
		sf.setHibernateProperties(getHibernateProperties());
		return sf;
	}
	
	@Bean
	public ResourceTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager tm = new HibernateTransactionManager();
		tm.setSessionFactory(sessionFactory);
		return tm;
	}
	
	@Bean
	public ProductoEventAdapter productoEventAdapter() {
		return new ProductoEventAdapter();
	}
	
	@Bean
	public ProductoDAO productoDAO(SessionFactory sessionFactory) {
		return new ProductoDAOImpl(sessionFactory);
	}
	
	@Bean
	public DummyService dummyService() {
		return new DummyService();
	}
	
	private Properties getHibernateProperties() {
		Map<String, Object> m = new HashMap<>();
		m.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		m.put("hibernate.hbm2ddl.auto", "create");
		// Debug
		m.put("hibernate.generate_statistics", true);
		m.put("hibernate.show_sql", true);

		Properties properties = new Properties();
		properties.putAll(m);
		return properties;
	}
}
