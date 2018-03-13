package _global.utils;

import java.util.List;
import java.util.Map;

public class Checker
{
	
	/**
	 * 檢查物件是否不為null且有值存在
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
	 * 檢查物件是否不為null且有值存在
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
	 * 檢查物件是否不為null且有值存在
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
	 * 檢查物件是否不為null且有值存在
	 * @param checkList 要確認不為null且有值存在的List
	 * @return 不為null且有值存在 : true ; 為null或沒有值存在 : false
	 */
	public static boolean notEmpty(List<?> checkList)
	{
		if (checkList != null && !checkList.isEmpty())
			return true;
		return false;
	}
	
}
