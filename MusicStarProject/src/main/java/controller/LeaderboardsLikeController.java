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

import _global.utils.Parser;
import model.bean.LikeMusicBean;
import model.bean.MemberBean;
import model.service.LikeMusicService;

@Controller
public class LeaderboardsLikeController {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private LikeMusicService likeMusicService;
	
	@RequestMapping(path={"/likeleaderboards.controller"},method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String method(LikeMusicBean bean, Model model) {
		Map<String,String> errors = new HashMap<>();
		model.addAttribute("errors",errors);
		
		Gson gson = new Gson();
		List<LikeMusicBean> beanList = likeMusicService.selectTotalLike(bean);
		if(beanList==null) {
			errors.put("action", "UnKnow Action");
			return "likemusic UnKnow";
		}else {
			model.addAttribute("data",beanList);
			String jsonString =gson.toJson(beanList);
			return jsonString;
		}
		
	}

		
	@RequestMapping(path={"/likeleaderboards.like.controller"},method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String InsertLike(LikeMusicBean bean, Integer likes_music_id, Model model, HttpSession session) {
		Map<String,String> data = new HashMap<>();
				
		MemberBean mb =(MemberBean) session.getAttribute("loginOK");
		if(mb!=null) {
			String xxx =mb.getMbrId();
			LikeMusicBean newbean = new LikeMusicBean(xxx,bean.getLikes_music_id());
			if(likeMusicService.insertLike(newbean)) {
				data.put("success", "謝謝您支持這位歌手");
			}else {				
				data.put("error", "你已經按過讚了");
			}
		}else {
			data.put("mustlogin", "必須登入才可以按讚喔~");
		}						
		return Parser.toJson(data);				
	}
	
	
	
	
	
}
