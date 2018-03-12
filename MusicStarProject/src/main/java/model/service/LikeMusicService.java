package model.service;

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
}
