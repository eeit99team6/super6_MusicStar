package model.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<MusicContestBean> selectContestIdCount(MusicContestBean bean) {
		List<MusicContestBean> result = null;
		if(bean!=null) {
			result = musicContestDAOHibernate.selectContestIdCount();
		}
		return result;
	}
	
}
