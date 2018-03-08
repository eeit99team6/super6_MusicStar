package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.MusicContestPlayerBean;

@Repository
public class MusicContestPlayerDAO {
	
	@Autowired
	private SessionFactory sessionFactory = null;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
//用在搜尋該比賽排行前3名	
	public List<MusicContestPlayerBean> selectContestIdTop3(Integer music_contest_id){
		Query query = this.getSession().createQuery("from MusicContestPlayerBean mb join MusicBean mc on mb.music_id=mc.music_id join MusicContestBean md on mb.music_contest_id=md.music_contest_id where mb.music_contest_id=?0 order by music_contest_players_votes desc");
		query.setParameter("0", music_contest_id);
		query.setMaxResults(3);
		return (List<MusicContestPlayerBean>) query.list();
	}
	
//用在搜尋該比賽排行前10名		
	public List<MusicContestPlayerBean> selectContestIdTop10(Integer music_contest_id){
		Query query = this.getSession().createQuery("from MusicContestPlayerBean mb join MusicBean mc on mb.music_id=mc.music_id join MusicContestBean md on mb.music_contest_id=md.music_contest_id where mb.music_contest_id=?0 order by music_contest_players_votes desc");
		query.setParameter("0", music_contest_id);
		query.setMaxResults(10);
		return (List<MusicContestPlayerBean>) query.list();
	}

	public List<MusicContestPlayerBean> selectContestId(Integer music_contest_id){
		Query query = this.getSession().createQuery("from MusicContestPlayerBean where music_contest_id=?0");
		query.setParameter("0", music_contest_id);
		return (List<MusicContestPlayerBean>) query.list();
	}
	
	public List<MusicContestPlayerBean> selectAll(){
		Query query = this.getSession().createQuery("from MusicContestPlayerBean");
		return (List<MusicContestPlayerBean>) query.list();
	}
	
	public MusicContestPlayerBean insert(MusicContestPlayerBean bean) {		
		if(bean!=null) {
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}
	
	public MusicContestPlayerBean update(MusicContestPlayerBean bean) {
		MusicContestPlayerBean result =null;
		if(bean!=null && bean.getMusic_contest_id()!=null && bean.getMusic_contest_player_id()!=null) {
			result.setMusic_id(bean.getMusic_id());
			return result;
		}
		return null;
	}
	
	public boolean delete(Integer music_contest_id,String music_contest_player_id) {
		MusicContestPlayerBean result = null;
		if(music_contest_id!=null && music_contest_player_id!=null) {
			this.getSession().delete(result);
			return true;
		}
		return false;
	}
	
}
