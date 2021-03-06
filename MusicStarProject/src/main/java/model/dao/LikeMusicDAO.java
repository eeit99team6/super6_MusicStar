package model.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.LikeMusicBean;
import model.bean.MusicListContentBean;

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
	 * @author james.pu 2018.03.20 11:45
	 * @return 用在leaderboards-like排行
	 */
	public List<LikeMusicBean> selectTotalLike(){
		Query query =this.getSession().createQuery(
				"select mc.music_photo, mc.music_link, mc.music_name, mc.music_member_id, mk.likes_music_id, (count(*)+ISNULL(mc.music_likes,0)) as total from LikeMusicBean mk join MusicBean mc on mk.likes_music_id=mc.music_id\r\n" + 
				"group by mk.likes_music_id, mc.music_name, mc.music_photo, mc.music_link, mc.music_member_id, mc.music_likes order by total desc");
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
	
	/**
	 * @author yeh 2018.03.15 10:30
	 * @return 用在找likes數量
	 */	
	
	//利用List<>裝Map, 並在HQL指令中new一個Map包住(取到的likes_music_id, count)當作(key, value)
	//因此select group by取出的值(key, value)會對應到(likes_music_id, 與group by之後的count值)
	public List<Map> selectLikeCount() {
		Query query = this.getSession().createQuery("select new Map (mb.music_id as likes_music_id, (ISNULL(count(lmb.likes_member_id),0) + ISNULL(mb.music_likes,0)) as count) from LikeMusicBean lmb right join MusicBean mb on lmb.likes_music_id = mb.music_id group by mb.music_id, mb.music_likes",Map.class);
		return query.list();	
	}
	
	/**
	 * @author james.pu 2018.03.16 15:40
	 * @return 用在刪除likes
	 */
	public boolean deletelike(String likes_member_id, Integer likes_music_id) {
		Query query = this.getSession().createQuery("from LikeMusicBean where likes_member_id= :site1 and likes_music_id= :site2");
		query.setParameter("site1", likes_member_id);
		query.setParameter("site2", likes_music_id);
		LikeMusicBean result= (LikeMusicBean) query.uniqueResult();
		if(result!=null) {
			this.getSession().delete(result);
			return true;
		}
		return false;
	}
	
	
	/**
	 * @author yeh 2018.03.20 15:00
	 * @return 用在搜尋某位會員按哪些歌曲讚
	 */
	public List<LikeMusicBean> selectLikeMusic(String likes_member_id){
		Query query = this.getSession().createQuery("from LikeMusicBean where likes_member_id = :likes_member_id");
		query.setParameter("likes_member_id", likes_member_id);
		return (List<LikeMusicBean>)query.list();
	}
}
