package com.demo.simpleFileTransfer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import com.util.FileUtil;
import com.util.Logger;

public class Client {
	private static final  String IP = "127.0.0.1";
	private static final int Port = 10527;
	private static final String filePath = "D:\\TestSpace\\LoaclLibrary\\1.jpg";  //�ļ�·��
	private static File transferFile; //�ϴ��ļ�
	private static DataInputStream dis ;
	private static DataOutputStream dos ;
	private static DataInputStream fRead;
	private static DataOutputStream fWrite;
	private static Socket socket;
	/**
	 * ��ʼ��socket�͹�����Ӧ��
	 */
	public static void init() {
		try {
			socket = new Socket(IP, Port);
			Logger.printf("Connect Success");
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			transferFile = new File(filePath);
			FileUtil.isExists(transferFile);
			fRead = new DataInputStream(new FileInputStream(transferFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			init();
			Logger.printf("transfer start");
			Logger.printf("sent transfer cmd");
			//�����ϴ��ļ�ָ��
			dos.writeInt(1);
			//�������Ƿ�����ļ�
			if(dis.readInt() != 0){
				Logger.printf("service cmd error");
				end();
				return;
			}
			//�����ļ���
			dos.write(transferFile.getName().getBytes());
			Logger.printf("Send FileName:"+transferFile.getName());
			if(dis.readInt() != 0){
				Logger.printf("service cmd error");
				end();
				return;
			}
			//�����ļ�����
			long length = transferFile.length();
			dos.writeLong(length);
			Logger.printf("Sent TransferFile Size:"+length);
			if(dis.readInt() != 0){
				Logger.printf("service cmd error");
				end();
				return;
			}
			//�����ļ�
			long size = length/2048;
			if(size < 1) {
				byte[] data = new byte[(int) length];
				fRead.read(data);
				dos.write(data);
			}
			else {
				byte[] data = new byte[2048];
				while(size > 0) {
					fRead.read(data);
					dos.write(data);
					size--;
				}
				byte[] end = new byte[(int) (length-2048*size)];
 				fRead.read(end);
 				dos.write(end);
			}
			end();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ���� �ر���Դ
	 * @throws IOException
	 */
	public static void end() throws IOException {
		if(dis != null) {
			dis.close();
		}
		if(dos != null) {
			dos.close();
		}
		if(fRead != null) {
			fRead.close();
		}
		if(fWrite != null) {
			fWrite.close();
		}
		Logger.printf("Client Close");
	}
}
