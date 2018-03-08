package model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MusicContestBean;
import model.dao.MusicContestDAOHibernate;

@Service
@Transactional
public class MusicContestService {
    
	@Autowired
	private MusicContestDAOHibernate music_contestDao;
	
	// select
	public List<MusicContestBean> select() {
		List<MusicContestBean> contestBean = (List<MusicContestBean>) music_contestDao.select();
		return contestBean;
	}

	// select with id
	public MusicContestBean select(int id) {
		if (id != 0) {
			MusicContestBean contestBean = (MusicContestBean) music_contestDao.select(id);
			return contestBean;
		}
		return null;
	}

	// insert
	public MusicContestBean insert(MusicContestBean bean) {
		if (bean != null) {
			music_contestDao.insert(bean);
			return bean;
		}
		return null;
	}

	// delete
	public boolean delete(MusicContestBean bean) {
		if(bean!= null) {
			music_contestDao.delete(bean);
		    return true;
		}
		return false;
	}
	
	// update
	public MusicContestBean update(Integer id, MusicContestBean bean) {
		if(bean!=null && id!=null) {
			music_contestDao.update(id,bean);
			return bean;
		}
		return null;
	}
	
}
