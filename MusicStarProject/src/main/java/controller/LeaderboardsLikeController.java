package controller;

import java.util.ArrayList;
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
import model.bean.MusicListBean;
import model.bean.MusicListContentBean;
import model.service.LikeMusicService;
import model.service.MusicListContentService;
import model.service.MusicListService;

@Controller
public class LeaderboardsLikeController {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private LikeMusicService likeMusicService;
	@Autowired
	private MusicListService musicListService;
	@Autowired
	private MusicListContentService musicListContentService;
	
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
	public String InsertLike(Integer likes_music_id, Model model, HttpSession session) {
		Map<String,String> data = new HashMap<>();
				
		MemberBean mb =(MemberBean) session.getAttribute("loginOK");
		if(mb!=null) {
			String xxx =mb.getMbrId();
			LikeMusicBean newbean = new LikeMusicBean(xxx,likes_music_id);
			if(likeMusicService.insertLike(newbean)) {
				data.put("success", "謝謝您支持這位歌手");
			}else {				
				data.put("error", "你已經按過讚了,要取消嗎?");
			}
		}else {
			data.put("mustlogin", "必須登入才可以按讚喔~");
		}						
		return Parser.toJson(data);				
	}
	
	/**
	 * @author james.pu 2018.03.16 15:40
	 * @return 用在刪除likes
	 */
	@RequestMapping(path={"/likeleaderboards.likedelete.controller"},method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String DeleteLike(Integer likes_music_id, Model model, HttpSession session) {
		Map<String,String> data = new HashMap<>();
				
		MemberBean mb =(MemberBean) session.getAttribute("loginOK");
		if(mb!=null) {
			String xxx =mb.getMbrId();
			LikeMusicBean newbean = new LikeMusicBean(xxx,likes_music_id);
			if(likeMusicService.deleteLike(newbean)) {
				data.put("deleteok", "很遺憾");
			}else {				
				data.put("error", "必須登入才可以按讚喔~~");
			}
		}else {
			data.put("mustlogin", "必須登入才可以按讚喔~");
		}						
		return Parser.toJson(data);				
	}
	
	/**
	 * @author james.pu 2018.03.17 14:30
	 * @return 用在搜尋有無歌單
	 */
	@RequestMapping(path={"/likeleaderboards.like.selectList.controller"},method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String selectMusicList(MusicListBean bean,HttpSession session) {
		Map<String,String> data = new HashMap<>();
		
		MemberBean mb = (MemberBean) session.getAttribute("loginOK");
		if(mb!=null) {
			String xxxId = mb.getMbrId();
			List<MusicListBean> listbean =  musicListService.selectmemid(xxxId);
			List<MusicListBean> newlistbean =null;
			if(listbean!=null) {
				newlistbean =new ArrayList<MusicListBean>();
				newlistbean.addAll(listbean);
				return Parser.toJson(newlistbean);
			}			
		}else {
			data.put("mustlogin", "必須登入才可以加入歌單~");
		}
		return Parser.toJson(data);		
	}
	
	/**
	 * @author james.pu 2018.03.17 16:30
	 * @return 用在新增歌單
	 */
	@RequestMapping(path={"/likeleaderboards.like.insertList.controller"},method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
	@ResponseBody
	public String insetMusicList(MusicListContentBean bean,Integer member_music_list_content_id, Integer member_music_list_content_music_id, HttpSession session) {
		Map<String,String> data = new HashMap<>();
		
		MemberBean mb = (MemberBean) session.getAttribute("loginOK");
		if(mb!=null) {
			bean.setMember_music_list_content_id(member_music_list_content_id);
			bean.setMember_music_list_content_music_id(member_music_list_content_music_id);
			MusicListContentBean selectBean = musicListContentService.select(member_music_list_content_id, member_music_list_content_music_id);
			if(selectBean!=null) {
				data.put("listfail", "此首歌曲以在您的歌單裡");			
			}else {
				musicListContentService.insert(bean);
				data.put("listok", "新增此首歌曲成功");
			}														
		}else {
			data.put("mustlogin", "必須登入才可以加入歌單~");
		}
		return Parser.toJson(data);		
	}
	
	
}
