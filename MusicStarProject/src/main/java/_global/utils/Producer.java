package _global.utils;

import javax.servlet.http.Cookie;

public class Producer
{
	/** 
	 * @param cookieName : Cookie的名稱
	 * @param cookieValue : Cookie的內容
	 * @param cookieMaxAge : Cookie存活時間(單位:秒)
	 * @param contextPath : web應用的contextPath
	 * @return cookie
	 */
	public static Cookie createCookie(String cookieName, String cookieValue,int cookieMaxAge,String contextPath)
	{
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(30 * 60 * 60);
		cookie.setPath(contextPath);
		return cookie;
	}

}
