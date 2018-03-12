package model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.LikeMusicBean;

@Repository
public class LikeMusicDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public LikeMusicBean insert(LikeMusicBean bean) {
		this.getSession().save(bean);
		return bean;

		
	}
}
