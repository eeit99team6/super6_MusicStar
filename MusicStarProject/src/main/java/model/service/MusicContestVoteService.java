package model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MusicContestVoteBean;
import model.dao.MusicContestVoteDAO;

@Service
@Transactional
public class MusicContestVoteService
{
	@Autowired
	private MusicContestVoteDAO musicContestVoteDAO;

	public boolean canVote(Integer musicCtstId,String musicCtstVoterId) {	
		return musicContestVoteDAO.selectById(musicCtstId, musicCtstVoterId) == null ? true : false;	
	}

	public boolean voting(MusicContestVoteBean bean) {

		return bean != null && bean.getMusic_contest_id() != null && bean.getVoter_id() != null && bean.getMusic_contest_player_id() != null && musicContestVoteDAO.insert(bean) != null ? true : false;		
	}
}
