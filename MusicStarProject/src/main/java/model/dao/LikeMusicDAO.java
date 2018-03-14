package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
	
	/**
	 * @author james.pu 2018.03.13 11:00
	 * @return 用在leaderboards-like排行
	 */
	public List<LikeMusicBean> selectTotalLike(){
		Query query =this.getSession().createQuery(
				"select mc.music_photo, mc.music_link, mc.music_name, mc.music_member_id, mk.likes_music_id, count(*) as total from LikeMusicBean mk join MusicBean mc on mk.likes_music_id=mc.music_id\r\n" + 
				"group by mk.likes_music_id, mc.music_name, mc.music_photo, mc.music_link, mc.music_member_id order by total desc");
		return (List<LikeMusicBean>) query.list();	
		}
	
	/**
	 * @author james.pu 2018.03.13 11:00
	 * @return 用在搜尋likes Table
	 */	
	public LikeMusicBean selectById(String likes_member_id, Integer likes_music_id) {
		Query query =this.getSession().createQuery("from LikeMusicBean where likes_member_id= :site1 and likes_music_id= :site2");
		query.setParameter("site1", likes_member_id);
		query.setParameter("site2", likes_music_id);
		return  (LikeMusicBean) query.uniqueResult();
	}
	
	/**
	 * @author james.pu 2018.03.13 11:00
	 * @return 用在新增likes
	 */	
	public LikeMusicBean insertLike(LikeMusicBean bean) {
		String likes_member_id = null;
		Integer likes_music_id = null;
		if(bean!=null &&  (likes_member_id=bean.getLikes_member_id())!=null && (likes_music_id=bean.getLikes_music_id())!=null&& selectById(likes_member_id, likes_music_id)==null) {
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}
	
	
}
