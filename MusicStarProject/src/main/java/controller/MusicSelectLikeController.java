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
public class MusicSelectLikeController {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private LikeMusicService likeMusicService;
	
	@RequestMapping(path={"/music/musicSelectLike"},method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String InsertLike(LikeMusicBean bean, Integer likes_music_id, Model model, HttpSession session) {
		Map<String,String> data = new HashMap<>();
				
		MemberBean mb =(MemberBean) session.getAttribute("loginOK");
		if(mb!=null) {
			String MbrId =mb.getMbrId();
			LikeMusicBean newbean = new LikeMusicBean(MbrId,bean.getLikes_music_id());
			if(likeMusicService.insertLike(newbean)) {
				data.put("success", "謝謝您支持這位歌手");
			}else {
				data.put("error", "您已經按過讚了");
			}
		}else {
			data.put("mustlogin", "必須登入才可以按讚喔~");
		}						
		return Parser.toJson(data);
	}
	
	@RequestMapping(path={"/music/musicSelectCount"},method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String SelectLikeCount(LikeMusicBean bean, Integer likes_music_id, Model model, HttpSession session) {
		Map<String,String> errors = new HashMap<>();
		model.addAttribute("errors",errors);
		
		Gson gson = new Gson();
		List<Map> beanList = likeMusicService.selectLikeCount(bean);
		if(beanList==null) {
			errors.put("action", "UnKnow Action");
			return "likemusic UnKnow";
		}else {
			//new一個新的Map map
			Map<String,String> beanMap = new HashMap<>();
			//利用for迴圈印出beanList中每一筆資料 "方式:for(小的Map:大的List)"
			for(Map map : beanList) {
				//在新的Map map中放入(key, value)=>(從小的Map中取出的likes_music_id,與count值)
				beanMap.put(map.get("likes_music_id").toString(), map.get("count").toString());
			}		
			String jsonString = gson.toJson(beanMap);
			return jsonString;
		}
	}
}
