package model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.MusicContestBean;

@Repository
public class MusicContestDAO {
	
    @Autowired
	private SessionFactory sessoinFactory;
    
	// session
	public MusicContestDAO(SessionFactory sessoinFactory) {
		this.sessoinFactory = sessoinFactory;
	}

	public Session getSessoin() {
		return sessoinFactory.getCurrentSession();
	}

	// select(id)
	public MusicContestBean select(int id) {
		return (MusicContestBean) this.getSessoin().get(MusicContestBean.class, id);
	}

	// select
	public List<MusicContestBean> select() {
		return this.getSessoin().createQuery("from MusicContestBean", MusicContestBean.class).list();
	}
	
	// select where music_contest where music_contest_status = '報名中'

	public List<MusicContestBean> selectMusicContestStatus(){
		return this.getSessoin().createQuery("from MusicContestBean mb where mb.music_contest_status = '報名中'", MusicContestBean.class).list();
	}
	
	// select where music_contest where music_contest_status = '比賽結束'

		public List<MusicContestBean> selecHistorytMusicContest(){
			return this.getSessoin().createQuery("from MusicContestBean mb where mb.music_contest_status = '比賽結束'", MusicContestBean.class).list();
		}
	
	// insert
	public MusicContestBean insert(MusicContestBean bean) {
		if (bean != null) {
				this.getSessoin().save(bean);
				return bean;
			}
		return null;
	}

	// update
	public MusicContestBean update(Integer id, MusicContestBean bean) {
		MusicContestBean temp = this.getSessoin().get(MusicContestBean.class, id);
		if(temp!=null ) {
			temp.setMusic_contest_apply_start_date(bean.getMusic_contest_apply_start_date());
			temp.setMusic_contest_description(bean.getMusic_contest_description());
			temp.setMusic_contest_end_date(bean.getMusic_contest_end_date());
			temp.setMusic_contest_name(bean.getMusic_contest_name());
			temp.setMusic_contest_photo(bean.getMusic_contest_photo());
			temp.setMusic_contest_status(bean.getMusic_contest_status());
			temp.setMusic_contest_style_id(bean.getMusic_contest_style_id());
			temp.setMusic_contest_validate_date(bean.getMusic_contest_validate_date());
			temp.setMusic_contest_vote_start_date(bean.getMusic_contest_vote_start_date());
			return temp;
		}
		return null;
	}
	
	// delete
	public boolean delete(MusicContestBean bean) {
		MusicContestBean temp =this.getSessoin().get(MusicContestBean.class, bean.getMusic_contest_id());
		if(temp!=null) {
			this.getSessoin().delete(temp);
			return true;
		}
		return false;
	}

}
