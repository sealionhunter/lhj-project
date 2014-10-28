package lhj.learn.service.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestServiceTest {

	private static File dest = new File("E:\\jarlinks"); 
	private static Map<String, String> existsFiles = new HashMap<String, String>();
	private static int count = 0;
	private static long fileSize = 0;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File root = new File("E:\\");
		copyJars(root);
		System.out.println(count);
		System.out.println((fileSize/1024/1024) + "MB");
	}
	
	public static void copyJars(File src) throws IOException {
		File[] subs = src.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().equals(dest.getName())) {
					return false;
				}
				if (pathname.isDirectory()) {
					return true;
				}
				return pathname.getName().endsWith(".jar");
			}
		});
		
		if (subs != null) {
			for (File sub : subs) {
				if (sub.isDirectory()) {
					copyJars(sub);
				} else if (sub.isFile() && sub.getName().endsWith(".jar")) {
					if (existsFiles.containsKey(sub.getName()) || (new File(dest, sub.getName()).exists())) {
						count++;
						fileSize += sub.length();
					} else {
						existsFiles.put(sub.getName(), "");
						System.out.println(sub.getAbsolutePath());
						copy(sub);
					}
				}
			}
		}
	}
	
	public static void copy(File src) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(dest, src.getName())));
		byte[] buffer = new byte[8192];
		int len = 0;
		while ( (len = in.read(buffer, 0, 8192)) != -1) {
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
	}

}
