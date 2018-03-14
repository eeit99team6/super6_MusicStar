package model.service;

import java.util.ArrayList;
import java.util.List;

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
	
	
}
