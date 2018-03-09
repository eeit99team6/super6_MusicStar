package model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MusicContestBean;
import model.bean.MusicContestPlayerBean;
import model.dao.MusicContestDAOHibernate;
import model.dao.MusicContestPlayerDAO;

@Service
@Transactional
public class MusicContestPlayerService {
	
	@Autowired
	private MusicContestPlayerDAO musicContestPlayerDAO;

	@Autowired
	private MusicContestDAOHibernate musicContestDAOHibernate;

	
	public List<MusicContestPlayerBean> selectContestIdTop3(MusicContestPlayerBean bean){
		List<MusicContestPlayerBean> result = null;
		if(bean!=null && bean.getMusic_contest_id()!=null) {
			List<MusicContestPlayerBean> temp = musicContestPlayerDAO.selectContestIdTop3(bean.getMusic_contest_id());
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
			List<MusicContestPlayerBean> temp = musicContestPlayerDAO.selectContestIdTop10(bean.getMusic_contest_id());
			if(temp!=null) {
				result = new ArrayList<MusicContestPlayerBean>();
				result.addAll(temp);
			}
		}else {
			return result;
		}
		return result;
	}
	
<<<<<<< HEAD
	/**
	 * 取得指定賽事ID的參賽者資料
	 * 
	 * @author Phil
	 * @param musicCtstId 賽事的ID
	 * @return 指定賽事參賽者資料的List
	 */
	public List<Map> getContestPlayers(Integer musicCtstId) {
		return musicCtstId != null ? musicContestPlayerDAO.selectPlayersByMusicCtstId(musicCtstId) : null;
=======
	public List<MusicContestBean> selectContestIdCount(MusicContestBean bean) {
		List<MusicContestBean> result = null;
		if(bean!=null) {
			result = musicContestDAOHibernate.selectContestIdCount();
		}
		return result;
>>>>>>> branch 'master' of https://github.com/eeit99team6/super6_MusicStar.git
	}
	
}
