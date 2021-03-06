package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _global.utils.Processor;
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
	 * @author Phil 2018.03.22
	 */
	public MemberBean selectById(String mbrId)
	{
		return mbrId != null ? getSession().createQuery("from MemberBean where mbrId = :mbrId", MemberBean.class).setParameter("mbrId", mbrId).uniqueResult() : null;
	}
	
	/**
	 * @author Phil 2018.03.19
	 */
	public MemberBean selectByIdAndEmail(String pwdForgetId, String pwdForgetEmail)
	{
		return pwdForgetId != null && pwdForgetEmail != null ? getSession().createQuery("from MemberBean where mbrId = :pwdForgetId and mbrEmail = :pwdForgetEmail", MemberBean.class).setParameter("pwdForgetId", pwdForgetId).setParameter("pwdForgetEmail", pwdForgetEmail).uniqueResult() : null;
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
	public boolean update(MemberBean bean)
	{
		if(bean != null) {			
			getSession().update(bean);
			return true;
		}else {
			return false;
		}	
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
	
	/**
	 * @author Phil 2018.03.19
	 */
	public MemberBean addResetLink(String mbrId, String mbrEmail ,String mbrPwdResetLink, Date mbrPwdResetLimit) {
		if (mbrId != null && mbrEmail != null)
		{
			MemberBean bean = getSession().createQuery("from MemberBean where mbrId = :mbrId and mbrEmail = :mbrEmail", MemberBean.class).setParameter("mbrId", mbrId).setParameter("mbrEmail", mbrEmail).uniqueResult();
			if (bean != null && mbrPwdResetLink != null && mbrPwdResetLimit != null)
			{
				bean.setMbrPwdResetLink(mbrPwdResetLink);
				bean.setMbrPwdResetLimit(mbrPwdResetLimit);
				return bean;
			}
		}
		return null;
	}
	
	/**
	 * @author Phil 2018.03.19
	 */
	public MemberBean selectByResetLink(String mbrPwdResetLink) {		
		return mbrPwdResetLink != null ? getSession().createQuery("from MemberBean where mbrPwdResetLink = :mbrPwdResetLink and mbrPwdResetLimit >= :today", MemberBean.class).setParameter("mbrPwdResetLink", mbrPwdResetLink).setParameter("today", Processor.getCurrentTwDate()).uniqueResult() : null;
	}
	
	
}
