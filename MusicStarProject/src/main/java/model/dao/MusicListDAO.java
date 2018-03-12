package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MusicListBean;

@Repository
public class MusicListDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public MusicListBean select(Integer member_music_list_id) {
		return this.getSession().get(MusicListBean.class, member_music_list_id);
	}
	
	public List<MusicListBean> select() {
		return this.getSession().createQuery(
				"from MusicListBean", MusicListBean.class).list();
	}
	public MusicListBean insert(MusicListBean bean) {
		
				this.getSession().save(bean);
				return bean;
		
	}

	public MusicListBean update(Integer member_music_list_id,
			String member_music_list_name, String member_music_list_description, String member_music_list_member_id, Short member_music_list_quantity) {
		MusicListBean result = this.getSession().get(MusicListBean.class, member_music_list_id);
		if(result!=null) {
			result.setMember_music_list_name(member_music_list_name);
//			result.setMember_music_list_member_id(member_music_list_member_id);
			result.setMember_music_list_description(member_music_list_description);
			result.setMember_music_list_quantity(member_music_list_quantity);
		}
		return result;
	}
	public boolean delete(Integer member_music_list_id) {
		MusicListBean result = this.getSession().get(MusicListBean.class, member_music_list_id);
		if(result!=null) {
			this.getSession().delete(result);
			return true;
		}
		return false;
	}
}
		
	