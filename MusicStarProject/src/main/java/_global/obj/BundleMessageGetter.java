package _global.obj;

import java.util.Locale;

import org.springframework.context.ApplicationContext;

public class BundleMessageGetter
{
	private ApplicationContext springContext;
	private Locale locale;

	public BundleMessageGetter(ApplicationContext springContext, Locale locale)
	{
		this.springContext = springContext;
		this.locale = locale;
	}

	public String getMsg(String key)
	{
		return springContext.getMessage(key, null, locale);
	}

	public String getMsg(String key, Object... args)
	{
		return springContext.getMessage(key, args, locale);
	}
}
