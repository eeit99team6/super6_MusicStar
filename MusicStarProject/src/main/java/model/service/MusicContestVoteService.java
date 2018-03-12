package model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MusicContestBean;
import model.bean.MusicContestVoteBean;
import model.dao.MusicContestDAO;
import model.dao.MusicContestPlayerDAO;
import model.dao.MusicContestVoteDAO;

@Service
@Transactional
public class MusicContestVoteService
{
	@Autowired
	private MusicContestVoteDAO musicContestVoteDAO;
	@Autowired
	private MusicContestPlayerDAO musicContestPlayerDAO;
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
		return musicContestDAO.checkCtstStatus(musicCtstId) == 3 ? true : false;
	}

	public boolean voting(MusicContestVoteBean bean) {
		
		if (bean != null) {
			MusicContestVoteBean mcvb = null;
			if ((mcvb = musicContestVoteDAO.insert(bean)) != null) {
				musicContestPlayerDAO.addVotes(mcvb);
				return true;
			}
		}		
		return false;		
	}
	
}
