package model.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.MusicBean;



@Repository
public class MusicDAO
{
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	//select all
	
	public List<MusicBean> select(){
		return this.getSession().createQuery("from MusicBean",MusicBean.class).list();
	}
	
	//select by id 
	
		public List<MusicBean> selectById(String id){
			Query query = this.getSession().createQuery("from MusicBean mub where mub.music_member_id = :id", MusicBean.class);
			query.setParameter("id", id);
			return (List<MusicBean>) query.list();
		}
	
	// update 
	
	public MusicBean update(Integer id , MusicBean bean) {
		MusicBean temp=null;
	 if(bean!=null && id!=null) {
		 temp =this.getSession().get(MusicBean.class, id);
		 if(temp!=null) {
			 temp.setMusic_description(bean.getMusic_description());
			 temp.setMusic_link(bean.getMusic_link());
			 temp.setMusic_lyrics(bean.getMusic_lyrics());
			 temp.setMusic_member_id(bean.getMusic_member_id());
			 temp.setMusic_name(bean.getMusic_name());
			 temp.setMusic_photo(bean.getMusic_photo());
			 temp.setMusic_style_id(bean.getMusic_style_id());
		   return bean;
		 }
	 }
		return null;
	}
	
	// insert 
	
	public MusicBean inisert(MusicBean bean) {
		if(bean!=null) {
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}
	
	//delete 
	
	public boolean delete(MusicBean bean) {
		MusicBean temp = this.getSession().get(MusicBean.class, bean.getMusic_id());
		if(temp!=null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}
	
}
