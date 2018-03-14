package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.LikeMusicBean;
import model.bean.MusicContestPlayerBean;
import model.bean.MusicListContentBean;

@Repository
public class MusicListContentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	//刪歌單時用的delete
	public boolean deletefirst(Integer member_music_list_content_id) {
		List<MusicListContentBean> result = this.getSession().createQuery("from MusicListContentBean where member_music_list_content_id = :id",MusicListContentBean.class).setParameter("id",member_music_list_content_id).list();
		if (result != null && !result.isEmpty()) {
			this.getSession().createQuery("delete from MusicListContentBean where member_music_list_content_id = :id").setParameter("id",member_music_list_content_id).executeUpdate();
			return true;
		}
		return false;
	}
	//刪除歌單內之音樂時用的delete
	public boolean delete2(Integer member_music_list_content_id,Integer member_music_list_content_music_id) {
		Query query =this.getSession().createQuery("from MusicListContentBean where member_music_list_content_id= :site1 and member_music_list_content_music_id= :site2");
		query.setParameter("site1", member_music_list_content_id);
		query.setParameter("site2", member_music_list_content_music_id);		
		List<MusicListContentBean> result= (List<MusicListContentBean>) query.list();
		if(result!=null && !result.isEmpty()) {
			this.getSession().createQuery("delete from MusicListContentBean where member_music_list_content_id = :site1 and member_music_list_content_music_id= :site2").setParameter("site1",member_music_list_content_id).setParameter("site2", member_music_list_content_music_id).executeUpdate();
			return  true;
		}
		return  false;
		
		

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
