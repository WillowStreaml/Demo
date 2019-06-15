package com.util;

public class StringUtil {
	/**
	 * 判断字符串是否为空(null和长度为0） ，为空返回true
	 * @param string
	 * @return 
	 */
	public static boolean isNull(String string) {
		if(string == null)
			return true;
		if(string.length() == 0)
			return true;
		return false;
	}
}
