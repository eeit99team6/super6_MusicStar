package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import _global.utils.Checker;
import _global.utils.Parser;
import _global.utils.Producer;
import model.bean.MemberBean;
import model.service.MemberService;

@Controller
public class MemberController
{
	@Autowired
	ServletContext servletContext;
	@Autowired
	MemberService memberService;

	@RequestMapping(path = "/members/registerAjax", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String registerAjax(HttpSession session, MemberBean bean, BindingResult result, @RequestHeader(value = "referer", required = false) String referer)
	{
		Map<String, Map<String, String>> data = new HashMap<>();
		Map<String, String> error = new HashMap<>();
		data.put("error", error);

		if (!Checker.notEmpty(bean.getMbrId())) {
			error.put("idError", "帳號必須填寫");
		}
		if (!Checker.notEmpty(bean.getMbrName())) {
			error.put("nameError", "姓名必須填寫");
		}
		if (!Checker.notEmpty(bean.getMbrPwd())) {
			error.put("pwdError", "密碼必須填寫");
		}
		if (!Checker.notEmpty(bean.getMbrEmail())) {
			error.put("emailError", "電子郵件必須填寫");
		}
		Character gneder = bean.getMbrGender();
		System.out.println("gneder = " + gneder);
		if (!Checker.notEmpty(gneder) || !(Checker.notEmpty(gneder) && (gneder.toString().equals("M") || gneder.toString().equals("F")))) {
			error.put("genderError", "性別必須選擇");
		}

		if (!Checker.notEmpty(error)) {

			if (memberService.register(bean)) {
				Map<String, String> success = new HashMap<>();
				data.put("success", success);
				success.put("message", "註冊成功!");
				success.put("referer", referer);
			} else {
				error.put("idError", "帳號重複!");
				error.put("message", "註冊失敗!");
			}
		}

		return Parser.toJson(data);
	}

	@RequestMapping(path = "/members/loginAjax", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String loginAjax(HttpSession session, HttpServletResponse response, MemberBean bean, BindingResult result, String rememberme, @RequestHeader(value = "referer", required = false) String referer)
	{

		String contextPath = servletContext.getContextPath();
		Map<String, String> data = new HashMap<>();
		MemberBean mb = null;

		if ((mb = memberService.login(bean)) != null) {
			session.setAttribute("loginOK", mb);
			session.removeAttribute("rememberMe");
			Cookie cookieUser = null;
			Cookie cookiePassword = null;
			Cookie cookieRememberMe = null;
			Cookie cookieAutoLogin = null;
			if (rememberme != null) {
				cookieUser = Producer.createCookie("user", bean.getMbrId(), 30 * 60 * 60, contextPath);
				cookiePassword = Producer.createCookie("password", Parser.encryptString(bean.getMbrPwd()), 30 * 60 * 60, contextPath);
				cookieRememberMe = Producer.createCookie("rm", "enabled", 30 * 60 * 60, contextPath);
				cookieAutoLogin = Producer.createCookie("autologin", "enabled", 30 * 60 * 60, contextPath);
			} else {
				// MaxAge==0 表示要請瀏覽器刪除此Cookie
				cookieUser = Producer.createCookie("user", "", 0, contextPath);
				cookiePassword = Producer.createCookie("password", "", 0, contextPath);
				cookieRememberMe = Producer.createCookie("rm", "disabled", 0, contextPath);
				cookieAutoLogin = Producer.createCookie("autologin", "disabled", 0, contextPath);
				session.removeAttribute("rmUser");
				session.removeAttribute("rmPassword");
			}
			response.addCookie(cookieUser);
			response.addCookie(cookiePassword);
			response.addCookie(cookieRememberMe);
			response.addCookie(cookieAutoLogin);

			data.put("success", referer);
			return Parser.toJson(data);
		} else {
			data.put("errMsg", "帳號或密碼錯誤");
			return Parser.toJson(data);
		}

	}

	@RequestMapping(path = "/members/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletResponse response, HttpSession session, MemberBean bean)
	{
		String contextPath = servletContext.getContextPath();
		session.removeAttribute("loginOK");
		Cookie cookieAutoLogin = Producer.createCookie("autologin", "disabled", 0, contextPath);
		response.addCookie(cookieAutoLogin);
		return "r.index";
	}

}
