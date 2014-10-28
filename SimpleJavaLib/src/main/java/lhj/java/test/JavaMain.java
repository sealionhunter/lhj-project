/*
 * Created on 2005-9-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lhj.java.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.swing.JTextField;

import lhj.java.exception.LhjException;
import lhj.java.util.BinaryTool;
import lhj.java.util.HexTool;
import lhj.java.util.SP;

import org.apache.commons.lang.StringUtils;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

/**
 * @author hjliang
 * 
 * 
 */
@SuppressWarnings("restriction")
public class JavaMain {
	public static int KB = 1024;
	public static int MB = KB << 10;
	public static int MB_32 = MB << 5;
	public static int MB_64 = MB_32 << 1;

	public static byte[] getUTF8Bytes(String s) {
		char[] c = s.toCharArray();
		int len = c.length;
		// Count the number of encoded bytes...
		int count = 0;
		for (int i = 0; i < len; i++) {
			int ch = c[i];
			if (ch <= 0x7f) {
				count++;
			} else if (ch <= 0x7ff) {
				count += 2;
			} else {
				count += 3;
			}
		}
		// Now return the encoded bytes...
		byte[] b = new byte[count];
		int off = 0;
		for (int i = 0; i < len; i++) {
			int ch = c[i];
			if (ch <= 0x7f) {
				b[off++] = (byte) ch;
			} else if (ch <= 0x7ff) {
				b[off++] = (byte) ((ch >> 6) | 0xc0);
				b[off++] = (byte) ((ch & 0x3f) | 0x80);
			} else {
				b[off++] = (byte) ((ch >> 12) | 0xe0);
				b[off++] = (byte) (((ch >> 6) & 0x3f) | 0x80);
				b[off++] = (byte) ((ch & 0x3f) | 0x80);
			}
		}
		return b;
	}

	public static void main(String[] args) throws Exception {
		// int BUFFER = 1024;
		// String name = "test\\中文和日本語混在.txt";
		// byte[] test = getUTF8Bytes(name);
		// byte[] test2 = name.getBytes("UTF-16");
		// byte[] test3 = name.getBytes();
		// name = new String(name.getBytes("UTF-8"), "UTF-8");
		// FileOutputStream out = new FileOutputStream(name);
		// out.write(name.getBytes());
		// out.close();
		// ZipUtil.compress("test", "test.zip");
		// ZipUtil.extract("test.zip", "testupzip");

		// FileOutputStream f =
		// new FileOutputStream("test3.zip");
		// CheckedOutputStream csum =
		// new CheckedOutputStream(
		// f, new Adler32());
		// ZipOutputStream out =
		// new ZipOutputStream(
		// new BufferedOutputStream(csum, BUFFER));
		//    	
		// ZipEntry entry = new ZipEntry(new String(name.getBytes("UTF-8"),
		// "UTF-8"));
		// out.putNextEntry(entry);
		// BufferedInputStream in =
		// new BufferedInputStream(
		// new FileInputStream(name), BUFFER);
		//
		// byte[] buffer = new byte[BUFFER];
		// int c;
		// while((c = in.read(buffer, 0, BUFFER)) != -1)
		// out.write(buffer, 0, c);
		// out.flush();
		// in.close();
		// out.close();
		// System.out.println(String.valueOf(true));
		JavaMain main = new JavaMain();
		// main.gb2312Test();
		// System.out.println(BinaryTool.byteToBinary((byte)0));
		// System.out.println(BinaryTool.charToBinary('0'));
		// System.out.println(BinaryTool.byteToBinary((byte)2));
		// System.out.println(BinaryTool.charToBinary('2'));
		// System.out.println('0' - 0);
		// System.out.println('a' - 0);
		// System.out.println('A' - 0);
		// main.getGB2312();
		// main.printMovie();
		// main.printMovie();
		// main.testArrayLength();
		main.properties();

		// try {
		// LocalClassLoader loader = new LocalClassLoader(new File[]{new
		// File("E:\\hjliang\\test\\")});
		// // File f = new File("E:\\hjliang\\test\\");
		// // System.out.println(f.getAbsolutePath());
		// // JavaMain main = new JavaMain();
		// // main.javac("lhj\\util\\*.java");
		// // Class test = loader.loadClass("Test");
		// Class test = loader.loadClass("lhj.Test", true);
		// Method print = test.getMethod("print", new Class[] {});
		// print.invoke(test.newInstance(), new Object[] {});
		//
		// test = loader.loadClass("java.lang.String");
		// Method toString = test.getMethod("toString", new Class[] {});
		// Constructor string = test.getConstructor(new Class[]{String.class});
		// Object obj = string.newInstance(new Object[]{new
		// String("This is a test!")});
		// System.out.println(toString.invoke(obj, new Object[] {}));
		//
		// URLClassLoader loader1 = URLClassLoader.newInstance(new URL[] {new
		// URL("file://E:/hjliang/test/")});
		// // loader1.
		// test = loader1.loadClass("lhj.Test");
		// print = test.getMethod("print", new Class[] {});
		// print.invoke(test.newInstance(), new Object[] {});
		// } catch (SecurityException e) {
		// e.printStackTrace();
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// } catch (NoSuchMethodException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// e.printStackTrace();
		// } catch (InstantiationException e) {
		// e.printStackTrace();
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// }
		// main.loadClass();
		// } catch (LhjException e) {
		// e.printStackTrace();
		// }
	}

	public String findJavaHome() {
		Properties prop = System.getProperties();
		String home = prop.getProperty("java.home");
		if (home.endsWith("jre")) {
			home = home.substring(0, home.indexOf("jre") - 1);
			File java = new File(home + File.separator + "bin" + File.separator
					+ "java.exe");
			File javac = new File(home + File.separator + "bin"
					+ File.separator + "javac.exe");
			if (javac.exists() && java.exists()) {
				return home;
			}
		}
		String lib = prop.getProperty("java.library.path");
		StringTokenizer st = new StringTokenizer(lib, ";");
		while (st.hasMoreTokens()) {
			String dir = st.nextToken();
			if (dir.indexOf("j2sdk") != -1) {
				File java = new File(dir + File.separator + "java.exe");
				File javac = new File(dir + File.separator + "javac.exe");
				if (java.exists() && javac.exists()) {
					int index = dir.indexOf("bin");
					if (index > 1) {
						dir = dir.substring(0, index - 1);
					}
					return dir;
				}
			}
		}
		return null;
	}

	public void testArrayLength() {
		byte[] array = new byte[(int) (MB_32)];
		System.out.println(array.length);
	}

	public String getJavaToolPath() {
		Properties prop = System.getProperties();
		String lib = prop.getProperty("java.library.path");
		StringTokenizer st = new StringTokenizer(lib, ";");
		while (st.hasMoreTokens()) {
			String dir = st.nextToken();
			File java = new File(dir + File.separator + "java.exe");
			File javac = new File(dir + File.separator + "javac.exe");
			if (javac.exists() && java.exists()) {
				return dir;
			}
		}
		return null;
	}

	public void javac(String fileName) {
		try {
			Process javac = Runtime
					.getRuntime()
					.exec(
							getJavaToolPath()
									+ File.separator
									+ "javac.exe -d ./ E:\\hjliang\\src\\lhj\\util\\*.java",
							new String[] {}, new File("E:\\hjliang\\"));
			InputStream out = javac.getErrorStream();
			byte[] msg = new byte[1024];
			int length;
			while ((length = out.read(msg)) != -1) {
				System.out.println(new String(msg, 0, length));
			}
			out = javac.getInputStream();
			while ((length = out.read(msg)) != -1) {
				System.out.println(new String(msg, 0, length));
			}
			out.close();
			javac.destroy();
			System.out.println(javac.exitValue());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadClass() throws LhjException {
		// LhjClassLoader loader = new LhjClassLoader(new
		// File("E:\\hjliang\\test", "Test.class"));
		// loader.invokeMethod("print", new Object[]{});
	}

	public void test() {
		JTextField j = new JTextField();
		j.setName("in");

		System.out.println("��this����?".getBytes().length);
		System.out.println(rightPad("����̓e�X�g�ł��I", 30, '!', "UTF-8")
				+ "test");
		System.out.println(rightPad("this is a tes�I", 30, '!', "ISO-8859-1")
				+ "test");
		System.out.println(rightPad("����̓e�X�g�ł��I", 30, "Shift-jis")
				+ "test");
		System.out.println(rightPad("this is a test!", 30, "UTF-8") + "test");
		System.out
				.println(StringUtils.rightPad("����̓e�X�g�ł��I", 30) + "test");
		System.out
				.println(StringUtils.rightPad("this is a test!", 30) + "test");
	}

	String priStr = "private String ";

	String nullSep = " = null;\n";

	String st = "/** ";

	String en = " */\n";

	private String clazzName = "";

	private String[] col = {};

	// private String[] colDoc = {};

	public void createBean() {
		StringBuffer clazz = new StringBuffer();
		clazz.append("public class ").append(clazzName).append("{\n");
		for (int i = 0; i < col.length; i++) {

		}
	}

	private static final String HANKAKU_SJIS = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~���������������������������������������������������������������";

	public static String rightPad(String source, int length, char padChar,
			String charset) {
		try {
			source = new String(source.getBytes("SJIS"), "SJIS");
		} catch (UnsupportedEncodingException ex) {
		}

		StringBuffer sb = new StringBuffer(source);
		for (int i = 0; i < length; i++) {
			sb.append(padChar);
		}

		char[] c = sb.toString().toCharArray();
		int len = 0;
		StringBuffer ss = new StringBuffer();
		for (int i = 0; i < length; i++) {
			if (HANKAKU_SJIS.indexOf(c[i]) != -1) {
				len += 1;
				if (len <= length) {
					ss.append(c[i]);
				} else {
					break;
				}
			} else {
				len += 2;
				if (len < length + 1) {
					ss.append(c[i]);
				} else if (len == length + 1) {
					ss.append(padChar);
				} else {
					break;
				}
			}
		}

		return ss.toString();
	}

	public static String rightPad(String source, int length, String charset) {
		int padLength = 0;
		try {
			byte[] bytes = source.getBytes(charset);
			padLength = length - bytes.length;
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
			padLength = length - source.length();
		}
		if (padLength <= 0) {
			return source;
		}
		while (SPACEPAD.length() < padLength) {
			SPACEPAD = SPACEPAD.concat(SPACEPAD);
		}
		return source.concat(SPACEPAD.substring(0, padLength));
	}

	public static String SPACEPAD = "                                ";

	public static void testFile() {
		try {
			File f = new File(
					"D:\\Sealion\\Project\\Test\\000000000001_image.jpg");
			System.out.println(f.exists());
			if (f.exists() && f.isFile()) {
				FileInputStream in = new FileInputStream(f);
				in.close();
			}
			f = new File("D:\\Sealion\\Project\\Test\\000000000001_image.jpg");
			System.out.println(f.exists());
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	void print(String value) {
		System.out.print(value);
	}

	void properties() throws Exception {
		Properties prop = System.getProperties();
		prop.store(System.out, "System Properties");
	}

	public void printMovie() throws Exception {
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(
				"C:\\Sealion\\MovieList.txt"), "UTF-8");
		int index = 1;
		printMovieName(new File("G:\\Movie"), out, index);
		printMovieName(new File("G:\\movie 1"), out, index);
		out.close();
	}

	private void printMovieName(File file, OutputStreamWriter out, int index)
			throws Exception {
		out.append(file.getName() + "\n");

		FileFilter filter = new FileFilter() {

			public boolean accept(File pathname) {
				return !pathname.getName().equals("sexual");
			}

		};
		File[] files = file.listFiles(filter);
		if (files != null && files.length > 0) {
			StringBuffer buf = new StringBuffer(index);
			for (int i = 0; i < index * 4; i++) {
				buf.append(' ');
			}
			for (int i = 0; i < files.length; i++) {
				out.append(buf);
				if (files[i].isDirectory()) {
					printMovieName(files[i], out, index + 1);
				} else {
					out.append(files[i].getName() + "\n");
				}
			}
			out.append("\n");
		}
	}

	public void gb2312Test() throws Exception {
		String fileName = "C:\\Sealion\\Projects\\SampleJavaLib\\ZhongWen.txt";
		FileInputStream inByte = new FileInputStream(fileName);
		// InputStreamReader in = new InputStreamReader(inByte, "gb2312");
		byte[] buff = new byte[1024];
		int length = inByte.read(buff, 0, 1024);
		byte[] buffTemp = new byte[length];
		System.arraycopy(buff, 0, buffTemp, 0, length);
		String nihao = new String(buffTemp, "GB2312");
		System.out.println(BinaryTool.byteToBinCharWithSP(buffTemp));
		System.out.println(HexTool.byteToHexChars(buffTemp));
		System.out.println(nihao);
		String fileNameUtf8 = "C:\\Sealion\\Projects\\SampleJavaLib\\ZhongWenUtf8.txt";
		FileInputStream inByteUtf8 = new FileInputStream(fileNameUtf8);
		// InputStreamReader in = new InputStreamReader(inByte, "gb2312");
		byte[] buffUtf8 = new byte[1024];
		int lengthUtf8 = inByteUtf8.read(buffUtf8, 0, 1024);
		byte[] buffTempUtf8 = new byte[lengthUtf8];
		System.arraycopy(buffUtf8, 0, buffTempUtf8, 0, lengthUtf8);
		String nihaoUtf8 = new String(buffTempUtf8, "Utf-8");
		System.out.println(BinaryTool.byteToBinCharWithSP(buffTempUtf8));
		System.out.println(HexTool.byteToHexChars(buffTempUtf8));
		System.out.println(nihaoUtf8);
		inByte.close();
		inByteUtf8.close();
	}

	public void getGB2312() throws Exception {
		String fileName = "G:\\Learn\\Unihan.txt";
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		BufferedWriter out = new BufferedWriter(new FileWriter(
				"C:\\Sealion\\Projects\\GB7.dat"));
		String buff = in.readLine();
		int count = 0;
		while (buff != null && buff.length() > 0) {
			char ch = buff.charAt(0);
			if (ch == '#') {
				buff = in.readLine();
				continue;
			}
			if (buff.indexOf("kGB7") != -1) {
				out.write(buff);
				out.append('\n');
			}
			if (count++ > 100) {
				out.flush();
				count = 0;
			}
			buff = in.readLine();
		}
		out.flush();
		in.close();
		out.close();
	}

	public void test1() throws Exception {
		String path = System.getProperty("java.class.path");
		if (path == null) {
			path = "C:\\Sealion\\Projects";
		} else {
			path += ";C:\\Sealion\\Projects";
		}
		System.setProperty("java.class.path", path);
		ClassLoader loader = new ClassLoader();
		Class<?> clazz = loader.loadClass("test.Test");
		SP.print(clazz.getName());
	}
}
