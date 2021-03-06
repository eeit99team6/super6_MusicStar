package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import _global.utils.Checker;
import _global.utils.Parser;
import model.bean.MemberBean;
import model.bean.MusicContestVoteBean;
import model.service.MusicContestPlayerService;
import model.service.MusicContestService;
import model.service.MusicContestVoteService;

@Controller
public class MusicContestVoteController {

	@Autowired
	private MusicContestService musicContestService;
	@Autowired
	private MusicContestVoteService musicContestVoteService;
	@Autowired
	private MusicContestPlayerService musicContestPlayerService;

	/**
	 * 進行投票
	 * @author Phil 2018.03.15
	 */
	@RequestMapping(path = "/contests/voting/vote", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String contestVotingAjax(String musicCtstId, String musicCtstPlayerId, HttpSession session) {
		Map<String, String> data = new HashMap<>();
		
		Integer ctstId = null;
		if (!Checker.notEmpty(musicCtstId)) {
			data.put("musicCtstIdErr", "必須提供musicCtstId");
		} else if ((ctstId = Parser.parseInt(musicCtstId)) == null) {
			data.put("musicCtstIdErr", "musicCtstId的格式錯誤");
		}
		if (!Checker.notEmpty(musicCtstPlayerId)) {
			data.put("musicCtstPlayerIdErr", "必須提供musicCtstPlayerId");
		}
		
		if (Checker.notEmpty(data)) {
			return Parser.toJson(data);
		}
		
		MemberBean mb = (MemberBean) session.getAttribute("loginOK");

		if (mb != null) {
			String musicCtstVoterId = mb.getMbrId();
			if (musicContestVoteService.canVote(ctstId)) {
				MusicContestVoteBean bean = new MusicContestVoteBean(ctstId,musicCtstVoterId,musicCtstPlayerId);
				if (musicContestVoteService.voting(bean)) {
					data.put("success", "投票成功!!");
				} else {
					data.put("errMsg", "這場比賽您已經投過票囉~無法再次投票!!");
				}
			} else {
				data.put("errMsg", "這場比賽目前無法進行投票!!");
			}
		} else {
			data.put("mustLogin", "必須登入才可進行投票!!");
		}

		return Parser.toJson(data);
	}
	
	/**
	 * 取得指定投票中賽事的參賽者資料
	 * @author Phil 2018.03.15
	 */
	@RequestMapping(path = "/contests/voting/players", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getContestPlayersAjax(String contestId) {
		Map<String, String> data = new HashMap<>();
		Integer id = Parser.parseInt(contestId);
		if (id == null) {
			data.put("errMsg", "contestId格式錯誤");
		}
		if (Checker.notEmpty(data)) {
			return Parser.toJson(data);
		}
		return Parser.toJson(musicContestPlayerService.getContestPlayers(id));
	}

	/**
	 * 取得指定狀態的賽事資料
	 * @author Phil 2018.03.16
	 */
	@RequestMapping(path = "/contests/{status}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getContestByStatus(@PathVariable("status") String status) {	
		return Parser.toJson(musicContestVoteService.getContests(status));	
	}
	
	/**
	 * 取得指定ID的賽事資料
	 * @author Phil 2018.03.16
	 */
	@RequestMapping(path = "/contests/voting/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String contestAtVotingAjax(String contestId) {	
		Map<String, String> data = new HashMap<>();
		Integer id = Parser.parseInt(contestId);
		if (id == null) {
			data.put("errMsg", "contestId格式錯誤");
		}
		if (Checker.notEmpty(data)) {
			return Parser.toJson(data);
		}		
		return Parser.toJson(musicContestService.select(id));
	}
}