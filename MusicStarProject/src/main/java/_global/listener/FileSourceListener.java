package _global.listener;

import java.io.File;
import java.net.MalformedURLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FileSourceListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent event)  { 
		ServletContext servletContext = event.getServletContext();	
		try
		{
			String resourcePath = servletContext.getResource("").getPath();		
			String contextPath =	servletContext.getContextPath();
			String rootPath = resourcePath.substring(0, resourcePath.indexOf(contextPath)+1);
			// 存放檔案的資料夾路徑
			String fileSourcePath = rootPath + "FileSource" + File.separator;
			// 如果目錄不存在則創建此目錄
			File fileSourceDir = new File(fileSourcePath);
			if (!fileSourceDir.exists())
			{
				fileSourceDir.mkdir();
			}
			servletContext.setAttribute("fileSourcePath", fileSourcePath);
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		}	
	}

    public void contextDestroyed(ServletContextEvent event)  { 

    }

	
}
