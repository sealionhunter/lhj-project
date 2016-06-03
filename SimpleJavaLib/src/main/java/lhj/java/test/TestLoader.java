package lhj.java.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.SecureClassLoader;

/**
 * @author IBM USER
 * 
 */
public class TestLoader extends SecureClassLoader {

	public static void main(String[] args) {
		TestLoader loader = new TestLoader();
		InputStream in = null;
		try {
			in = new FileInputStream("target/classes/lhj/java/test/ProxyTest.class");
			byte[] cl = new byte[0];
			byte[] bt = new byte[10240];
			int len;
			while ((len = in.read(bt)) != -1) {
				byte[] temp = new byte[cl.length + len];
				System.arraycopy(cl, 0, temp, 0, cl.length);
				System.arraycopy(bt, 0, temp, cl.length == 0 ? 0 : cl.length - 1, len);
				cl = temp;
			}
			Class<?> test = loader.defineClass("lhj.java.test.ProxyTest", cl, 0, cl.length);
			Method print = test.getMethod("proxyTest", new Class[] {});
			print.invoke(test.newInstance(), new Object[] {});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}