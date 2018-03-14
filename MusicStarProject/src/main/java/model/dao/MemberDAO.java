package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.MemberBean;

@Repository
public class MemberDAO
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
	public MemberBean selectById(String mbrId)
	{
		return mbrId != null ? getSession().get(MemberBean.class, mbrId) : null;
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public MemberBean selectByGoogleId(String mbrGoogleId)
	{
		return mbrGoogleId != null ? getSession().createQuery("from MemberBean where mbrGoogleId = :mbrGoogleId", MemberBean.class).setParameter("mbrGoogleId", mbrGoogleId).uniqueResult() : null;
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public MemberBean selectByFbId(String mbrFbId)
	{
		return mbrFbId != null ? getSession().createQuery("from MemberBean where mbrFbId = :mbrFbId", MemberBean.class).setParameter("mbrFbId", mbrFbId).uniqueResult() : null;
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public List<MemberBean> selectAll()
	{
		return getSession().createQuery("from MemberBean", MemberBean.class).list();
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public MemberBean insert(MemberBean bean)
	{
		String mbrId = null;
		if (bean != null && (mbrId = bean.getMbrId()) != null && selectById(mbrId) == null)
		{
			getSession().save(bean);
			return bean;
		}
		return null;
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public MemberBean update(MemberBean bean)
	{
		MemberBean mb = null;
		String mbrId = null;
		if (bean != null && (mbrId = bean.getMbrId()) != null && (mb = selectById(mbrId)) != null)
		{
			mb.setMbrPwd(bean.getMbrPwd());
			mb.setMbrName(bean.getMbrName());
			mb.setMbrEmail(bean.getMbrEmail());
			mb.setMbrPhoto(bean.getMbrPhoto());
			mb.setMbrGender(bean.getMbrGender());
			mb.setMbrGoogleId(bean.getMbrGoogleId());
			mb.setMbrFbId(bean.getMbrFbId());
			mb.setMbrEmailVerify(bean.getMbrEmailVerify());
			return mb;
		}
		return null;
	}

	/**
	 * @author Phil 2018.03.12
	 */
	public boolean delete(String mbrId)
	{
		MemberBean mb = null;
		if (mbrId != null && (mb = selectById(mbrId)) != null)
		{
			getSession().delete(mb);
			return true;
		}
		return false;
	}

}
