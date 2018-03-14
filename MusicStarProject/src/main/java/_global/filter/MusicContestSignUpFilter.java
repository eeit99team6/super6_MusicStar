//package _global.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import model.bean.MemberBean;
//
//@WebFilter(urlPatterns= {"/pages/contestSignUp.jsp"})
//public class MusicContestSignUpFilter implements Filter
//{
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
//			throws IOException, ServletException
//	{
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) resp;
//	
//		System.out.println("我進來MusicContestFilter");
//		HttpSession session = request.getSession();
//		MemberBean loginToken = (MemberBean) session.getAttribute("loginOK");
//		if (loginToken == null)
//		{
//			// 沒有login GG
//			System.out.println("Filter: loginNotok..");
//			String contentPath = request.getContextPath();
//			response.sendRedirect(contentPath+"/index.jsp");
//
//		} else
//		{
//			System.out.println("Filter: loginOk :)");
//			chain.doFilter(request, response);
//		}
//
//	}
//
//	FilterConfig filterCongig;
//
//	@Override
//	public void init(FilterConfig filterCongig) throws ServletException
//	{
//		this.filterCongig = filterCongig;
//	}
//	
//
//	@Override
//	public void destroy()
//	{
//		// TODO Auto-generated method stub
//
//	}
//
//}
