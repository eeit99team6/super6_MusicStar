package _global.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class Checker
{

	private static GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
			.setAudience(Collections.singletonList("49274278323-nm9ajs8osl7l4gv8vji8r3l8au6go1ra.apps.googleusercontent.com")).build();
	
	/**
	 * @param checkString 要確認不為null且有值存在的字串
	 * @return 不為null且有值存在 : true ; 為null或沒有值存在 : false
	 */
	public static boolean notEmpty(String checkString)
	{
		if (checkString != null && checkString.trim().length() > 0)
			return true;
		return false;
	}

	/**
	 * @param checkCharacter 要確認不為null且有值存在的字元
	 * @return 不為null且有值存在 : true ; 為null或沒有值存在 : false
	 */
	public static boolean notEmpty(Character checkCharacter)
	{
		if (checkCharacter != null && checkCharacter.toString().length() > 0)
			return true;
		return false;
	}

	/**
	 * @param checkMap 要確認不為null且有值存在的Map
	 * @return 不為null且有值存在 : true ; 為null或沒有值存在 : false
	 */
	public static boolean notEmpty(Map<?, ?> checkMap)
	{
		if (checkMap != null && !checkMap.isEmpty())
			return true;
		return false;
	}

	/**
	 * @param checkList 要確認不為null且有值存在的List
	 * @return 不為null且有值存在 : true ; 為null或沒有值存在 : false
	 */
	public static boolean notEmpty(List<?> checkList)
	{
		if (checkList != null && !checkList.isEmpty())
			return true;
		return false;
	}
	
	/**
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
}
