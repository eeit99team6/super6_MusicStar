package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.MusicContestVoteBean;

@Repository
public class MusicContestVoteDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public MusicContestVoteBean selectById(Integer musicCtstId,String musicCtstVoterId)
	{
		return musicCtstId != null ? getSession().createQuery("from MusicContestVoteBean where music_contest_id = :musicCtstId and voter_id = :musicCtstVoterId", MusicContestVoteBean.class).setParameter("musicCtstId", musicCtstId).setParameter("musicCtstVoterId", musicCtstVoterId).uniqueResult() : null;
	}
	
	/**
	 * @author Phil 2018.03.12
	 */
	public List<MusicContestVoteBean> selectByMusicCtstId(Integer musicCtstId)
	{
		return musicCtstId != null ? getSession().createQuery("from MusicContestVoteBean where music_contest_id = :musicCtstId", MusicContestVoteBean.class).setParameter("musicCtstId", musicCtstId).list() : null;
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public List<MusicContestVoteBean> selectByMusicCtstVoterId(String musicCtstVoterId)
	{
		return musicCtstVoterId != null ? getSession().createQuery("from MusicContestVoteBean where voter_id = :musicCtstVoterId", MusicContestVoteBean.class).setParameter("musicCtstVoterId", musicCtstVoterId).list() : null;
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public List<MusicContestVoteBean> selectAll()
	{
		return getSession().createQuery("from MusicContestVoteBean", MusicContestVoteBean.class).list();
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public MusicContestVoteBean insert(MusicContestVoteBean bean)
	{
		Integer musicCtstId = null;
		String musicCtstVoterId = null;
		if (bean != null && (musicCtstId = bean.getMusic_contest_id()) != null && (musicCtstVoterId = bean.getVoter_id()) != null && selectById(musicCtstId, musicCtstVoterId) == null)
		{
			getSession().save(bean);
			return bean;
		}
		return null;
	}
	
	/**
	 * @author Phil 2018.03.12
	 */
	public boolean delete(	Integer musicCtstId, String musicCtstVoterId)
	{
		MusicContestVoteBean mcvb = null;
		if (musicCtstId != null && musicCtstVoterId != null && (mcvb = selectById(musicCtstId,musicCtstVoterId)) != null)
		{
			getSession().delete(mcvb);
			return true;
		}
		return false;
	}

}
