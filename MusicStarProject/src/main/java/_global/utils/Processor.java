package _global.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Processor
{

	private static OkHttpClient okHttpClient = new OkHttpClient();

	/**
	 * @param <T> JSON物件的類型
	 * @param url 以GET發出請求的url
	 * @param classOfT 回應的JSON物件所屬的Class
	 * @param params 查詢字串，例:"q=1","p=2",...
	 * @return 回應的JSON物件
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
			Response response;
			response = okHttpClient.newCall(request).execute();
			return Parser.parseJson(response.body().charStream(), classOfT);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param url 以GET發出請求的url
	 * @param params 查詢字串，例:"q=1","p=2",...
	 * @return 回應的JSON字串
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

}
