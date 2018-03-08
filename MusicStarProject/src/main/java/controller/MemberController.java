package controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import _global.utils.Checker;
import _global.utils.Constant;
import _global.utils.Parser;
import _global.utils.Processor;
import _global.utils.Producer;
import json.fb.FaceBook;
import model.bean.MemberBean;
import model.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	MemberService memberService;
	@Autowired
	String profilesDirectoryPath;
	@Autowired
	String coverDirectoryPath;
	@Autowired
	String audiosDirectoryPath;
	
	@RequestMapping(path = "/members/show", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String show() {
		return Parser.toJson(memberService.search(null));
	}

	@RequestMapping(path = "/members/registerAjax", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String registerAjax(HttpSession session,
			@RequestPart(value = "mbrProfile", required = false) MultipartFile mbrProfile, MemberBean bean,
			BindingResult result, @RequestHeader(value = "referer", required = false) String referer) {
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
		if (!Checker.notEmpty(gneder)
				|| !(Checker.notEmpty(gneder) && (gneder.toString().equals("M") || gneder.toString().equals("F")))) {
			error.put("genderError", "性別必須選擇");
		}

		String contentType = mbrProfile.getContentType();
		if (!mbrProfile.isEmpty()) {
			if (contentType.indexOf("image") == -1) {
				error.put("profileError", "上傳必須為圖檔");
			} 
		}

		if (!Checker.notEmpty(error)) {

			if (memberService.register(bean)) {
				String mbrId = bean.getMbrId();
				String fileName = mbrId + "." + contentType.split("/")[1];
				try {
					mbrProfile.transferTo(new File(profilesDirectoryPath + fileName));
					bean.setMbrPhoto(Constant.profilesDirectory + fileName);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}				
				File coverDir = new File(coverDirectoryPath + mbrId);	
				if (!coverDir.exists()) {
					coverDir.mkdir();
				}				
				File audioDir = new File(audiosDirectoryPath + mbrId);	
				if (!audioDir.exists()) {
					audioDir.mkdir();
				}
				
				Map<String, String> success = new HashMap<>();
				data.put("success", success);
				success.put("message", "註冊成功!");
				success.put("referer", referer);
			} else {
				error.put("idError", "帳號重複!");
			}
		}

		return Parser.toJson(data);
	}

	@RequestMapping(path = "/members/loginAjax", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String loginAjax(HttpSession session, HttpServletResponse response, MemberBean bean, BindingResult result,
			String rememberme, @RequestHeader(value = "referer", required = false) String referer) {

		String contextPath = servletContext.getContextPath();
		Map<String, String> data = new HashMap<>();
		MemberBean mb = null;

		if ((mb = memberService.login(bean)) != null) {
			session.setAttribute("loginOK", mb);
			String mbrPhoto = mb.getMbrPhoto();
			if (mbrPhoto != null) {
				session.setAttribute("mbrProfile", mbrPhoto);
			}
			Cookie cookieUser = null;
			Cookie cookiePassword = null;
			Cookie cookieRememberMe = null;
			Cookie cookieAutoLogin = null;
			if (rememberme != null) {
				String user = bean.getMbrId();
				String pwd = bean.getMbrPwd();
				cookieUser = Producer.createCookie("user", user, 30 * 60 * 60, contextPath);
				cookiePassword = Producer.createCookie("password", Parser.encryptString(pwd), 30 * 60 * 60,
						contextPath);
				cookieRememberMe = Producer.createCookie("rm", "enabled", 30 * 60 * 60, contextPath);
				cookieAutoLogin = Producer.createCookie("autologin", "enabled", 30 * 60 * 60, contextPath);
				session.setAttribute("rememberMe", "enabled");
				session.setAttribute("rmUser", user);
				session.setAttribute("rmPassword", pwd);
			} else {
				// MaxAge==0 表示要請瀏覽器刪除此Cookie
				cookieUser = Producer.createCookie("user", "", 0, contextPath);
				cookiePassword = Producer.createCookie("password", "", 0, contextPath);
				cookieRememberMe = Producer.createCookie("rm", "disabled", 0, contextPath);
				cookieAutoLogin = Producer.createCookie("autologin", "disabled", 0, contextPath);
				session.setAttribute("rememberMe", "disabled");
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
	public String logout(Model model, HttpServletResponse response, HttpSession session, MemberBean bean) {
		String contextPath = servletContext.getContextPath();
		session.removeAttribute("loginOK");
		session.removeAttribute("mbrProfile");
		Cookie cookieAutoLogin = Producer.createCookie("autologin", "disabled", 0, contextPath);
		response.addCookie(cookieAutoLogin);
		return "r.index";
	}

	@RequestMapping(path = "/members/googleLoginAjax", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String googleLoginAjax(HttpSession session, HttpServletResponse response, String idToken,
			@RequestHeader(value = "referer", required = false) String referer) {
		Map<String, String> data = new HashMap<>();

		GoogleIdToken googleIdToken = Checker.verifyGoogleIdToken(idToken);

		if (googleIdToken != null) {
			Payload payload = googleIdToken.getPayload();

			// Get profile information from payload
			String name = (String) payload.get("name");
			String email = payload.getEmail();
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			String pictureUrl = (String) payload.get("picture");
			String googleId = payload.getSubject();

			// Use or store profile information
			MemberBean bean = new MemberBean();
			bean.setMbrGoogleId(googleId);

			MemberBean mb = null;
			if ((mb = memberService.googleLogin(bean)) != null) {
				session.setAttribute("loginOK", mb);
				String mbrPhoto = mb.getMbrPhoto();
				if (mbrPhoto != null) {
					session.setAttribute("mbrProfile", mb.getMbrPhoto());
				}
				data.put("success", referer);
			} else {
				bean.setMbrId(email.split("@")[0]);
				bean.setMbrName(name);
				bean.setMbrEmail(email);
				bean.setMbrEmailVerify(emailVerified);
				bean.setMbrPhoto(pictureUrl);
				int i = 2;

				while (!memberService.register(bean)) {
					bean.setMbrId(bean.getMbrId() + "_" + i++);
				}
				session.setAttribute("loginOK", bean);
				session.setAttribute("mbrProfile", pictureUrl);
				data.put("success", referer);
			}
		} else {
			data.put("errMsg", "登入失敗!!");
		}

		return Parser.toJson(data);
	}

	@RequestMapping(path = "/members/fbLoginAjax", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String fbLoginAjax(HttpSession session, HttpServletResponse response, String accessToken,
			@RequestHeader(value = "referer", required = false) String referer) {
		Map<String, String> data = new HashMap<>();

		FaceBook fbAccessToken = Processor.runHttpGet("https://graph.facebook.com/v2.12/me", FaceBook.class,
				"fields=id,name,email,picture", "access_token=" + accessToken);
		
		json.fb.Error error = fbAccessToken.getError();
		if (error == null) {

			// Get profile information from fbAccessToken
			String name = fbAccessToken.getName();
			String email = fbAccessToken.getEmail();
			boolean emailVerified = email != null;
			String pictureUrl = fbAccessToken.getPicture().getData().getUrl();
			String fbId = fbAccessToken.getId();

			// Use or store profile information
			MemberBean bean = new MemberBean();
			bean.setMbrFbId(fbId);

			MemberBean mb = null;
			if ((mb = memberService.fbLogin(bean)) != null) {
				session.setAttribute("loginOK", mb);
				String mbrPhoto = mb.getMbrPhoto();
				if (mbrPhoto != null) {
					session.setAttribute("mbrProfile", mbrPhoto);
				}
				data.put("success", referer);
			} else {
				if (Checker.notEmpty(email)) {
					bean.setMbrId(email.split("@")[0]);
				} else {
					bean.setMbrId(name);
				}
				bean.setMbrName(name);
				bean.setMbrEmail(email);
				bean.setMbrEmailVerify(emailVerified);
				bean.setMbrPhoto(pictureUrl);
				int i = 2;

				while (!memberService.register(bean)) {
					bean.setMbrId(bean.getMbrId() + "_" + i++);
				}
				session.setAttribute("loginOK", bean);
				session.setAttribute("mbrProfile", pictureUrl);
				data.put("success", referer);
			}
		} else {
			data.put("errMsg", "登入失敗!!(" + error.getMessage() + ")");
		}

		return Parser.toJson(data);
	}

}
