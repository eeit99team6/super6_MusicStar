package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.SlideshowBean;

@Repository
public class SlideshowDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public List<SlideshowBean> selectAll()
	{
		return getSession().createQuery("from SlideshowBean order by slide_order", SlideshowBean.class).list();
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public SlideshowBean insert(SlideshowBean bean)
	{
		if (bean != null)
		{
			bean.setSlide_id((Integer) getSession().save(bean));		
			return bean;
		}
		return null;
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public boolean update(SlideshowBean sb)
	{
		if (sb != null)
		{
			getSession().saveOrUpdate(sb);
			return true;
		}
		return false;
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public boolean delete(SlideshowBean sb)
	{
		if (sb != null)
		{
			getSession().delete(sb);
			return true;
		}
		return false;
	}

}
