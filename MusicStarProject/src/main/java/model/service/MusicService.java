package model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MemberBean;
import model.bean.MusicBean;
import model.dao.MemberDAO;
import model.dao.MusicDAO;

@Service
@Transactional
public class MusicService
{
	@Autowired
	MusicDAO musicdao;

	@Autowired
	MemberDAO memberdao;
	
	// insert 
	public MusicBean insert(String memberId ,MusicBean bean){
	       MemberBean temp = memberdao.selectById(memberId);
		if(bean!=null && temp!=null) {
			MusicBean musicbean = musicdao.inisert(bean);
			return musicbean;
	   }	
		return null;
	}
	
	// select all
	public List<MusicBean> selectAll(String memberId ,MusicBean bean){
	       MemberBean temp = memberdao.selectById(memberId);
		if(bean!=null && temp!=null) {
			List<MusicBean> musicbean = musicdao.select();
			return musicbean;
	   }	
		return null;
	}

	// select by id 
		public List<MusicBean> selectById(String memberId){
			if(memberId!=null && memberId.length()!=0) {
				List<MusicBean> musicbean = musicdao.selectById(memberId);
				return musicbean;
		   }	
			return null;
		}
	
	// update 
	 public MusicBean update(String memberId ,MusicBean bean, Integer musicId){
	       MemberBean temp = memberdao.selectById(memberId);
		if(bean!=null && temp!=null) {
			MusicBean musicbean  = musicdao.update(musicId, bean);
			return musicbean;
	   }	
		return null;
	}
	
	// delete 
	public boolean delete(String memberId ,MusicBean bean) {
		MemberBean temp = memberdao.selectById(memberId);
		if(bean!=null && temp!=null) {
			return musicdao.delete(bean);
	   }	
		return false;
	} 
	
	
	/**
	 * @author Yeh
	 */
	
	// select all
	public List<MusicBean> select(){
		List<MusicBean> list = musicdao.select();
		return list;
	}
	
	// select by music_style_id
	public List<MusicBean> selectStyle(Integer music_style_id){
		List<MusicBean> style = null;
		if(music_style_id != null){
			style = musicdao.selectStyle(music_style_id);
		}
		return style;
	}
	
	// select by music_member_id
	public List<MusicBean> selectSinger(String music_member_id){
		List<MusicBean> singer = null;
		if(music_member_id != null){
			singer = musicdao.selectSinger(music_member_id);
		}
		return singer;
	}
	
	// select by music_member_id , music_style_id
	public List<MusicBean> selectSingerStyle(String music_member_id , Integer music_style_id){
		List<MusicBean> singerStyle = null;
		if(music_member_id != null){
			singerStyle = musicdao.selectSingerStyle(music_member_id, music_style_id);
		}
		return singerStyle;
	}
	
	// select by keyword
	public MusicBean selectMusicName(String keyword){
		MusicBean musicName = null;
		if(keyword != null){
			musicName = musicdao.selectMusicName(keyword);
			return musicName;
		}
		return null;
	}
	
	// select by music_name
	public List<MusicBean> selectAllMusicName(String music_name){
		List<MusicBean> allMusicName = null;
		if(music_name != null){
			allMusicName = musicdao.selectAllMusicName(music_name);
		}
		return allMusicName;
	}
	
}
