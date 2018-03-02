package spring.cfg;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringContextInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	

	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return new Class[] {SpringRootConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class[] {SpringMvcConfiguration.class};
	}

	@Override
	protected String[] getServletMappings()
	{
		return new String[] {"/"};
	}

}
