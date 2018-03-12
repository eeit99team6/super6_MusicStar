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
	 
}
