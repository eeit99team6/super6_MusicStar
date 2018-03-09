package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.EmployeeBean;
import model.bean.MemberBean;

@Repository
public class EmployeeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	// session
	public EmployeeDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	// select(id)

	public EmployeeBean select(String id) {
		return (EmployeeBean) this.getSession().get(EmployeeBean.class, id);
	}

	// select
	public List<EmployeeBean> select() {
		return this.getSession().createQuery("from EmployeeBean", EmployeeBean.class).list();
	}

	// insert
	public EmployeeBean insert(EmployeeBean bean) {
		if (bean != null) {
			EmployeeBean temp = this.getSession().get(EmployeeBean.class, bean.getEmployee_id());
			if (temp == null) {
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	// update
	public EmployeeBean update(EmployeeBean bean) {
		if(bean!=null) {
			EmployeeBean temp = this.getSession().get(EmployeeBean.class, bean.getEmployee_id());
			if (temp != null) {
				temp.setEmployee_name(bean.getEmployee_name());
				temp.setEmployee_id(bean.getEmployee_id());
				temp.setEmployee_password(bean.getEmployee_password());
				return bean;
		}
	}
		return null;
 }
}
