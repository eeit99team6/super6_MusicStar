package model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MusicContestBean;
import model.bean.MusicContestVoteBean;
import model.dao.MusicContestDAO;
import model.dao.MusicContestVoteDAO;

@Service
@Transactional
public class MusicContestVoteService
{
	@Autowired
	private MusicContestVoteDAO musicContestVoteDAO;
	@Autowired
	private MusicContestDAO musicContestDAO;

	public List<MusicContestBean> getContests(String status){		
		if(status != null) {
			status = status.toLowerCase();
			switch (status) {
			case "applying":			
				return musicContestDAO.slelctCtstAtApplying();
			case "validating":			
				return musicContestDAO.slelctCtstAtValidating();
			case "voting":			
				return musicContestDAO.slelctCtstAtVoting();
			case "close":			
				return musicContestDAO.slelctCtstIsClose();
			}
		}
		return null;
	}
	
	public boolean canVote(Integer musicCtstId) {	
		return true;	
	}

	public boolean voting(MusicContestVoteBean bean) {
		return bean != null && bean.getMusic_contest_id() != null && bean.getVoter_id() != null && bean.getMusic_contest_player_id() != null && musicContestVoteDAO.insert(bean) != null ? true : false;		
	}
}
