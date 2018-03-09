package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import _global.utils.Checker;
import _global.utils.Parser;
import model.bean.MemberBean;
import model.bean.MusicContestVoteBean;
import model.service.MusicContestVoteService;

@Controller
public class MusicContestVoteController {

	@Autowired
	private MusicContestVoteService musicContestVoteService;

	@RequestMapping(path = "/contests/voting/vote", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String contestvotingAjax(String musicCtstId, String musicCtstPlayerId, HttpSession session) {
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

		MemberBean mb = (MemberBean) session.getAttribute("loginOK");

		if (mb != null) {
			String musicCtstVoterId = mb.getMbrId();
			if (musicContestVoteService.canVote(ctstId,musicCtstVoterId)) {
				MusicContestVoteBean bean = new MusicContestVoteBean(ctstId,musicCtstVoterId,musicCtstPlayerId);
				if (musicContestVoteService.voting(bean)) {
					data.put("success", "投票成功!!");
				} else {
					data.put("errMsg", "投票失敗~請稍候再試!!");
				}
			} else {
				data.put("errMsg", "這場比賽您已經投過票囉~無法再次投票!!");
			}

		} else {
			data.put("mustLogin", "必須登入才可進行投票!!");
		}

		return Parser.toJson(data);
	}

}
