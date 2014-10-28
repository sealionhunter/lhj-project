/*
 * Created on 2005-9-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lhj.java.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author IBM USER
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class LocalResource {
	File clazzFile = null;

	protected LocalResource(File f) {
		this.clazzFile = f;
	}

	protected LocalResource(File f, String name) {

	}

	public byte[] getBytes() {
		if (clazzFile == null) {
			return new byte[0];
		}
		InputStream in = null;
		try {
			in = new FileInputStream(clazzFile);
			byte[] cl = new byte[0];
			byte[] bt = new byte[10240];
			int len;
			while ((len = in.read(bt)) != -1) {
				byte[] temp = new byte[cl.length + len];
				System.arraycopy(cl, 0, temp, 0, cl.length);
				System.arraycopy(bt, 0, temp, cl.length == 0 ? 0
						: cl.length - 1, len);
				cl = temp;
			}
			return cl;
		} catch (FileNotFoundException e) {
			return new byte[0];
		} catch (IOException e) {
			return new byte[0];
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

	public String toString() {
		return clazzFile.getAbsolutePath();
	}

}
