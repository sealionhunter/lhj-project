package test;

import java.io.File;

public class ErrorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		for (int i = 0; i < 40; i++) {
			System.out.println("2^"+i + "\t"+ (2<<i));
		}
//		try {
//			File f = new File("\\\\192.168.200.24\\hjliang\\test\\test");
//			while (f.getParentFile() != null && f.getParentFile().exists()) {
//				f = f.getParentFile();
//			}
//			System.out.println("getAbsolutePath=" + f.getAbsolutePath());
//			System.out.println("TotalSpace=" + f.getTotalSpace());
//			System.out.println("getUsableSpace=" + f.getUsableSpace());
//			if (!f.exists()) {
//				f.createNewFile();
//			}
//			//throw new Error("test error");
//		} catch (Exception e) {
//			System.out.println("catch exception");
//		} finally {
//			System.out.println("finally");
//		}

	}

}
