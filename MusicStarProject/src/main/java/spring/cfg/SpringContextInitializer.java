package spring.cfg;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import _global.utils.Constant;

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
	
    @Override  
    protected Filter[] getServletFilters() {  
        CharacterEncodingFilter c = new CharacterEncodingFilter();  
        c.setEncoding("UTF-8");  
        c.setForceRequestEncoding(true);
        return new Filter[] {c};    
    }
    
    @Override
    protected void customizeRegistration(Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement("",Constant.fileUploadMaxFileSize,Constant.fileUploadMaxRequestSize,Constant.fileUploadMemoryThreshold));
    }

}
