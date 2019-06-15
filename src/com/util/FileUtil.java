package com.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
	/**
	 * 判断文件是否存在，不存在则创建(包括目录)
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
