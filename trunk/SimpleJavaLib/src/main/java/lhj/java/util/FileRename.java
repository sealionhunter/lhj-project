/*
 * Created on 2005/09/24
 *
 */
package lhj.java.util;

import java.io.File;
import java.io.FileFilter;

import lhj.java.exception.LhjException;

/**
 * @author sealion Created on 2005/09/24
 * 
 */
public class FileRename {

	public static void renameFile(File dir, final String srcEx, String dtsEx) {
		if (dir.isFile()) {
			String name = dir.getName();
			name = name.replaceAll(srcEx, dtsEx);
			File dts = new File(dir.getParentFile(), name);
			dir.renameTo(dts);
		} else if (dir.isDirectory()) {
			FileFilter filter = new FileFilter() {
				public boolean accept(File f) {
					if (f.isDirectory()) {
						return true;
					}
					return f.getName().endsWith(srcEx);
				}
			};
			File[] lst = dir.listFiles(filter);
			for (int i = 0; i < lst.length; i++) {
				renameFile(lst[i], srcEx, dtsEx);
			}
		}
	}

	public static void renameFile(String file, String srcEx, String dtsEx) {
		renameFile(new File(file), srcEx, dtsEx);
	}

	public static void renameFile(String dir) throws LhjException {
		renameFile(new File(dir));
	}

	public static void renameFile(File dir) throws LhjException {
		if (dir.isFile()) {
			String fileName = dir.getName();
			if (fileName.endsWith(".jad")) {
				fileName = fileName.replaceAll(".jad", ".java");
				File newFile = new File(dir.getParentFile(), fileName);
				dir.renameTo(newFile);
			}
		} else if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				renameFile(files[i]);
			}
		}
	}

	public static void main(String[] args) throws LhjException {
		renameFile("C:\\Sealion\\Projects\\sanctuary\\src");
		System.out.println("Rename begin...");
		// FileRename.renameFile("C:\\Hjliang\\Project\\MyDev\\JavaDocHelper\\src",
		// "jad", "java");
		System.out.println("Rename end.");
	}
}
