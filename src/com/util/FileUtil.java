package com.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	/**
	 * �ж��ļ��Ƿ���ڣ��������򴴽�(����Ŀ¼)
	 * @param file
	 */
	public static void isExists(File file) {
		if(!file.exists()) {
			try {
				File director = file.getParentFile();
				if(!director.exists())
					director.mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
