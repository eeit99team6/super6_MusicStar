package model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MemberBean;
import model.dao.MemberDAO;

@Service
@Transactional
public class MemberService
{
	@Autowired
	private MemberDAO memberDAO;

	public boolean register(MemberBean bean)
	{
		String mbrId = null;
		if (bean != null && (mbrId = bean.getMbrId()) != null && memberDAO.selectById(mbrId) == null)
		{
			bean.setMbrRegisterDate(new Date());
			return memberDAO.insert(bean) != null ? true : false;
		} else
		{
			return false;
		}
	}

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

}
