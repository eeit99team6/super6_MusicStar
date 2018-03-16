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

import _global.utils.Parser;
import model.bean.MemberBean;
import model.bean.MusicListBean;
import model.bean.MusicListContentBean;
import model.service.MusicListContentService;
import model.service.MusicListService;

/**
 * @author Yeh
 * @return 2018/3/16 16:00
 */

@Controller
public class MusicSelectListController {
	@Autowired
	private MusicListService musicListService;
	
	@Autowired
	private MusicListContentService musicListContentService;
	
	@Autowired
	private ApplicationContext context;
	
	@RequestMapping(
			path={"/music/musicSelectList"},
			method={RequestMethod.GET, RequestMethod.POST},
			produces="application/json;charset=UTF-8"
	)
	@ResponseBody
	protected String method(HttpSession session ,Model model, MusicListBean bean){
		MemberBean mb =(MemberBean) session.getAttribute("loginOK");
		Map<String,String> data = new HashMap<>();
		
		if(mb!=null){
			String MbrId = mb.getMbrId();
			List<MusicListBean> listbean =  musicListService.selectmemid(MbrId);
			for(MusicListBean map : listbean){
				Integer temp = map.getMember_music_list_id();
				String id = temp.toString();
				String name = map.getMember_music_list_name();
				data.put(id, name);
			}
		}else{
			data.put("mustlogin", "必須登入才可以加入歌單哦~");
		}
		return Parser.toJson(data);
	}
	
	
	@RequestMapping(
			path={"/music/musicInsertList"},
			method={RequestMethod.GET, RequestMethod.POST},
			produces="application/json;charset=UTF-8"
	)
	@ResponseBody
	protected String method(Integer member_music_list_content_id,Integer member_music_list_content_music_id,
							HttpSession session ,Model model, MusicListContentBean bean){
		
		Map<String,String> data = new HashMap<>();
		bean.setMember_music_list_content_id(member_music_list_content_id);
		bean.setMember_music_list_content_music_id(member_music_list_content_music_id);
		
		MusicListContentBean select = musicListContentService.select(member_music_list_content_id, member_music_list_content_music_id);
		
		if(select!=null) {
			data.put("insertListFailed", "您已加入過此歌單");
		}else {
			musicListContentService.insert(bean);
			data.put("insertListSuccess", "已成功加入歌單");
		}
		
		return Parser.toJson(data);
	}
}
