package model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _global.utils.Processor;
import model.bean.MemberBean;
import model.dao.MemberDAO;

@Service
@Transactional
public class MemberService
{
	@Autowired
	private MemberDAO memberDAO;

	/**
	 * @author Phil 2018.03.15
	 */
	public boolean register(MemberBean bean)
	{
		String mbrId = null;
		if (bean != null && (mbrId = bean.getMbrId()) != null && memberDAO.selectById(mbrId) == null)
		{
			bean.setMbrRegisterDate(Processor.getCurrentTwDate());
			return memberDAO.insert(bean) != null ? true : false;
		} else
		{
			return false;
		}
	}

	/**
	 * @author Phil 2018.03.15
	 */
	public MemberBean login(MemberBean bean)
	{
		MemberBean mb = null;
		String mbrId = bean.getMbrId();
		String mbrPwd = bean.getMbrPwd();
		if (bean != null && mbrId != null && mbrPwd != null)
		{
			mb = memberDAO.selectById(mbrId);
			if (mb != null && mbrPwd.equals(mb.getMbrPwd()))
			{
				return mb;
			}
		}
		return null;
	}

	/**
	 * @author Phil 2018.03.15
	 */
	public MemberBean googleLogin(MemberBean bean)
	{
		MemberBean mb = null;
		String mbrGoogleId = null;
		if (bean != null && (mbrGoogleId = bean.getMbrGoogleId()) != null && (mb = memberDAO.selectByGoogleId(mbrGoogleId)) != null)
		{
			return mb;
		}
		return null;
	}

	/**
	 * @author Phil 2018.03.15
	 */
	public MemberBean fbLogin(MemberBean bean)
	{
		MemberBean mb = null;
		String mbrFbId = null;
		if (bean != null && (mbrFbId = bean.getMbrFbId()) != null && (mb = memberDAO.selectByFbId(mbrFbId)) != null)
		{
			return mb;
		}
		return null;
	}

	/**
	 * @author Phil 2018.03.15
	 */
	public List<MemberBean> search(MemberBean bean)
	{
		List<MemberBean> beanList = null;
		String mbrId = null;
		if (bean != null && (mbrId = bean.getMbrId()) != null)
		{
			beanList = new ArrayList<>();
			beanList.add(memberDAO.selectById(mbrId));
			return beanList;
		} else
		{
			return memberDAO.selectAll();
		}
	}

	/**
	 * @author Phil 2018.03.17
	 */
	public boolean update(MemberBean bean)
	{
		if (bean != null)
		{
			return memberDAO.update(bean);
		}
		return false;
	}

	/**
	 * @author Phil 2018.03.19
	 */
	public MemberBean setMbrPwdResetLink(String mbrId, String mbrEmail, String mbrPwdResetLink)
	{
		if (mbrId != null && mbrEmail != null && mbrPwdResetLink != null)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(Processor.getCurrentTwDate());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			return memberDAO.addResetLink(mbrId, mbrEmail, mbrPwdResetLink, calendar.getTime());
		}
		return null;
	}

	/**
	 * @author Phil 2018.03.19
	 */
	public MemberBean checkResetPwdLink(String mbrPwdResetLink)
	{
		if (mbrPwdResetLink != null)
		{
			return memberDAO.selectByResetLink(mbrPwdResetLink);
		}
		return null;
	}

}
