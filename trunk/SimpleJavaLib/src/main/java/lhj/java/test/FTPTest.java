///*
// * $Id: FTPTest.java,v 1.1 2007/08/25 18:19:10 ostore Exp $
// *
// * Copyright (c) 2005-2007 Sun Japan Corporation. All Rights Reserved.
// */
//package lhj.java.test;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.SocketException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.commons.net.ftp.FTPClient;
//
///**
// * <p>
// * [���@��] �N���X����
// * </p>
// * <p>
// * [�@�@�\] �N���X�̋@�\���L�q����B
// * </p>
// * 
// * @author hjliang
// * @version $Revision: 1.1 $ $Date: 2007/08/25 18:19:10 $
// */
//public class FTPTest {
//	// static String nvl;
//	public static void main(String[] args) {
//		try {
//			FTPClient client = new FTPClient();
//			client.connect("192.168.7.49");
//			boolean isSuc = client.login("edi", "edi");
//			isSuc = client.makeDirectory("UID-Z");
//			System.out.println(isSuc);
//			// isSuc = client.changeWorkingDirectory("UID-Z");
//			// isSuc = client.makeDirectory("UID-Z");
//			// isSuc = client.changeWorkingDirectory("UID-Z");
//			// System.out.println(isSuc);
//			// client.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
//			// System.out.println(client.getReplyString());
//			// client.setFileType(FTP.BINARY_FILE_TYPE);
//			// System.out.println(client.getReplyString());
//			//
//			client.sendCommand("TYPE", "I");
//			System.out.println(client.getReplyString());
//			isSuc = client.storeFile("FTPTEST/CRLFTEST.txt",
//					new FileInputStream("C:\\EDI\\FTP\\CRLFTEST.txt"));
//			System.out.println(client.getReplyString());
//			// System.out.println(isSuc);
//			client.logout();
//			client.disconnect();
//		} catch (SocketException ex) {
//			// TODO Auto-generated catch block
//			ex.printStackTrace();
//		} catch (FileNotFoundException ex) {
//			// TODO Auto-generated catch block
//			ex.printStackTrace();
//		} catch (IOException ex) {
//			// TODO Auto-generated catch block
//			ex.printStackTrace();
//		}
//		// // client.changeWorkingDirectory("folder");
//		// System.out.println(client.makeDirectory("folder/fold-er"));
//		// // client.changeToParentDirectory();
//		// System.out.println(client.changeWorkingDirectory("folder/fold-er"));
//		// System.out.println(client.changeWorkingDirectory("/./../../../.."));
//		// System.out.println(client.changeWorkingDirectory("folder/fold-er"));
//		// //// System.out.println(client.cdup());
//		// // System.out.println(client.getReplyString());
//		// // System.out.println(client.getStatus());
//		// client.changeWorkingDirectory("folder");
//		// FTPFile[] files = client.listFiles();
//		// for (int i = 0; i < files.length; i++) {
//		// int f = files[i].isDirectory() ? client.dele(files[i].getName()) :
//		// client.dele(files[i]
//		// .getName());
//		// System.out.println(client.getReplyString());
//		// }
//		// client.changeToParentDirectory();
//		// client.dele("folder");
//		// System.out.println(client.getReplyString());
//		// client.changeWorkingDirectory("fold-er");
//		// files = client.listFiles();
//		// for (int i = 0; i < files.length; i++) {
//		// int f = files[i].isDirectory() ? client.dele(files[i].getName()) :
//		// client.dele(files[i]
//		// .getName());
//		// System.out.println(client.getReplyString());
//		// }
//		// client.changeToParentDirectory();
//		// client.dele("fold-er");
//		// System.out.println(client.getReplyString());
//		// files = client.listFiles();
//		// for (int i = 0; i < files.length; i++) {
//		// int f = files[i].isDirectory() ? client.dele(files[i].getName()) :
//		// client.dele(files[i]
//		// .getName());
//		// System.out.println(client.getReplyString());
//		// }
//		// System.out.println(client.getReplyString());
//		// System.out.println(client.getStatus());
//		// deleteDir(new File("c:\\hjliang\\temp\\com"));
//		// mapTest();
//	}
//
//	public static void deleteDir(File dir) {
//		if (dir.isFile()) {
//			dir.delete();
//		} else {
//			File[] fs = dir.listFiles();
//			for (int i = 0; i < fs.length; i++) {
//				deleteDir(fs[i]);
//			}
//			dir.delete();
//		}
//
//	}
//
//	public static void mapTest() {
//		Map<String, String> map = new HashMap<String, String>();
//		for (int i = 0; i < 10; i++) {
//			map.put(String.valueOf(i), String.valueOf(i * 100));
//		}
//		for (Map.Entry<String, String> entry : map.entrySet()) {
//			System.out.println(entry.getKey() + " = " + entry.getValue());
//		}
//	}
//
//	public static final long DAYMILL = 1000l * 60l * 60l * 24l;
//
//	/**
//	 * ��ԓ���Z�o����B
//	 * 
//	 * @param fromDate
//	 *            ��ԊJ�n��(YYYY/MM/DD)
//	 * @param endDate
//	 *            ��ԏI����(YYYY/MM/DD)
//	 * @return ��ԓ�
//	 */
//	public static long countDays(Date fromDate, Date endDate) {
//		return (endDate.getTime() - fromDate.getTime()) / DAYMILL + 1;
//	}
//}
