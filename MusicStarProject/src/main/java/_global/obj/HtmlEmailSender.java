package _global.obj;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import _global.utils.Constant;

public class HtmlEmailSender extends Thread
{
	private String sendTo;
	private String subject;
	private String htmlContent;	
	
	public HtmlEmailSender(String sendTo, String subject, String htmlContent)
	{
		this.sendTo = sendTo;
		this.subject = subject;
		this.htmlContent = htmlContent;
	}

	@Override
	public void run() {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(Constant.gmailSmtpHost);
		email.setSmtpPort(Constant.gmailSmtpPortTLS);
		try
		{
			email.setAuthentication(Constant.gmailUserName, Constant.gmailPassword);
			email.setFrom(Constant.gmailUserEmail);
			email.addTo(sendTo);
			email.setSubject(subject);			
			email.setHtmlMsg(htmlContent);
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
