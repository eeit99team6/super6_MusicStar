package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.MusicContestPlayerBean;
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
	
	//搜尋歌單id裡的音樂 
	//謙 0313
	public List<MusicListContentBean> selectmusiclistiditem(Integer member_music_list_content_id){
		Query query = this.getSession().createQuery("from MusicListContentBean mlcb join MusicBean mc on mlcb.member_music_list_content_music_id=mc.music_id where mlcb.member_music_list_content_id=?0");
		query.setParameter("0", member_music_list_content_id);
	
		return (List<MusicListContentBean>) query.list();
	}
	
}
