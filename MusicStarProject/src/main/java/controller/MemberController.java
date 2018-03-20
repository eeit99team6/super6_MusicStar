package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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

	/**
	 * @author Phil 2018.03.15
	 */
	@RequestMapping(path = "/members/show", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String show() {
		return Parser.toJson(memberService.search(null));
	}

	/**
	 * @author Phil 2018.03.15
	 */
	@RequestMapping(path = "/members/registerAjax", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String registerAjax(HttpSession session,
			@RequestPart(value = "mbrProfile", required = false) MultipartFile mbrProfile, MemberBean bean,
			BindingResult result, @RequestHeader(value = "referer", required = false) String referer) {
		Map<String, Map<String, String>> data = new HashMap<>();
		Map<String, String> error = new HashMap<>();
		data.put("error", error);

		String mbrId = bean.getMbrId();
		if (!Checker.notEmpty(mbrId)) {
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

		String fileName = null;
		String contentType = mbrProfile.getContentType();
		if (!mbrProfile.isEmpty()) {
			if (contentType.indexOf("image") == -1) {
				error.put("profileError", "上傳必須為圖檔");
			}else {
				fileName = mbrId + "." + contentType.split("/")[1];				
				bean.setMbrPhoto(Constant.profilesDirectory + fileName);				
			}
		}

		if (!Checker.notEmpty(error)) {
			if (memberService.register(bean)) {
				try {
					if(fileName != null) {
						mbrProfile.transferTo(new File(profilesDirectoryPath + fileName));						
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
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					error.put("idError", "註冊失敗!");
				}
			} else {
				error.put("idError", "帳號重複!");
			}
		}

		return Parser.toJson(data);
	}

	/**
	 * @author Phil 2018.03.15
	 */
	@RequestMapping(path = "/members/loginAjax", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String loginAjax(HttpSession session, HttpServletResponse response, MemberBean bean, BindingResult result,
			String rememberme, @RequestHeader(value = "referer", required = false) String referer) {

		String contextPath = servletContext.getContextPath();
		Map<String, String> data = new HashMap<>();
		MemberBean mb = null;

		if ((mb = memberService.login(bean)) != null) {
			session.setAttribute("loginOK", mb);
			Cookie cookieUser = null;
			Cookie cookiePassword = null;
			Cookie cookieRememberMe = null;
			Cookie cookieAutoLogin = null;
			if (rememberme != null) {
				String user = bean.getMbrId();
				String pwd = bean.getMbrPwd();
				cookieUser = Processor.createCookie("user", user, 30 * 60 * 60, contextPath);
				cookiePassword = Processor.createCookie("password", Parser.encryptString(pwd), 30 * 60 * 60,
						contextPath);
				cookieRememberMe = Processor.createCookie("rm", "enabled", 30 * 60 * 60, contextPath);
				cookieAutoLogin = Processor.createCookie("autologin", "enabled", 30 * 60 * 60, contextPath);
				session.setAttribute("rememberMe", "enabled");
				session.setAttribute("rmUser", user);
				session.setAttribute("rmPassword", pwd);
			} else {
				// MaxAge==0 表示要請瀏覽器刪除此Cookie
				cookieUser = Processor.createCookie("user", "", 0, contextPath);
				cookiePassword = Processor.createCookie("password", "", 0, contextPath);
				cookieRememberMe = Processor.createCookie("rm", "disabled", 0, contextPath);
				cookieAutoLogin = Processor.createCookie("autologin", "disabled", 0, contextPath);
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

	/**
	 * @author Phil 2018.03.15
	 */
	@RequestMapping(path = "/members/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletResponse response, HttpSession session, MemberBean bean) {
		String contextPath = servletContext.getContextPath();
		session.removeAttribute("loginOK");
		Cookie cookieAutoLogin = Processor.createCookie("autologin", "disabled", 0, contextPath);
		response.addCookie(cookieAutoLogin);
		return "r.index";
	}

	/**
	 * @author Phil 2018.03.15
	 */
	@RequestMapping(path = "/members/googleLoginAjax", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String googleLoginAjax(HttpSession session, HttpServletResponse response, String idToken,
			@RequestHeader(value = "referer", required = false) String referer) {
		Map<String, String> data = new HashMap<>();

		GoogleIdToken googleIdToken = Processor.verifyGoogleIdToken(idToken);

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
				String mbrId = bean.getMbrId();
				File coverDir = new File(coverDirectoryPath + mbrId);
				if (!coverDir.exists()) {
					coverDir.mkdir();
				}
				File audioDir = new File(audiosDirectoryPath + mbrId);
				if (!audioDir.exists()) {
					audioDir.mkdir();
				}
				session.setAttribute("loginOK", bean);
				data.put("success", referer);
			}
		} else {
			data.put("errMsg", "登入失敗!!");
		}

		return Parser.toJson(data);
	}

	/**
	 * @author Phil 2018.03.15
	 */
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
				String mbrId = bean.getMbrId();
				File coverDir = new File(coverDirectoryPath + mbrId);
				if (!coverDir.exists()) {
					coverDir.mkdir();
				}
				File audioDir = new File(audiosDirectoryPath + mbrId);
				if (!audioDir.exists()) {
					audioDir.mkdir();
				}
				session.setAttribute("loginOK", bean);
				data.put("success", referer);
			}
		} else {
			data.put("errMsg", "登入失敗!!(" + error.getMessage() + ")");
		}

		return Parser.toJson(data);
	}

	@RequestMapping(path = "/members/update/name", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateMemberName(HttpSession session, String newName) {
		Map<String, String> data = new HashMap<>();
		MemberBean member =  (MemberBean) session.getAttribute("loginOK");
		if(member != null) {
			if (Checker.notEmpty(newName))
			{
				member.setMbrName(newName);
				memberService.update(member);
				data.put("success", "修改成功!");
			} else
			{
				data.put("errMsg", "名稱不可為空白!");
			}
		}else {
			data.put("errMsg", "會員未登入!");
		}
		return Parser.toJson(data);		
	}
	@RequestMapping(path = "/members/update/photo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateMemberPhoto(HttpSession session, @RequestPart(name="newPhoto") MultipartFile newPhoto) {
		Map<String, String> data = new HashMap<>();
		MemberBean member =  (MemberBean) session.getAttribute("loginOK");
		if(member != null) {
			if (!newPhoto.isEmpty())
			{
				String contentType = newPhoto.getContentType();
				if(contentType.indexOf("image") == 0) {
					String fileName = member.getMbrId() + "." + contentType.split("/")[1];
					try
					{
						newPhoto.transferTo(new File(profilesDirectoryPath + fileName));
					} catch (IllegalStateException | IOException e)
					{
						e.printStackTrace();
						data.put("errMsg", "圖片上傳失敗!");
						return Parser.toJson(data);	
					}	
					member.setMbrPhoto(Constant.profilesDirectory + fileName);
					memberService.update(member);
					data.put("success", "修改成功!");
				}else {					
					data.put("errMsg", "圖片格式不正確!");
				}
			} else
			{
				data.put("errMsg", "未上傳圖片!");
			}
		}else {
			data.put("errMsg", "會員未登入!");
		}
		return Parser.toJson(data);		
	}
	@RequestMapping(path = "/members/update/gender", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateMemberGender(HttpSession session,String newGender) {
		Map<String, String> data = new HashMap<>();
		MemberBean member =  (MemberBean) session.getAttribute("loginOK");
		if(member != null) {
			if (Checker.notEmpty(newGender))
			{
				member.setMbrGender(newGender.charAt(0));
				memberService.update(member);
				data.put("success", "修改成功!");
			} else
			{
				data.put("errMsg", "性別選擇錯誤!");
			}
		}else {
			data.put("errMsg", "會員未登入!");
		}
		return Parser.toJson(data);		
	}
	@RequestMapping(path = "/members/update/pwd", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateMemberPwd(HttpSession session, HttpServletResponse response, String mbrOldPwd, String mbrNewPwd) {
		Map<String, String> data = new HashMap<>();
		MemberBean member =  (MemberBean) session.getAttribute("loginOK");
		if(member != null) {
			if(member.getMbrPwd() == null || member.getMbrPwd().equals(mbrOldPwd)) {
				if(Checker.notEmpty(mbrNewPwd)) {					
					member.setMbrPwd(mbrNewPwd);
					memberService.update(member);
					session.setAttribute("rmPassword",mbrNewPwd);
					response.addCookie(Processor.createCookie("password", Parser.encryptString(mbrNewPwd), 30 * 60 * 60, servletContext.getContextPath()));
					data.put("success", "修改成功!");
				}else {					
					data.put("newPwdError", "新密碼不可為空白!");
				}
			}else {
				data.put("oldPwdError", "原密碼不正確!");				
			}
		}else {
			data.put("errMsg", "會員未登入!");
		}
			
		return Parser.toJson(data);		
	}
	
	@RequestMapping(path = "/members/forgetPwdAjax", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String forgetPwdAjax(HttpSession session, HttpServletRequest request, String pwdForgetId, String pwdForgetEmail) {
		Map<String, String> data = new HashMap<>();
		
		if(!Checker.notEmpty(pwdForgetId)) {
			data.put("idError", "帳號不可為空白");
		}
		if(!Checker.notEmpty(pwdForgetEmail)) {
			data.put("emailError", "電子郵件不可為空白");
		}
		if(!Checker.notEmpty(data)) {
			String link = Parser.encryptString(pwdForgetId + System.currentTimeMillis() + pwdForgetEmail);
			MemberBean mb = memberService.setMbrPwdResetLink(pwdForgetId, pwdForgetEmail, link);
			if(mb != null) {
			String contextPath = servletContext.getContextPath();
			String path = request.getRequestURL().toString().split(contextPath)[0] + contextPath ;
			String name = mb.getMbrName();	
			StringBuffer htmlDoc = new StringBuffer();
			htmlDoc.append("<h2>");
			htmlDoc.append("重置密碼:");
			htmlDoc.append("</h2>");
			htmlDoc.append("<h3>");
			htmlDoc.append(name);		
			htmlDoc.append(" 您好，請於24小時內點選以下連結以重置密碼:");		
			htmlDoc.append("</h3>");
			htmlDoc.append("<h5>");
			htmlDoc.append("連結:");		
			htmlDoc.append("<a rel='noopener noreferrer' title='點我重置密碼' target='_blank' href='"); 	
			htmlDoc.append(path);		
			htmlDoc.append("/security/resetPassord?link=");		
			htmlDoc.append(link);		
			htmlDoc.append("'>請點我重置密碼</a>");
			htmlDoc.append("</h5>");
			htmlDoc.append("<h3>");
			htmlDoc.append("若您未要求要重置密碼，請忽略本信。");		
			htmlDoc.append("</h3>");
			Processor.sendHtmlMail(pwdForgetEmail, "重置 MusicStar帳戶密碼", htmlDoc.toString());	
			}
			data.put("success", "如果  "+ pwdForgetId+"－"+ pwdForgetEmail +" 已有 MusicStar帳戶，系統將以電子郵件傳送進一步的操作指示。");
		}	
		return Parser.toJson(data);		
	}
	
	@RequestMapping(path = "/security/resetPassord", method = RequestMethod.GET)
	public String resetpassord(String link, HttpSession session) {			
		if(link != null) {
			MemberBean mb = memberService.checkResetPwdLink(link);
			if(mb != null) {
				session.setAttribute("forgetPwdMbr", mb);
				return "f.resetPwd";
			}
		}
		return "r.index";
	}
	
	@RequestMapping(path = "/security/resetPwdAjax", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String resetPwdAjax(HttpSession session, String newPwd, String checkNewPwd) {
		Map<String, String> data = new HashMap<>();
		MemberBean mb = (MemberBean) session.getAttribute("forgetPwdMbr");
		if(mb != null) {
			if(!Checker.notEmpty(newPwd)) {
				data.put("newPwdError", "新密碼不可為空白");
			}
			if(!Checker.notEmpty(checkNewPwd)) {
				data.put("checkNewPwdError", "必須確認新密碼");
			}else if(!checkNewPwd.equals(newPwd)) {
				data.put("checkNewPwdError", "密碼不相符");
			}
			if(!Checker.notEmpty(data)) {
				mb.setMbrPwd(newPwd);
				mb.setMbrPwdResetLink(null);
				mb.setMbrPwdResetLimit(null);
				if(memberService.update(mb)) {
					session.removeAttribute("forgetPwdMbr");
					data.put("success", "密碼修改成功");
				}
			}
		}else {
			data.put("errMsg", "無法修改密碼!!請依照忘記密碼流程進行密碼重設作業!!!");
		}
		return Parser.toJson(data);
	}
}
