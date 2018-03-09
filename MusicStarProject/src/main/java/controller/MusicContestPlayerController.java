package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import model.bean.MusicBean;
import model.bean.MusicContestBean;
import model.bean.MusicContestPlayerBean;
import model.service.MusicContestPlayerService;

@Controller
public class MusicContestPlayerController {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private MusicContestPlayerService musicContestPlayerService;
	
	@RequestMapping(path={"/leaderboards.controller"},method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String method(MusicContestPlayerBean bean, Model model) {	
		Map<String,String> errors = new HashMap<>();
		model.addAttribute("errors",errors);
		
		Gson gson = new Gson();
		List<MusicContestPlayerBean> beanList = musicContestPlayerService.selectContestIdTop3(bean);
		if(beanList==null) {
			errors.put("action", "UnKnow Action");
			return "leaderboards.UnKnow";
		}else {
			model.addAttribute("data",beanList);						
			String jsonString =gson.toJson(beanList);				
			return jsonString;							
		}						
	}
	
	@RequestMapping(path={"/leaderboards.count.controller"},method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String methodCount(MusicContestBean bean, Model model) {	
		Map<String,String> errors = new HashMap<>();
		model.addAttribute("errors",errors);
		
		Gson gson = new Gson();
		List<MusicContestBean> beanList = musicContestPlayerService.selectContestIdCount(bean);
		if(beanList==null) {
			errors.put("action", "UnKnow Action");
			return "leaderboards.UnKnow";
		}else {
			model.addAttribute("datacount",beanList);						
			String jsonCount=gson.toJson(beanList);				
			return jsonCount;							
		}						
	}
	
	@RequestMapping(path={"/leaderboards-1.controller"},method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String method2(MusicContestPlayerBean bean, Model model) {	
		Map<String,String> errors = new HashMap<>();
		model.addAttribute("errors",errors);
		
		Gson gson = new Gson();
		List<MusicContestPlayerBean> beanList = musicContestPlayerService.selectContestIdTop10(bean);
		if(beanList==null) {
			errors.put("action", "UnKnow Action");
			return "leaderboards.UnKnow";
		}else {
			model.addAttribute("data",beanList);						
			String jsonString =gson.toJson(beanList);				
			return jsonString;							
		}						
	}
	
	
}
