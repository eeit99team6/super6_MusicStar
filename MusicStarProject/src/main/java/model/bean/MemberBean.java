package model.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import _global.utils.Parser;

@Entity
@Table(name = "MEMBERS")
public class MemberBean
{
	@Id
	@Column(name = "MEMBER_ID")
	String mbrId;
	@Column(name = "MEMBER_PASSWORD")
	String mbrPwd;
	@Column(name = "MEMBER_NAME")
	String mbrName;
	@Column(name = "MEMBER_GENDER")
	Character mbrGender;
	@Column(name = "MEMBER_EMAIL")
	String mbrEmail;
	@Column(name = "MEMBER_PHOTO")
	String mbrPhoto;
	@Column(name = "MEMBER_GOOGLE_ID")
	String mbrGoogleId;
	@Column(name = "MEMBER_FB_ID")
	String mbrFbId;
	@Column(name = "MEMBER_EMAIL_VERIFY")
	Boolean mbrEmailVerify;
	@Column(name = "MEMBER_REGISTER_DATE")
	Date mbrRegisterDate;

	public String getMbrId()
	{
		return mbrId;
	}

	public void setMbrId(String mbrId)
	{
		this.mbrId = mbrId;
	}

	public String getMbrPwd()
	{
		return mbrPwd;
	}

	public void setMbrPwd(String mbrPwd)
	{
		this.mbrPwd = mbrPwd;
	}

	public String getMbrName()
	{
		return mbrName;
	}

	public void setMbrName(String mbrName)
	{
		this.mbrName = mbrName;
	}

	public Character getMbrGender()
	{
		return mbrGender;
	}

	public void setMbrGender(Character mbrGender)
	{
		this.mbrGender = mbrGender;
	}

	public String getMbrEmail()
	{
		return mbrEmail;
	}

	public void setMbrEmail(String mbrEmail)
	{
		this.mbrEmail = mbrEmail;
	}

	public String getMbrPhoto()
	{
		return mbrPhoto;
	}

	public void setMbrPhoto(String mbrPhoto)
	{
		this.mbrPhoto = mbrPhoto;
	}

	public String getMbrGoogleId()
	{
		return mbrGoogleId;
	}

	public void setMbrGoogleId(String mbrGoogleId)
	{
		this.mbrGoogleId = mbrGoogleId;
	}

	public String getMbrFbId()
	{
		return mbrFbId;
	}

	public void setMbrFbId(String mbrFbId)
	{
		this.mbrFbId = mbrFbId;
	}



	public Boolean getMbrEmailVerify()
	{
		return mbrEmailVerify;
	}

	public void setMbrEmailVerify(Boolean mbrEmailVerify)
	{
		this.mbrEmailVerify = mbrEmailVerify;
	}

	public Date getMbrRegisterDate()
	{
		return mbrRegisterDate;
	}

	public void setMbrRegisterDate(Date mbrRegisterDate)
	{
		this.mbrRegisterDate = mbrRegisterDate;
	}

	@Override
	public String toString()
	{
		return Parser.toJson(this);
	}
}
