package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import _global.utils.Parser;
import model.bean.MusicListBean;
import model.bean.MusicListContentBean;
import model.service.MusicListContentService;

@Controller
public class MusiclistContentController {

	@Autowired
	private MusicListContentService musicListContentService;

	@RequestMapping(path={"/musiclistiditem.controller"},method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String method(MusicListContentBean bean, Model model) {	
		Map<String,String> errors = new HashMap<>();
		model.addAttribute("errors",errors);
		
		Gson gson = new Gson();
		List<MusicListContentBean> beanList = musicListContentService.selectmusiclistiditem(bean);
		if(beanList==null) {
			errors.put("action", "UnKnow Action");
			return "xxx.UnKnow";
		}else {
			model.addAttribute("data",beanList);						
			String jsonString =gson.toJson(beanList);				
			return jsonString;							
		}						
	}
	
	// 刪除我的歌單內的音樂  謙0314
		@RequestMapping(path = { "/deletemymusiclistmusic" }, method = { RequestMethod.GET,
				RequestMethod.POST }, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public String method1(MusicListContentBean bean, Model model) {
			Map<String, String> data = new HashMap<>();
			boolean result = musicListContentService.delete2(bean);
			if (!result) {
				data.put("fail", "刪除失敗");
			} else {
				data.put("ok", "刪除成功");
			}
			return Parser.toJson(data);
		}
	
}