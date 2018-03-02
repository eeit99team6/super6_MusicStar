package _global.utils;

import java.util.List;
import java.util.Map;

public class Checker
{

	/**
	 * @param checkString 要確認不為null且有值存在的字串
	 * @return 不為null且有值存在 : true ; 為null或沒有值存在 : false
	 */
	public static synchronized boolean notEmpty(String checkString)
	{
		if (checkString != null && checkString.trim().length() > 0)
			return true;
		return false;
	}

	/**
	 * @param checkCharacter 要確認不為null且有值存在的字元
	 * @return 不為null且有值存在 : true ; 為null或沒有值存在 : false
	 */
	public static synchronized boolean notEmpty(Character checkCharacter)
	{
		if (checkCharacter != null && checkCharacter.toString().length() > 0)
			return true;
		return false;
	}

	/**
	 * @param checkMap 要確認不為null且有值存在的Map
	 * @return 不為null且有值存在 : true ; 為null或沒有值存在 : false
	 */
	public static synchronized boolean notEmpty(Map<?, ?> checkMap)
	{
		if (checkMap != null && !checkMap.isEmpty())
			return true;
		return false;
	}

	/**
	 * @param checkList 要確認不為null且有值存在的List
	 * @return 不為null且有值存在 : true ; 為null或沒有值存在 : false
	 */
	public static synchronized boolean notEmpty(List<?> checkList)
	{
		if (checkList != null && !checkList.isEmpty())
			return true;
		return false;
	}
}
