/*
 * Created on 2005-9-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lhj.java.util;

import java.io.File;
import java.util.Enumeration;
import java.util.StringTokenizer;

/**
 * @author IBM USER
 * 
 */
public class LocalClassPath {
	File[] fileArray = new File[10];
	int size = 0;

	LocalClassPath(File[] files) {

		if (files != null) {
			this.fileArray = files;
			size = files.length;
		}
	}

	public void addFile(File f) {
		size++;
		if (size >= fileArray.length) {
			increase(size);
			fileArray[size - 1] = f;
		}
	}

	public File[] getFiles() {
		if (size == 0) {
			return new File[0];
		}
		File[] temp = new File[size];
		System.arraycopy(fileArray, 0, temp, 0, size);
		return temp;
	}

	public LocalResource getResource(String clazzName, boolean is) {
		if (size == 0)
			return null;
		LocalResource ret = null;
		for (int i = 0; i < size; i++) {
			File f = fileArray[i];
			if ((ret = findResource(f, getClazzName(clazzName),
					parsePackages(clazzName))) != null) {
				return ret;
			}
		}
		return ret;
	}

	public File checkFile(File file) {
		if (size == 0)
			return null;
		for (int i = 0; i < size; i++) {
			File f = fileArray[i];
			if (f.equals(file)) {
				return f;
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Enumeration findResources(String name, boolean isb) {
		class Enu implements Enumeration {
			File[] files;
			int current = -1;

			Enu(File[] fs) {
				files = fs;
			}

			public Object nextElement() {
				if (files == null || current == -1 || current >= files.length) {
					throw new IndexOutOfBoundsException(String.valueOf(current));
				}
				return files[current];
			}

			public boolean hasMoreElements() {
				return files == null || ++current >= files.length;
			}
		}
		return new Enu(fileArray);
	}

	private boolean checkFile(File f, String clazzName, String[] packag) {
		if (f.isFile() && f.getName().equals(clazzName.concat(".class"))) {
			boolean found = true;
			File temp = f;
			for (int i = packag.length - 1; i > 0; i++) {
				if (!temp.getParentFile().getName().equals(packag[i])) {
					found = false;
					break;
				}
				temp = temp.getParentFile();
			}
			return found;
		}
		return false;
	}

	private LocalResource findResource(File f, String clazzName, String[] packag) {
		if (checkFile(f, clazzName, packag)) {
			return new LocalResource(f);
		}
		if (f.isFile()) {
			return null;
		}
		File[] fs = f.listFiles();
		LocalResource ret = null;
		for (int i = 0; i < fs.length; i++) {
			ret = findResource(fs[i], clazzName, packag);
			if (ret != null) {
				return ret;
			}
		}
		return ret;
	}

	private void increase(int minCapacity) {
		int oldCapacity = fileArray.length;
		if (minCapacity > oldCapacity) {
			File oldFile[] = fileArray;
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			fileArray = new File[newCapacity];
			System.arraycopy(oldFile, 0, fileArray, 0, size);
		}
	}

	private String getClazzName(String clazz) {
		int i = clazz.lastIndexOf('/');
		if (i == -1) {
			return clazz;
		}
		return clazz.substring(i + 1);
	}

	private String[] parsePackages(String clazzName) {
		StringTokenizer st = new StringTokenizer(clazzName, "/");
		String[] ret = new String[st.countTokens() - 1];
		int i = 0;
		while (st.hasMoreTokens()) {
			if (i == ret.length) {
				return ret;
			}
			ret[i++] = st.nextToken();
		}
		return ret;
	}

	public static void main(String[] args) {
		LocalClassPath fcp = new LocalClassPath(new File[] { new File(
				"E:\\hjliang\\classes\\") });
		System.out.print(fcp.getResource("lhj/LocalClassLoader", false));

	}
}
