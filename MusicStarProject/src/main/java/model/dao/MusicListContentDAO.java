package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import model.bean.MusicListContentBean;

@Repository
public class MusicListContentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public boolean deletefirst(Integer member_music_list_content_id) {
		List<MusicListContentBean> result = this.getSession().createQuery("from MusicListContentBean where member_music_list_content_id = :id",MusicListContentBean.class).setParameter("id",member_music_list_content_id).list();
		if (result != null && !result.isEmpty()) {
			this.getSession().createQuery("delete from MusicListContentBean where member_music_list_content_id = :id").setParameter("id",member_music_list_content_id).executeUpdate();
			return true;
		}
		return false;
	}
	public boolean deletefirst2(Integer member_music_list_content_id) {
		MusicListContentBean result = this.getSession().get(MusicListContentBean.class, member_music_list_content_id);
		if(result!=null) {
			this.getSession().delete(result);
			return true;
		}
		return false;
	}
	public List<MusicListContentBean> select(Integer member_music_list_content_id) {
		return this.getSession().createQuery("from MusicListContentBean",MusicListContentBean.class).list();
				}
	
	
}
