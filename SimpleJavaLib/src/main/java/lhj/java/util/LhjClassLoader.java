/*
 * Created on 2005-9-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lhj.java.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lhj.java.exception.LhjException;

/**
 * @author IBM USER
 */
public class LhjClassLoader extends ClassLoader {
	Class<?> clazz = null;

	File clazzFile = null;

	String clazzName = null;

	boolean loaded = false;

	protected LhjClassLoader() {
		super();
	}

	// public LhjClassLoader(String baseDir, String classFile) throws
	// LhjException {
	// this(new File(baseDir, classFile));
	// }
	//
	// public LhjClassLoader(String classFile) throws LhjException {
	// this(new File(classFile));
	// }

	public LhjClassLoader(File classFile) throws LhjException {
		super();
		this.clazzFile = classFile;
		if (!clazzFile.exists()) {
			throw new LhjException("File:" + classFile + " don't exist!");
		}
		clazzName = clazzFile.getName();
		int index = clazzName.indexOf(".class");
		if (index > -1) {
			clazzName = clazzName.substring(0, index);
		} else if ((index = clazzName.indexOf(".jar")) > -1) {
			clazzName = clazzName.substring(0, index);
		}
	}

	// public Class loadClass(String name, byte[] b, int offset, int length) {
	// return super.defineClass(name, b, offset, length);
	// }

	private void loadFile() throws LhjException {
		FileInputStream in = null;
		try {
			in = new FileInputStream(clazzFile);
			byte[] cl = new byte[0];
			byte[] bt = new byte[8096];
			int len;
			while ((len = in.read(bt)) != -1) {
				byte[] temp = new byte[cl.length + len];
				System.arraycopy(cl, 0, temp, 0, cl.length);
				System.arraycopy(bt, 0, temp, cl.length == 0 ? 0
						: cl.length - 1, len);
				cl = temp;
			}
			clazz = super.defineClass(clazzName, cl, 0, cl.length);
		} catch (FileNotFoundException e) {
			throw new LhjException(e);
		} catch (IOException e) {
			throw new LhjException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new LhjException(e);
				}
			}
		}
	}

	public void loadClass() throws LhjException {
		if (!loaded) {
			loadFile();
			loaded = true;
		}
	}

	public Object invokeMethod(String methodName, Object[] parameter)
			throws LhjException {
		try {
			loadClass();
			Class<?>[] paramType = new Class[parameter.length];
			for (int i = 0; i < parameter.length; i++) {
				paramType[i] = parameter[i].getClass();
			}
			Method method = clazz.getMethod(methodName, paramType);
			return method.invoke(clazz.newInstance(), parameter);
		} catch (SecurityException e) {
			throw new LhjException(e);
		} catch (IllegalArgumentException e) {
			throw new LhjException(e);
		} catch (LhjException e) {
			throw new LhjException(e);
		} catch (NoSuchMethodException e) {
			throw new LhjException(e);
		} catch (IllegalAccessException e) {
			throw new LhjException(e);
		} catch (InvocationTargetException e) {
			throw new LhjException(e);
		} catch (InstantiationException e) {
			throw new LhjException(e);
		}
	}
}
