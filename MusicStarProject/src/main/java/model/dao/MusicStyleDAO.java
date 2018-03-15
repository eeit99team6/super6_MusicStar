package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.MusicStyleBean;

@Repository
public class MusicStyleDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	/**
	 * @author Phil 2018.03.15
	 */
	public MusicStyleBean selectById(Integer styleId)
	{
		return styleId != null ? getSession().get(MusicStyleBean.class, styleId) : null;
	}

	/**
	 * @author Phil 2018.03.15
	 */
	public List<MusicStyleBean> selectAll()
	{
		return getSession().createQuery("from MusicStyleBean", MusicStyleBean.class).list();
	}



}
