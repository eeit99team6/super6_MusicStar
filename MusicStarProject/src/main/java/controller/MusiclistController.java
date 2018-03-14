package controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import _global.utils.Parser;
import model.bean.MemberBean;
import model.bean.MusicListBean;
import model.bean.MusicListContentBean;
import model.service.MusicListService;


@Controller
public class MusiclistController {
		
	@Autowired
	private MusicListService musicListService = null;
	
	@Autowired
	private ApplicationContext context;
	
	@RequestMapping(
			path={"/pages/product.controller"}
	)
	public String method(String prodaction, MusicListBean bean,BindingResult bindingResult, Model model,
			@RequestParam("member_music_list_id")	String temp1) {
		
		Locale locale = LocaleContextHolder.getLocale();
//接收資料
		Map<String, String> errors = new HashMap<>();
		model.addAttribute("errors", errors);
		
//轉換資料
		if(bindingResult!=null && bindingResult.hasErrors()) {
			if(bindingResult.getFieldErrorCount("id")!=0) {
				errors.put("xxx1", context.getMessage("product.id.format", null, locale));
			}
			if(bindingResult.getFieldErrorCount("price")!=0) {
				errors.put("xxx2", context.getMessage("product.price.format", null, locale));
			}
			if(bindingResult.getFieldErrorCount("make")!=0) {
				errors.put("xxx3", context.getMessage("product.make.format", null, locale));
			}
			if(bindingResult.getFieldErrorCount("expire")!=0) {
				errors.put("xxx4", context.getMessage("product.expire.format", null, locale));
			}
		}
		
		
//驗證資料
//		if("Insert".equals(prodaction) || "Update".equals(prodaction) || "Delete".equals(prodaction)) {
//			if(temp1==null || temp1.length()==0) {
//				errors.put("xxx1", context.getMessage("product.id.required", new String[] {prodaction}, locale));
//			}
//		}
//		
//		if(errors!=null && !errors.isEmpty()) {
//			return "product.error";
//		}
//		
//呼叫model
//根據model執行結果呼叫view元件
		if("Select".equals(prodaction)) {
			List<MusicListBean> result = musicListService.select(bean);
			model.addAttribute("select", result);
			return "product.select";
			
		} else if("Insert".equals(prodaction)) {
			MusicListBean result = musicListService.insert(bean);
			if(result==null) {
				errors.put("action", "Insert fail");
			} else {
				model.addAttribute("insert", result);
			}
			return "product.error";
			
		} else if("Update".equals(prodaction)) {
			MusicListBean result = musicListService.update(bean);
			if(result==null) {
				errors.put("action", "Update fail");
			} else {
				model.addAttribute("update", result);
			}
			return "product.error";
			
		} else if("Delete".equals(prodaction)) {
			boolean result = musicListService.delete(bean);
			if(!result) {
				model.addAttribute("delete", 0);
			} else {
				model.addAttribute("delete", 1);
			}
			return "product.error";			
		} else {
			errors.put("action", "Unknown Action:"+prodaction);
			return "product.error";
		}
	}
	@RequestMapping(value="/pages/musiclists", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
	public String musiclistAjax() {
		return Parser.toJson(musicListService.select());
	}
	//0313 抓自己歌單之controller 謙
	
	@RequestMapping(path={"/mymusiclistidid.controller"},method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String method(HttpSession session) {	
		Map<String,String> errors = new HashMap<>();
		MemberBean mb =(MemberBean) session.getAttribute("loginOK");			
		
		if(mb!=null) 
		{
			String beanId = mb.getMbrId();
			List<MusicListBean> beanList = musicListService.selectmemid(beanId);
			if (beanList == null) {
				errors.put("action", "UnKnow Action");
				return Parser.toJson(errors);
			} else {
//				model.addAttribute("data", beanList);
				return Parser.toJson(beanList);
			}				
		}else {
			errors.put("mustLogin", "必須登入");
			return Parser.toJson(errors);
		}
	}
}