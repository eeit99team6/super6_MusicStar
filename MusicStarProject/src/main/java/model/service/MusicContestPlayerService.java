package model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MusicContestPlayerBean;
import model.dao.MusicContestPlayerDAO;

@Service
@Transactional
public class MusicContestPlayerService {
	
	@Autowired
	private MusicContestPlayerDAO musicContestPlayersDAO;
	
	public List<MusicContestPlayerBean> selectContestIdTop3(MusicContestPlayerBean bean){
		List<MusicContestPlayerBean> result = null;
		if(bean!=null && bean.getMusic_contest_id()!=null) {
			List<MusicContestPlayerBean> temp = musicContestPlayersDAO.selectContestIdTop3(bean.getMusic_contest_id());
			if(temp!=null) {
				result = new ArrayList<MusicContestPlayerBean>();
				result.addAll(temp);
			}
		}else {
			return result;
		}
		return result;
	} 
	
	public List<MusicContestPlayerBean> selectContestIdTop10(MusicContestPlayerBean bean){
		List<MusicContestPlayerBean> result = null;
		if(bean!=null && bean.getMusic_contest_id()!=null) {
			List<MusicContestPlayerBean> temp = musicContestPlayersDAO.selectContestIdTop10(bean.getMusic_contest_id());
			if(temp!=null) {
				result = new ArrayList<MusicContestPlayerBean>();
				result.addAll(temp);
			}
		}else {
			return result;
		}
		return result;
	}
	
	
}
