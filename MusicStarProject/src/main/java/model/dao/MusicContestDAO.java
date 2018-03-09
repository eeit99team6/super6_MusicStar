package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _global.utils.Processor;
import model.bean.MusicContestBean;

@Repository
public class MusicContestDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 取得狀態為報名中的賽事資料
	 * @author Phil
	 * @return 報名中的賽事資料的List
	 */
	public List<MusicContestBean> slelctCtstAtApplying(){		
		return getSession().createQuery("from MusicContestBean where music_contest_apply_start_date <= :today and music_contest_validate_date > :today",MusicContestBean.class).setParameter("today", Processor.getCurrentDate()).list();	
	}

	/**
	 * 取得狀態為審核中的賽事資料
	 * @author Phil
	 * @return 審核中的賽事資料的List
	 */
	public List<MusicContestBean> slelctCtstAtValidating(){		
		return getSession().createQuery("from MusicContestBean where music_contest_validate_date <= :today and music_contest_vote_start_date > :today",MusicContestBean.class).setParameter("today", Processor.getCurrentDate()).list();	
	}
	
	/**
	 * 取得狀態為投票中的賽事資料
	 * @author Phil
	 * @return 投票中的賽事資料的List
	 */
	public List<MusicContestBean> slelctCtstAtVoting(){		
		return getSession().createQuery("from MusicContestBean where music_contest_vote_start_date <= :today and music_contest_end_date > :today",MusicContestBean.class).setParameter("today", Processor.getCurrentDate()).list();	
	}
	
	/**
	 * 取得狀態為已結束的賽事資料
	 * @author Phil
	 * @return 已結束的賽事資料的List
	 */
	public List<MusicContestBean> slelctCtstIsClose(){		
		return getSession().createQuery("from MusicContestBean where music_contest_end_date <= :today",MusicContestBean.class).setParameter("today", Processor.getCurrentDate()).list();	
	}
}
