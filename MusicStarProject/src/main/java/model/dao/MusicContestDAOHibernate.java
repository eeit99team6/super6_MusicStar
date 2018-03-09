package model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.MusicContestBean;
import model.bean.MusicContestPlayerBean;

@Repository
public class MusicContestDAOHibernate {
	
    @Autowired
	private SessionFactory sessoinFactory;
    
	// session
	public MusicContestDAOHibernate(SessionFactory sessoinFactory) {
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
	public void delete(MusicContestBean bean) {
		MusicContestBean temp =this.getSessoin().get(MusicContestBean.class, bean.getMusic_contest_id());
		if(temp!=null) {
			this.getSessoin().delete(temp);
		}
	}

	//用在搜尋已結束的比賽有幾個-賽事排行榜使用
	public List<MusicContestBean> selectContestIdCount(){
		Query query = this.getSessoin().createQuery("select count(music_contest_id) from MusicContestBean where music_contest_status='比賽結束'");
		return (List<MusicContestBean>) query.list();
	}
	
}
