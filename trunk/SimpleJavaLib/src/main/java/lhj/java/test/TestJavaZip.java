package lhj.java.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;

import lhj.java.util.zip.SjisZipInputStream;
import lhj.java.util.zip.SjisZipOutputStream;

public class TestJavaZip {
	public void zip(String zipFileName, String inputFile) throws Exception {
		zip(zipFileName, new File(inputFile));
	}

	public void zip(String zipFileName, File inputFile) throws Exception {
		SjisZipOutputStream out = new SjisZipOutputStream(new FileOutputStream(
				zipFileName));
		zip(out, inputFile, "");
		System.out.println("zip done");
		out.close();
	}

	public void unzip(String zipFileName, String outputDirectory)
			throws Exception {
		SjisZipInputStream in = new SjisZipInputStream(new FileInputStream(
				zipFileName));
		ZipEntry z;
		while ((z = in.getNextEntry()) != null) {
			System.out.println("unziping " + z.getName());
			if (z.isDirectory()) {
				String name = z.getName();
				name = name.substring(0, name.length() - 1);
				File f = new File(outputDirectory + File.separator + name);
				f.mkdir();
				System.out.println("mkdir " + outputDirectory + File.separator
						+ name);
			} else {
				String name = z.getName();
				System.out.println("----------------" + name + "------------");

				int index = name.indexOf(".");
				if (index == -1)
					continue;
				System.out.println(name.substring(index).toLowerCase());
				if (!".csv".equals(name.substring(index).toLowerCase()))
					continue;

				BufferedReader read = new BufferedReader(new InputStreamReader(
						in));

				String line = null;
				while ((line = read.readLine()) != null) {
					System.out.println(line);
				}
				read.close();

				File f = new File(outputDirectory + File.separator
						+ z.getName());
				f.createNewFile();
				FileOutputStream out = new FileOutputStream(f);
				int b;
				while ((b = in.read()) != -1)
					out.write(b);
				out.close();
			}
		}

		in.close();
	}

	public void zip(SjisZipOutputStream out, File f, String base)
			throws Exception {
		System.out.println("Zipping  " + f.getName());
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			while ((b = in.read()) != -1)
				out.write(b);
			in.close();
		}

	}

	public static void main(String[] args) {
		try {
			TestJavaZip t = new TestJavaZip();
			// File f = new File("D:\\Sealion\\Project");
			// File ff = new File(f, "Test\\Test.zip");
			//            
			// System.out.println(ff.getAbsolutePath() + ff.exists());
			// ff = new File(f, "ddd\\fff");
			// System.out.println("ff.mkdirs():" + ff.mkdirs());
			t.zip("D:\\Sealion\\Project\\SPA\\UploadedInfo.zip",
					"D:\\Sealion\\Project\\SPA\\test\\");
			// t.unzip("D:\\Sealion\\Project\\Test\\Test.zip",
			// "D:\\Sealion\\Project\\Test\\test2\\");
			//            
			// t.zip("e:\\test.zip", "e:\\test");
			// t.unzip("e:\\test.zip", "e:\\test2");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}
