package _global.obj;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import _global.utils.Constant;

public class EmailSender extends Thread
{
	private String sendTo;
	private String subject;
	private String content;	
	
	public EmailSender(String sendTo, String subject, String content)
	{
		this.sendTo = sendTo;
		this.subject = subject;
		this.content = content;
	}

	@Override
	public void run() {
		SimpleEmail email = new SimpleEmail();
		email.setHostName(Constant.gmailSmtpHost);
		email.setSmtpPort(Constant.gmailSmtpPortTLS);
		try
		{
			email.setAuthentication(Constant.gmailUserName, Constant.gmailPassword);
			email.setFrom(Constant.gmailUserEmail);
			email.addTo(sendTo);
			email.setSubject(subject);			
			email.setMsg(content);
			email.setCharset("utf-8");
			email.setSSLOnConnect(true);
			email.setStartTLSEnabled(true);
			email.send();
		} 
		catch (EmailException e)
		{
			e.printStackTrace();
		}
	}
	
}
