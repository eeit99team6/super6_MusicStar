package model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MusicContestPlayerBean;
import model.bean.MusicListContentBean;
import model.dao.MusicListContentDAO;

@Service
@Transactional
public class MusicListContentService {
	@Autowired
	private MusicListContentDAO musicListContentDAO;
	
	@Transactional
	public List<MusicListContentBean> select(MusicListContentBean bean){
		List<MusicListContentBean> result = null;
		if(bean !=null && bean.getMember_music_list_content_id()!=null) {
			List<MusicListContentBean> temp = musicListContentDAO.select(bean.getMember_music_list_content_id());
			if (temp != null) {
				result = new ArrayList<MusicListContentBean>();
				result.addAll(temp);
			}
		} else {
			result = musicListContentDAO.select(bean.getMember_music_list_content_id());
		}
		return result;
	}
	public boolean delete(MusicListContentBean bean) {
		boolean result = false;
		if(bean!=null) {
			result = musicListContentDAO.deletefirst(bean.getMember_music_list_content_id());
		}
		return result;
	}
	

	public List<MusicListContentBean> selectmusiclistiditem(MusicListContentBean bean){
		List<MusicListContentBean> result = null;
		if(bean!=null && bean.getMember_music_list_content_id()!=null) {
			List<MusicListContentBean> temp = musicListContentDAO.selectmusiclistiditem(bean.getMember_music_list_content_id());
			if(temp!=null) {
				result = new ArrayList<MusicListContentBean>();
				result.addAll(temp);
			}
		}else {
			return result;
		}
		return result;
	} 
	

}