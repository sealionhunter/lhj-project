package lhj.java.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MoveSrc {
	private String baseDir = ".";

	private String srcToDir = "src";

	private String include = "java";

	private String exclude = "*";

	private String srcFromDir = "**";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MoveSrc main = new MoveSrc();
		main.setBaseDir("C:\\Sealion\\Projects\\SampleJavaLib\\jrlib_src\\");
		main.setSrcFromDir("src");
		main.setSrcToDir("Test");
		main.move();
		// TODO Auto-generated method stub

	}

	public void move() {
		File base = new File(baseDir);
		File srcTo = new File(base, srcToDir);
		if (!srcTo.exists() || !srcTo.isDirectory()) {
			srcTo.mkdirs();
		}
		File srcFrom = new File(base, srcFromDir);
		move(srcFrom, srcTo);
	}

	private void move(File from, File to) {
		if (from.isDirectory()) {
			File[] subFrom = from.listFiles(new FileFilter() {

				public boolean accept(File pathname) {
					if (pathname.isDirectory())
						return true;
					return pathname.getName().endsWith(".java");
				}
			});
			if (subFrom != null) {
				for (int i = 0; i < subFrom.length; i++) {
					move(subFrom[i], to);
				}
			}
		}
		if (from.isFile()) {
			copyFile(from, new File(makePackage(to, getPackage(from)), from
					.getName()));
		}
	}

	private void copyFile(File src, File dest) {
		if (!src.exists() || !src.isFile()) {
			return;
		}
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(src);
			out = new FileOutputStream(dest);
			byte[] buff = new byte[8192];
			int len;
			while ((len = in.read(buff, 0, 8192)) != -1) {
				out.write(buff, 0, len);
			}
			out.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private File makePackage(File base, String pkg) {
		if (pkg == null || pkg.length() == 0) {
			return base;
		}
		File retTemp = base;
		StringTokenizer st = new StringTokenizer(pkg, ".");
		while (st.hasMoreTokens()) {
			retTemp = new File(retTemp, st.nextToken());
		}
		if (!retTemp.exists()) {
			retTemp.mkdirs();
		}
		return retTemp;
	}

	private String getPackage(File javaFile) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(javaFile));
			String pkg = "";
			while ((pkg = in.readLine()) != null) {
				if (pkg.indexOf("package") != -1) {
					pkg = pkg.substring(pkg.indexOf("package") + 7);
					int end = pkg.indexOf(';');
					if (end != -1) {
						pkg = pkg.substring(0, end).trim();
						return pkg;
					}
					break;
				}
				if (pkg.indexOf("public") != -1) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		return null;
	}

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public String getExclude() {
		return exclude;
	}

	public void setExclude(String exclude) {
		this.exclude = exclude;
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getSrcToDir() {
		return srcToDir;
	}

	public void setSrcToDir(String srcDestDir) {
		this.srcToDir = srcDestDir;
	}

	public String getSrcFromDir() {
		return srcFromDir;
	}

	public void setSrcFromDir(String srcSrcDir) {
		this.srcFromDir = srcSrcDir;
	}

}
