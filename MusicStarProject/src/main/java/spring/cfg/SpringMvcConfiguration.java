package spring.cfg;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

import spring.interceptor.MustLoginInterceptor;

@Configuration
@ComponentScan("controller")
@EnableWebMvc
public class SpringMvcConfiguration implements WebMvcConfigurer
{
	@Autowired
	private ServletContext servletContext;
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry)
	{
		XmlViewResolver xmlViewResolver = new XmlViewResolver();
		xmlViewResolver.setLocation( new ServletContextResource(servletContext, "/WEB-INF/spring.views.cfg.xml"));		
		xmlViewResolver.setOrder(1);
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setOrder(2);	
		registry.viewResolver(xmlViewResolver);
		registry.viewResolver(internalResourceViewResolver);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(new MustLoginInterceptor());
	}


}
