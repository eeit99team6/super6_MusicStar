package _global.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.WebApplicationContextUtils;

import _global.utils.Checker;
import _global.utils.Parser;
import model.bean.MemberBean;
import model.service.MemberService;

@WebFilter("/*")
public class RememberMeFilter implements Filter
{

	public void init(FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}

	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();

		String rememberMe = (String) session.getAttribute("rememberMe");
		if (rememberMe == null) {
			session.setAttribute("rememberMe", "disabled");
			Cookie[] cookies = request.getCookies();
			String cookieName;
			if (cookies != null) {
				String user = null;
				String password = null;
				String autoLogin = null;
				for (Cookie cookie : cookies) {
					cookieName = cookie.getName();
					switch (cookieName)
					{
					case "user":
						user = cookie.getValue();
						session.setAttribute("rmUser", user);
						break;
					case "password":
						password = Parser.decryptString(cookie.getValue());
						session.setAttribute("rmPassword", password);
						break;
					case "rm":
						rememberMe = cookie.getValue();
						session.setAttribute("rememberMe", rememberMe);
						break;
					case "autologin":
						autoLogin = cookie.getValue();
						session.setAttribute("autoLogin", autoLogin);
						break;
					}
				}
				if (Checker.notEmpty(autoLogin) && autoLogin.equals("enabled") && Checker.notEmpty(user) && Checker.notEmpty(password)) {
					MemberService memberService = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getBean(MemberService.class);
					MemberBean bean = new MemberBean();
					bean.setMbrId(user);
					bean.setMbrPwd(password);
					if ((bean = memberService.login(bean)) != null) {
						session.setAttribute("loginOK", bean);
					}
				}
			}

		}
		chain.doFilter(request, response);
	}

}
