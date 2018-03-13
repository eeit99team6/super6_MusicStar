package model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MusicContestBean;
import model.bean.MusicContestPlayerBean;
import model.dao.MusicContestDAO;
import model.dao.MusicContestPlayerDAO;

@Service
@Transactional
public class MusicContestPlayerService {
	
	@Autowired
	private MusicContestPlayerDAO musicContestPlayerDAO;

	@Autowired
	private MusicContestDAO musicContestDAO;

	
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

	/**
	 * 取得指定賽事ID的參賽者資料
	 * 
	 * @author Phil
	 * @param musicCtstId 賽事的ID
	 * @return 指定賽事參賽者資料的List
	 */
	public List<Map> getContestPlayers(Integer musicCtstId) {
		return musicCtstId != null ? musicContestPlayerDAO.selectPlayersByMusicCtstId(musicCtstId) : null;
	}
	
	public List<MusicContestBean> selectContestIdCount(MusicContestBean bean) {
		List<MusicContestBean> result = null;
		if(bean!=null) {
			result = musicContestDAO.selectContestIdCount();
		}
		return result;
	}
	

	/**
	 * 查看: 檢查 是否已報名此賽事 
	 * 
	 * @author yuting
	 * 
	 */
	public long chekcedPlayer(Integer musicId, String memberId) {
		if(musicId!=null && memberId!=null) {
			return musicContestPlayerDAO.selectByMIdAndMusId(musicId, memberId);
		}
		return 0;
	}
	
	
	
	/**
	 * 報名: 塞入參賽者資料
	 * 
	 * @author yuting
	 * 
	 */
	public MusicContestPlayerBean insertPlayer(MusicContestPlayerBean bean) {
		if (bean != null) {
			MusicContestPlayerBean reslut = musicContestPlayerDAO.insert(bean);
			return reslut;
		}
		return null;
	}
	
}
