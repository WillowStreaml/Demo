package com.demo.simpleFileTransfer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.util.FileUtil;
import com.util.Logger;

public class Service {
	final static int PORT = 10527;
	final static String FilePath = "D:\\TestSpace\\DocumentLibrary"; //�������ļ���·��
	private static ServerSocket server;
	private static Socket socket;
	private static DataInputStream dis;
	private static DataOutputStream dos;
	private static DataOutputStream fdos;
	/**
	 * server���յ�socket ������Ӧ��
	 * @throws IOException
	 */
	private static void startup() throws IOException {
		socket = server.accept();
		Logger.printf("client connected");
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
	}
	
	public static void main(String[] args) {
		try {
			server = new ServerSocket(PORT);
			Logger.printf("service startup!");
			while(true){
				startup();
				int type = dis.readInt(); // 1���� 0����
				if(type ==  1) {
					dos.writeInt(0);
					//�����ϴ��ļ���
					byte[] fileName = new byte[1024];
					dis.read(fileName);
					dos.writeInt(0);
					String transferFileName = new String(fileName).trim();
					File file = new File(FilePath+System.getProperties().get("file.separator")+transferFileName);
					FileUtil.isExists(file);
					//�����ļ�����
					long length = dis.readLong();
					long size = length/2048;
					dos.writeInt(0); 
					Logger.log("service start recive file:"+ transferFileName);

					fdos = new DataOutputStream(new FileOutputStream(file,true));
					if(size < 1) {
						byte[] data = new byte[(int) length];
						dis.read(data);
						fdos.write(data);
						Logger.log("service recive file end, file size:"+length);
					}
					else {
						byte[] data = new byte[2048];
						byte[] end = new byte[(int) (length-2048*size)];
						while(size > 0) {
							dis.read(data);
							fdos.write(data);
							size--;
						}
						dis.read(end);
						fdos.write(end);
						Logger.log("service recive file end, file size:"+length);
					}
					startDown();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ر���
	 * @throws IOException
	 */
	private static void startDown() throws IOException {
		if(dis != null) {
			dis.close();
		}
		if(dos != null) {
			dos.close();
		}
		if(fdos != null) {
			fdos.close();
		}
		if(socket != null) {
			socket.close();
		}
	}
}
