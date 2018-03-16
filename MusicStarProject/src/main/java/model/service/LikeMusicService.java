package model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.LikeMusicBean;
import model.dao.LikeMusicDAO;

@Service
@Transactional
public class LikeMusicService {
	@Autowired
	private LikeMusicDAO likeMusicDAO;

	public LikeMusicBean insert(LikeMusicBean bean) {
		LikeMusicBean result = null;
		if (bean != null) {
			result = likeMusicDAO.insert(bean);
		}
		return result;
	}
	
	/**
	 * @author james.pu 2018.03.13 11:00
	 * @return 用在leaderboards-like排行
	 */
	public List<LikeMusicBean> selectTotalLike(LikeMusicBean bean){
		List<LikeMusicBean> result = null;
		if(bean!=null) {
			List<LikeMusicBean> temp = likeMusicDAO.selectTotalLike();
			if(temp!=null) {
				result =new ArrayList<LikeMusicBean>();
				result.addAll(temp);
			}
		}else {
			return result;			
		}
		return result;		
	}
	
	/**
	 * @author james.pu 2018.03.13 11:00
	 * @return 用在新增likes
	 */
	public boolean insertLike(LikeMusicBean bean) {
		if(bean!=null) {
			if(likeMusicDAO.insertLike(bean) != null) {				
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * @author yeh 2018.03.15 10:30
	 * @return 用在找likes數量
	 */
	
	//回傳值利用List<>裝Map,並在此方法內利用DAO呼叫selectLikeCount()將select出來的結果回傳
	public List<Map> selectLikeCount(LikeMusicBean bean) {

		List<Map> temp = likeMusicDAO.selectLikeCount();
		if (temp != null) {
			return temp;
		}

		return null;
	}
	
	/**
	 * @author james.pu 2018.03.16 15:40
	 * @return 用在刪除likes
	 */
	public boolean deleteLike(LikeMusicBean bean) {
		boolean result =false;
		if(bean!=null) {
			result = likeMusicDAO.deletelike(bean.getLikes_member_id(), bean.getLikes_music_id());
			return true;
		}
		return result;
	}
	
}
