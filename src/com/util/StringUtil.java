package com.util;

public class StringUtil {
	/**
	 * �ж��ַ����Ƿ�Ϊ��(null�ͳ���Ϊ0�� ��Ϊ�շ���true
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
