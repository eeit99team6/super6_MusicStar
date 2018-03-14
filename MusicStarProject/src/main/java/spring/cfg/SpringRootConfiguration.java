package spring.cfg;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.listener.SlideshowInitListener;

@Configuration
@ComponentScan("model")
@EnableTransactionManagement
public class SpringRootConfiguration
{
	@Bean
	public DataSource dataSource()
	{
		try
		{
			return (DataSource) new InitialContext().lookup("java:comp/env/jdbc/MusicStar");
		} catch (NamingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	public SessionFactory sessionFactory()
	{
		return new LocalSessionFactoryBuilder(dataSource()).scanPackages("model.bean").configure("hibernate.cfg.xml").buildSessionFactory();
	}

	@Bean
	HibernateTransactionManager transactionManager()
	{
		return new HibernateTransactionManager(sessionFactory());
	}

    @Bean
    public SlideshowInitListener slideshowInitListener() {
    	return new SlideshowInitListener();
    }
    
}
