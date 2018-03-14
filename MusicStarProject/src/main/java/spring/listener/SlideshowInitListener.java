package spring.listener;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import model.service.SlideshowService;

public class SlideshowInitListener implements ApplicationListener<ContextRefreshedEvent>
{
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private SlideshowService slideshowService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		if (event.getApplicationContext().getParent() == null)
		{
			servletContext.setAttribute("slides", slideshowService.getAllSlides());
		} 
	}

}
