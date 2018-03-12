package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _global.utils.Processor;
import model.bean.MusicContestBean;

@Repository
public class MusicContestDAO {
	
    @Autowired
	private SessionFactory sessoinFactory;
    
	// session
	public MusicContestDAO(SessionFactory sessoinFactory) {
		this.sessoinFactory = sessoinFactory;
	}

	public Session getSession() {
		return sessoinFactory.getCurrentSession();
	}
	
	/**
	 * 確認賽式的狀態
	 * @author Phil
	 * @return 尚未開始:0 , 報名中:1 , 審核中:2 , 投票中:3 , 已結束:4
	 */
	public int checkCtstStatus (Integer musicCtstId)
	{
		MusicContestBean bean = getSession().createQuery("from MusicContestBean where music_contest_id = :musicCtstId", MusicContestBean.class).setParameter("musicCtstId", musicCtstId).uniqueResult();
		Date today = Processor.getCurrentTwDate();
		if (today.after(bean.getMusic_contest_end_date()))
			return 4;
		else if (today.after(bean.getMusic_contest_vote_start_date()))
			return 3;
		else if (today.after(bean.getMusic_contest_validate_date()))
			return 2;
		else if (today.after(bean.getMusic_contest_apply_start_date()))
			return 1;
		else
			return 0;		
	}
	
	/**
	 * 取得狀態為報名中的賽事資料
	 * @author Phil
	 * @return 報名中的賽事資料的List
	 */
	public List<MusicContestBean> slelctCtstAtApplying(){		
		return getSession().createQuery("from MusicContestBean where music_contest_apply_start_date <= :today and music_contest_validate_date > :today",MusicContestBean.class).setParameter("today", Processor.getCurrentTwDate()).list();	
	}

	/**
	 * 取得狀態為審核中的賽事資料
	 * @author Phil
	 * @return 審核中的賽事資料的List
	 */
	public List<MusicContestBean> slelctCtstAtValidating(){		
		return getSession().createQuery("from MusicContestBean where music_contest_validate_date <= :today and music_contest_vote_start_date > :today",MusicContestBean.class).setParameter("today", Processor.getCurrentTwDate()).list();	
	}
	
	/**
	 * 取得狀態為投票中的賽事資料
	 * @author Phil
	 * @return 投票中的賽事資料的List
	 */
	public List<MusicContestBean> slelctCtstAtVoting(){		
		return getSession().createQuery("from MusicContestBean where music_contest_vote_start_date <= :today and music_contest_end_date > :today",MusicContestBean.class).setParameter("today", Processor.getCurrentTwDate()).list();	
	}
	
	/**
	 * 取得狀態為已結束的賽事資料
	 * @author Phil
	 * @return 已結束的賽事資料的List
	 */
	public List<MusicContestBean> slelctCtstIsClose(){		
		return getSession().createQuery("from MusicContestBean where music_contest_end_date <= :today",MusicContestBean.class).setParameter("today", Processor.getCurrentTwDate()).list();	
	}
	
	//用在搜尋已結束的比賽有幾個-賽事排行榜使用
	public List<MusicContestBean> selectContestIdCount(){
		Query query = this.getSession().createQuery("select count(music_contest_id) from MusicContestBean where music_contest_status='比賽結束'");
		return (List<MusicContestBean>) query.list();
	}

	// select(id)
	public MusicContestBean select(int id) {
		return (MusicContestBean) this.getSession().get(MusicContestBean.class, id);
	}

	// select
	public List<MusicContestBean> select() {
		return this.getSession().createQuery("from MusicContestBean", MusicContestBean.class).list();
	}
	
	// select where music_contest where music_contest_status = '報名中'

	public List<MusicContestBean> selectMusicContestStatus(){
		return this.getSession().createQuery("from MusicContestBean mb where mb.music_contest_status = '報名中'", MusicContestBean.class).list();
	}
	
	// select where music_contest where music_contest_status = '比賽結束'

		public List<MusicContestBean> selecHistorytMusicContest(){
			return this.getSession().createQuery("from MusicContestBean mb where mb.music_contest_status = '比賽結束'", MusicContestBean.class).list();
		}
	
	// insert
	public MusicContestBean insert(MusicContestBean bean) {
		if (bean != null) {
				this.getSession().save(bean);
				return bean;
			}
		return null;
	}

	// update
	public MusicContestBean update(Integer id, MusicContestBean bean) {
		MusicContestBean temp = this.getSession().get(MusicContestBean.class, id);
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
		MusicContestBean temp =this.getSession().get(MusicContestBean.class, bean.getMusic_contest_id());
		if(temp!=null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}

}
