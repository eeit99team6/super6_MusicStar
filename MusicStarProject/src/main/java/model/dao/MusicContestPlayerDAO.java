package model.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.MusicContestPlayerBean;
import model.bean.MusicContestVoteBean;

@Repository
public class MusicContestPlayerDAO {
	
	@Autowired
	private SessionFactory sessionFactory = null;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
//用在搜尋該比賽排行前3名	
	public List<MusicContestPlayerBean> selectContestIdTop3(Integer music_contest_id){
		Query query = this.getSession().createQuery("from MusicContestPlayerBean mb join MusicBean mc on mb.music_id=mc.music_id join MusicContestBean md on mb.music_contest_id=md.music_contest_id where mb.music_contest_id=?0 order by music_contest_players_votes desc");
		query.setParameter("0", music_contest_id);
		query.setMaxResults(3);
		return (List<MusicContestPlayerBean>) query.list();
	}
	
//用在搜尋該比賽排行前10名		
	public List<MusicContestPlayerBean> selectContestIdTop10(Integer music_contest_id){
		Query query = this.getSession().createQuery("from MusicContestPlayerBean mb join MusicBean mc on mb.music_id=mc.music_id join MusicContestBean md on mb.music_contest_id=md.music_contest_id where mb.music_contest_id=?0 order by music_contest_players_votes desc");
		query.setParameter("0", music_contest_id);
		query.setMaxResults(10);
		return (List<MusicContestPlayerBean>) query.list();
	}
	
//用在搜尋有幾個比賽
	public List<MusicContestPlayerBean> selectContestIdCount(){
		Query query = this.getSession().createQuery("select count(DISTINCT music_contest_id) from MusicContestPlayerBean");
		return (List<MusicContestPlayerBean>) query.list();
	}
	

	public List<MusicContestPlayerBean> selectContestId(Integer music_contest_id){
		Query query = this.getSession().createQuery("from MusicContestPlayerBean where music_contest_id=?0");
		query.setParameter("0", music_contest_id);
		return (List<MusicContestPlayerBean>) query.list();
	}
	
	
	public List<MusicContestPlayerBean> selectAll(){
		Query query = this.getSession().createQuery("from MusicContestPlayerBean");
		return (List<MusicContestPlayerBean>) query.list();
	}
	
	public MusicContestPlayerBean insert(MusicContestPlayerBean bean) {		
		if(bean!=null) {
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}
		
	public MusicContestPlayerBean update(MusicContestPlayerBean bean) {
		MusicContestPlayerBean result =null;
		if(bean!=null && bean.getMusic_contest_id()!=null && bean.getMusic_contest_player_id()!=null) {
			result.setMusic_id(bean.getMusic_id());
			return result;
		}
		return null;
	}
		
	public boolean delete(Integer music_contest_id,String music_contest_player_id) {
		MusicContestPlayerBean result = null;
		if(music_contest_id!=null && music_contest_player_id!=null) {
			this.getSession().delete(result);
			return true;
		}
		return false;
	}
		
	/**
	 * 取得指定賽事參賽者ID資料
	 * @author Phil 2018.03.12
	 * @param musicCtstId 賽事的ID
	 * @param musicCtstPlayerId 參賽者的ID
	 * @return 指定賽事參賽者的資料
	 */
	public MusicContestPlayerBean selectById(Integer musicCtstId, String musicCtstPlayerId)
	{
		return musicCtstId != null && musicCtstPlayerId != null
				? getSession().createQuery("from MusicContestPlayerBean where music_contest_id = ? and music_contest_player_id = ?", MusicContestPlayerBean.class).setParameter(0, musicCtstId)
						.setParameter(1, musicCtstPlayerId).uniqueResult()
				: null;
	}
	
	/**
	 * 取得指定賽事ID的參賽者資料
	 * @author Phil 2018.03.12
	 * @param musicCtstId 賽事的ID
	 * @return 指定賽事參賽者資料的List
	 */
	public List<Map> selectPlayersByMusicCtstId(Integer musicCtstId)
	{
		String query = "select new Map (m.music_name as musicName, m.music_photo as musicPhoto, m.music_description as musicDescription, m.music_lyrics as musicLyrics, m.music_link as musicLink, m.music_member_id as musicCtstPlayerId, mcp.music_contest_players_votes as musicCtstPlayerVotes) from MusicContestPlayerBean mcp join MusicBean m on mcp.music_id = m.music_id where mcp.music_contest_id = :musicCtstId";
		return musicCtstId != null ? getSession().createQuery(query,Map.class).setParameter("musicCtstId", musicCtstId).list(): null;
	}
	
	/**
	 * 取得所有賽事的參賽者資料
	 * @author Phil 2018.03.12
	 * @return 所有賽事參賽者資料的List
	 */
	public List<Map> selectAllMusicCtstPlayers()
	{
		String query = "select new Map (m.music_name as musicName, m.music_photo as musicPhoto, m.music_description as musicDescription, m.music_lyrics as musicLyrics, m.music_link as musicLink, m.music_member_id as musicCtstPlayerId, mcp.music_contest_players_votes as musicCtstVotes) from MusicContestPlayerBean mcp join MusicBean m on mcp.music_id = m.music_id";
		return getSession().createQuery(query,Map.class).list();
	}
	
	// 選擇  指定的賽事 和 指定的 member_id 
	public long selectByMIdAndMusId(Integer musicId, String memberId) {
		Query query = this.getSession().createQuery("select count(*) from MusicContestPlayerBean m where m.music_contest_id = :mid and m.music_contest_player_id = :mplayid", Long.class);
		query.setParameter("mid", musicId);
		query.setParameter("mplayid", memberId);
		return (Long)query.uniqueResult();
		}

	/**
	 * 
	 * 增加投票數
	 * @author Phil 2018.03.12
	 */
	public synchronized void addVotes(MusicContestVoteBean bean) {
		Integer musicCtstId = null;
		String musicCtstPlayerId = null;
		if (bean != null && (musicCtstId = bean.getMusic_contest_id()) != null && (musicCtstPlayerId = bean.getMusic_contest_player_id()) != null) {
			MusicContestPlayerBean mcpb = selectById(musicCtstId, musicCtstPlayerId);
			Integer votes = mcpb.getMusic_contest_players_votes();
			if(votes == null) {
				votes = 0;
			}
			mcpb.setMusic_contest_players_votes(++votes);
		}
	}
	
}

