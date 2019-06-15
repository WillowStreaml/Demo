package com.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
/**
 * ��־
 * @author willow_stream
 *
 */
public class Logger {
	private final static String  PATH = "src/logText.txt";
	private static FileWriter fw ;
	private static File log;
	/**
	 * ��ʼ���ļ����������־�ļ�
	 */
	public static void startup() {
		log = new File(PATH);
		FileUtil.isExists(log);
		try {
			fw = new FileWriter(log, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �����Ϣ������̨
	 * @param message
	 */
	public static void printf(String message) {
		message = "Log" + new Time(System.currentTimeMillis()) +" : " + message ;
		System.out.println(message);
	}
	/**
	 * д����־�ļ����������̨s
	 * @param messaage
	 */
	public static void log(String message) {
		startup();
		message = "Log" + new Time(System.currentTimeMillis()) +" : " + message +"\n";
		try {
			fw.write(message);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		startdown();
	}
	/**
	 * �رն�Ӧ��
	 */
	public static void startdown() {
		if(fw != null) {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
