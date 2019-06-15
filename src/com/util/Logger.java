package com.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
/**
 * 日志
 * @author willow_stream
 *
 */
public class Logger {
	private final static String  PATH = "src/logText.txt";
	private static FileWriter fw ;
	private static File log;
	/**
	 * 初始化文件输出量和日志文件
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
	 * 输出信息到控制台
	 * @param message
	 */
	public static void printf(String message) {
		message = "Log" + new Time(System.currentTimeMillis()) +" : " + message ;
		System.out.println(message);
	}
	/**
	 * 写入日志文件并输出控制台s
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
	 * 关闭对应流
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
