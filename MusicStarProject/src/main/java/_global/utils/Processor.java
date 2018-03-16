package _global.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.time.ZoneId;
import java.util.Collections;

import javax.servlet.http.Cookie;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import _global.obj.EmailSender;
import _global.obj.HtmlEmailSender;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Processor
{
	private static GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
			.setAudience(Collections.singletonList("49274278323-nm9ajs8osl7l4gv8vji8r3l8au6go1ra.apps.googleusercontent.com")).build();
	private static OkHttpClient okHttpClient = new OkHttpClient();

	/**
	 * 向指定url帶上參數發出HttpGet請求，並將回傳的內容轉為指定POJO類型的JSON物件
	 * @param <T> JSON物件的類型
	 * @param url 以GET發出請求的url
	 * @param classOfT 回應的JSON物件類型所屬的Class
	 * @param params 查詢字串，例:"q=1","p=2",...
	 * @return 請求成功 ： 指定類型的JSON物件 ； 請求失敗 ： null
	 */
	public static <T> T runHttpGet(String url, Class<T> classOfT, String... params)
	{
		if (params != null)
		{
			StringBuffer sb = new StringBuffer(url + "?");
			for (String param : params)
			{
				sb.append(param + "&");
			}
			String str = sb.toString();
			url = str.substring(0, str.length() - 1);
		}

		try
		{
			Request request = new Request.Builder().url(url).build();
			Response response = okHttpClient.newCall(request).execute();
			return Parser.parseJson(response.body().charStream(), classOfT);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 向指定url帶上參數發出HttpGet請求，並將回傳的內容轉為字串
	 * @param url 以GET發出請求的url
	 * @param params 查詢字串，例:"q=1","p=2",...
	 * @return 請求成功 ： 回傳的內容字串 ； 請求失敗 ： null
	 */
	public static String runHttpGet(String url, String... params)
	{
		if (params != null)
		{
			StringBuffer sb = new StringBuffer(url + "?");
			for (String param : params)
			{
				sb.append(param + "&");
			}
			String str = sb.toString();
			url = str.substring(0, str.length() - 1);
		}

		try
		{
			Request request = new Request.Builder().url(url).build();
			Response response;
			response = okHttpClient.newCall(request).execute();
			return response.body().string();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取得指定檔案路徑的byte[]
	 * @param filePath 檔案的路徑
	 * @return 成功 :檔案的byte[] ; 失敗:null
	 */
	public static byte[] getBytesFromFilePath(String filePath)
	{
		try
		{
			if (filePath != null)
			{
				File file = new File(filePath);
				byte[] data = new byte[(int) file.length()];

				FileInputStream fis = new FileInputStream(file);
				fis.read(data);
				fis.close();

				return data;
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * 創建一個新的 HTTP Cookie
	 * @param cookieName : Cookie的名稱
	 * @param cookieValue : Cookie的內容
	 * @param cookieMaxAge : Cookie存活時間(單位:秒)
	 * @param contextPath : web應用的contextPath
	 * @return 創建出的 cookie物件
	 */
	public static Cookie createCookie(String cookieName, String cookieValue,int cookieMaxAge,String contextPath)
	{
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(cookieMaxAge);
		cookie.setPath(contextPath);
		return cookie;
	}
	
	/**
	 * 取得當前台灣時區的日期
	 * @return 當前台灣時區日期的 Date物件
	 */
	public static Date getCurrentTwDate()
	{
		return Date.valueOf(java.time.LocalDate.now(ZoneId.of("UTC+8")));
	}
	
	/**
	 * 提供idToken給Google進行驗證，若驗證成功，請Google提供GoogleIdToken(用於取得用戶資料)
	 * @param idToken Google 的 idToken String
	 * @return 正確的idToken : GoogleIdToken ; 不正確的idToken : null
	 */
	public static GoogleIdToken verifyGoogleIdToken(String idToken)
	{
		try
		{
			return googleIdTokenVerifier.verify(idToken);
		} catch (GeneralSecurityException | IOException e)
		{
			e.printStackTrace();
			return null;
		} 
	}
	
	/**
	 * 發送信件到指定的信箱(純文字格式)
	 * @param sendTo 收件人的信箱
	 * @param subject 信件的主題
	 * @param content 純文字格式的信件內容
	 */
	public static void sendMail(String sendTo, String subject, String content) {
		EmailSender sender = new EmailSender(sendTo, subject, content);
		sender.start();
	}
	
	/**
	 * 發送信件到指定的信箱(HTML格式)
	 * @param sendTo 收件人的信箱
	 * @param subject 信件的主題
	 * @param htmlContent HTML格式的信件內容
	 */
	public static void sendHtmlMail(String sendTo, String subject, String htmlContent) {
		HtmlEmailSender sender = new HtmlEmailSender(sendTo, subject, htmlContent);
		sender.start();
	}
	
}
