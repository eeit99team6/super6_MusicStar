package _global.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.EmployeeBean;

@WebFilter(urlPatterns= {"/backend/*"})
public class EmployeeLoginFilter implements Filter
{
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		
		
		System.out.println("我進來Filter");
		HttpSession session = request.getSession();
		EmployeeBean loginToken = (EmployeeBean) session.getAttribute("backEndloginOK");
		if (loginToken == null)
		{
			// 沒有login GG
			System.out.println("Filter: loginNotok..");
			String contentPath = request.getContextPath();
			response.sendRedirect(contentPath+"/backEndlogin.jsp");

		} else
		{
			System.out.println("Filter: loginOk :)");
			chain.doFilter(request, response);
		}

	}

	FilterConfig filterCongig;

	@Override
	public void init(FilterConfig filterCongig) throws ServletException
	{
		this.filterCongig = filterCongig;
	}
	

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

}
