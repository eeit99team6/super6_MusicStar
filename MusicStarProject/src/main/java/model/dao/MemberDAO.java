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

	public MemberBean selectById(String mbrId)
	{
		return mbrId != null ? getSession().get(MemberBean.class, mbrId) : null;
	}

	public MemberBean selectByGoogleId(String mbrGoogleId)
	{
		return mbrGoogleId != null ? getSession().createQuery("from MemberBean where mbrGoogleId = ?", MemberBean.class).setParameter(0, mbrGoogleId).uniqueResult() : null;
	}

	public List<MemberBean> selectAll()
	{
		return getSession().createQuery("from MemberBean", MemberBean.class).list();
	}

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
