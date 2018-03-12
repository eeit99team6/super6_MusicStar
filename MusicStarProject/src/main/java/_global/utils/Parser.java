package _global.utils;

import java.io.Reader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Parser
{
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	SimpleDateFormat sdfForTW = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
	
	/**
	 * @param parseStr 要轉成Integer的字串
	 * @return 轉換成功 : Integer  ； 轉換失敗 : null
	 */
	public static Integer parseInt(String parseStr)
	{
		try
		{
			return Integer.parseInt(parseStr);
		} catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * @param src 要轉成JSON字串的來源物件
	 * @return JSON字串
	 */
	public static String toJson(Object src)
	{
		return gson.toJson(src);
	}
	
	/**
	 * @param <T> JSON物件的類型
	 * @param jsonString 要轉成JSON物件的字串
	 * @param classOfT 所需JSON物件所屬的Class
	 * @return JSON物件
	 */
	public static <T> T parseJson(String jsonString, Class<T> classOfT)
	{
		return gson.fromJson(jsonString,classOfT);
	}
	
	/**
	 * @param reader 要讀取的JSON物件的Reader
	 * @param classOfT 要轉成JSON物件的Class
	 * @return JSON物件
	 */
	public static <T> T parseJson(Reader reader, Class<T> classOfT)
	{
		return gson.fromJson(reader,classOfT);
	}
	
	/** 
	 *   本方法可對字串message(Plaintext, 明文)加密，然後將加密後的字串
	 *   (Ciphertext, 密文)傳回。
	 * 
	 * @param message : 明文(要加密的字串) 
	 * @return 加密後的 message
	 */
	public static String encryptString(String message) 
    {
	//  DES : Data Encryption Standard, 一種對稱式加密演算法。
	//           美國聯邦政府於1976年定為聯邦資料處理標準(FIPS)，它的
	//           金鑰則必須是7個位元組、加密區塊(Cipher Block)固定為8個位元組。
	//           DES目前已被視為是一種不安全的演算法。
	//  AES : Advanced Encryption Standard, 一種對稱式加密演算法。
	//           (美國聯邦政府於2001年納入FIPS 140-2標準)，此種演算法
	//           的Cipher Block固定為16個位元組。金鑰則必須是16個位元組、
	//           24個位元組或32個位元組(即128個位元、192個位元或256個位元)。
	//  ECB : Electronic CookBook, 一種資料的加密方式，這種加密方式採取
	//           每個區塊(如8個或16個位元組)獨立加密，即加密任ㄧ區塊時與其它區塊
	//           無關。獨立壓縮有優點也有缺點。
	//           優點為可以由多個處理器來平行處理ㄧ個很大的資料。缺點為如果資料
	//           的內容有重複出現的部分，而且重複資料的長度剛好與加密區塊等長，
	//           則這些重複出現的部分經過加密後會出現相同的結果。
	//  PKCS5Padding: 如果要加密的資料不是8個(如DES加密演算法)或
	//           16個(如AES加密演算法)位元組的整數倍，則必須在欲加密資料的
	//           尾端加入若干個位元組來湊成整數倍。PKCS5Padding是一種
	//           補足不足位元組的方法。
	String encryptedString = "";
	try {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
		SecretKeySpec secretKey = new SecretKeySpec(Constant.securityKey.getBytes(), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		encryptedString = DatatypeConverter.printBase64Binary(cipher.doFinal(message.getBytes()));
	} catch (InvalidKeyException e) {
		e.printStackTrace();
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	} catch (NoSuchPaddingException e) {
		e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
		e.printStackTrace();
	} catch (BadPaddingException e) {
		e.printStackTrace();
	}
	return encryptedString;
	}
	
	/** 
	 *   本方法可對加密之字串(Ciphertext)解密，key為當初加密時的金鑰
	 *   傳回值為解密後的字串(Plaintext)
	 *  
	 * @param encryptMessage : 加密文(要解密的字串) 
	 * @return 解密後的 message
	 */
	public static String decryptString(String encryptMessage)
			 {
		String decryptedString = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
			SecretKeySpec secretKey = new SecretKeySpec(Constant.securityKey.getBytes(), "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] b = DatatypeConverter.parseBase64Binary(encryptMessage);
			decryptedString = new String(cipher.doFinal(b));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return decryptedString;
	}
	
	/**
	 * @param date 要轉成字串的Date物件
	 * @return "yyyy-MM-dd"日期格式的字串
	 */
	public String formatDate(Date date) {
		return sdfForTW.format(date);
	}
	
	/**
	 * @param source 要轉成Date的字串
	 * @return 轉換成功 : Date物件  ； 轉換失敗 : null
	 */
	public Date parseDate(String source) {
		try {
			return sdfForTW.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
