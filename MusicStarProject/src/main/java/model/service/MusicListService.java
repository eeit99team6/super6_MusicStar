package model.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.bean.MusicListBean;
import model.dao.MusicListContentDAO;
import model.dao.MusicListDAO;

@Service
@Transactional
public class MusicListService {
	@Autowired
	private MusicListDAO musicListDAO;

	@Autowired
	private MusicListContentDAO musicListContentDAO;
	@Transactional
	public List<MusicListBean> select(MusicListBean bean) {
		List<MusicListBean> result = null;
		if (bean != null && bean.getMember_music_list_id()!=null) {
			MusicListBean temp = musicListDAO.select(bean.getMember_music_list_id());
			if (temp != null) {
				result = new ArrayList<MusicListBean>();
				result.add(temp);
			}
		} else {
			result = musicListDAO.select();
		}
		return result;
	}

	public MusicListBean insert(MusicListBean bean) {
		MusicListBean result = null;
		if (bean != null) {
			result = musicListDAO.insert(bean);
		}
		return result;
	}
	public MusicListBean update(MusicListBean bean) {
		MusicListBean result = null;
		if(bean!=null) {
			result = musicListDAO.update(bean.getMember_music_list_id(),bean.getMember_music_list_name(), bean.getMember_music_list_member_id(),
					bean.getMember_music_list_description(), bean.getMember_music_list_quantity());
		}
		return result;
	}
	public boolean delete(MusicListBean bean) {
		boolean result1 = false;
		boolean result = false;

		if(bean!=null) {
			result1=musicListContentDAO.deletefirst(bean.getMember_music_list_id());
			System.out.println("result1 OK");
			if(result1) {
			result=musicListDAO.delete(bean.getMember_music_list_id());
			System.out.println("result OK");
			}	
			else if(!result1){
				result=musicListDAO.delete(bean.getMember_music_list_id());
				System.out.println("result OK");
			}
		}
		return result1;
	}

}