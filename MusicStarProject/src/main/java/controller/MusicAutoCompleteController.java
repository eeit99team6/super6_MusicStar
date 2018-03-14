package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.bean.MusicBean;
import model.service.MusicService;

/**
 * @author Yeh
 */

@Controller
public class MusicAutoCompleteController {
	
	@Autowired
	private MusicService service;
	
	@RequestMapping(
		path={"/music/autoComplete"},
		method={RequestMethod.GET, RequestMethod.POST}
	)
	protected String method(String keyword,Model model,HttpSession session){
		Map<String,String> errors = new HashMap<String,String>();
		List<MusicBean> list = new ArrayList<MusicBean>();
		MusicBean bean = new MusicBean();
		
		//依據Model執行結果，呼叫View
		if(keyword != null){
			bean = service.selectMusicName(keyword);
			if(bean != null){
				list.add(bean);
			}else if(bean == null && keyword == "") {
				errors.put("error", "請輸入歌曲名稱並點選以上選項");
				model.addAttribute("errors", errors);
			}else if(bean == null && keyword != null){
				errors.put("error", "請按空白從中點選歌曲以便搜尋");
				model.addAttribute("errors", errors);
			}
			model.addAttribute("bean", list);
		}
		return "music.select";
	}
}
