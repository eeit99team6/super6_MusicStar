package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import _global.utils.Checker;
import _global.utils.Parser;
import model.bean.MusicContestBean;
import model.bean.MusicContestPlayerBean;
import model.service.MusicContestPlayerService;
import model.service.MusicStyleService;

@Controller
public class MusicContestPlayerController {
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
	
	// 塞入一筆player的資料到指定的比賽
	
	/*
	 * YuTingWu 
	 * 
	 * */
	@RequestMapping(value="/pages/insertPlayer", method=RequestMethod.POST)
	public String insertPlayer(@RequestParam(value="music_contest_players_votes", required=false) String playersVotes ,
			MusicContestPlayerBean bean,Model model) {
		Long result = musicContestPlayerService.chekcedPlayer(bean.getMusic_contest_id(), bean.getMusic_contest_player_id());
        if(!(result !=0)) {
		  if(bean!=null) {
			  musicContestPlayerService.insertPlayer(bean);
			  model.addAttribute("playerInfor", bean);
			  return "r.insertPlayer.ok";
		}
      }
        model.addAttribute("alertInsert", "此賽事您已經報名過了唷!");
		return "f.insertPlayer.notok";
    }
	
	
	// 確認 報名者是否已經報名過比賽
	
	/* =================== 確認 報名者是否已經報名過比賽  ========================== */
	
	@RequestMapping(value="/pages/checkedPlayerAjax", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String chekcedPlayer(@RequestParam(value="music_contest_players_votes", required=false) String playersVotes ,
			@RequestParam(value="music_id", required=false) String musicId ,
			MusicContestPlayerBean bean,Model model) {
//		List listCont = new  ArrayList<>();
//		Map<String,Long> map = new HashMap<String,Long>();
		Long result = musicContestPlayerService.chekcedPlayer(bean.getMusic_contest_id(), bean.getMusic_contest_player_id());
		
		System.out.println(result);
//		listCont.add(map.put("count", result));
		return Parser.toJson(result);
	}
	
	
	
	
	
}
