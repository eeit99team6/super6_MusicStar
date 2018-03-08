package spring.cfg;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
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
		xmlViewResolver.setLocation(new ServletContextResource(servletContext, "/WEB-INF/spring.views.cfg.xml"));		
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

    @Bean  
    public MultipartResolver multipartResolver()throws IOException{  
        return new StandardServletMultipartResolver();  
    }
    
    @Bean
    public String fileSourcePath() throws MalformedURLException {
    	String resourcePath = servletContext.getResource("").getPath();		
		String contextPath = servletContext.getContextPath();
		String rootPath = resourcePath.substring(0, resourcePath.indexOf(contextPath)+1);
		// 存放檔案的資料夾路徑
		String fileSourcePath = rootPath + "FileSource" + File.separator;
		// 如果目錄不存在則創建此目錄
		File fileSourceDir = new File(fileSourcePath);
		if (!fileSourceDir.exists())
		{
			fileSourceDir.mkdir();
		}
    	return fileSourcePath;
    }
    
    @Bean
    public String imagesDirectoryPath() throws MalformedURLException {
    	String imagesDirectoryPath = fileSourcePath() + "images" + File.separator;
		File imagesDir = new File(imagesDirectoryPath);
		if (!imagesDir.exists()) {
			imagesDir.mkdir();
		}
		return imagesDirectoryPath;
    }
    
    @Bean
    public String profilesDirectoryPath() throws MalformedURLException {
    	String profilesDirectoryPath = imagesDirectoryPath()+ "profiles" + File.separator;
		File profilesDir = new File(profilesDirectoryPath);
		if (!profilesDir.exists()) {
			profilesDir.mkdir();
		}
		return profilesDirectoryPath;
    }
   
    @Bean
    public String coverDirectoryPath() throws MalformedURLException {
    	String coverDirectoryPath = imagesDirectoryPath()+ "cover" + File.separator;
		File coverDir = new File(coverDirectoryPath);
		if (!coverDir.exists()) {
			coverDir.mkdir();
		}
		return coverDirectoryPath;
    } 
    
    @Bean
    public String audiosDirectoryPath() throws MalformedURLException {
    	String audiosDirectoryPath = fileSourcePath() + "audios" + File.separator;
		File audiosDir = new File(audiosDirectoryPath);
		if (!audiosDir.exists()) {
			audiosDir.mkdir();
		}
		return audiosDirectoryPath;
    } 

}
