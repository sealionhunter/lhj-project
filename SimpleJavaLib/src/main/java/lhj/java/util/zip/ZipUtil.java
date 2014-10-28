package lhj.java.util.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * Title:ZipUtil
 * </p>
 * <p>
 * Description: �o���pZip�AUnzip����Util
 * </p>
 * <p>
 * Copyright: Copyright nttdata(c) 2003
 * </p>
 * <p>
 * Company: nttdata
 * </p>
 * 
 * @author: sunjapan
 * @version 1.0
 */

public class ZipUtil {

	static final String FILE_SEPARATOR;
	static {
		FILE_SEPARATOR = System.getProperty("file.separator");
	}

	// �t�@�C�����k�izip�t�@�C���i���j
	private static void zipCompress(final String fileName, final String zipFileName)
			throws IOException {
		int BUFFER = 1024;

		// �ژ^�𐶐�����
		if (zipFileName.indexOf("\\") != -1) {
			File file = new File(zipFileName.substring(0, zipFileName
					.lastIndexOf("\\")));
			file.mkdirs();
		}

		FileOutputStream f = new FileOutputStream(zipFileName);
		CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
				csum, BUFFER));

		String zipEntryName = "";
		if (fileName.indexOf(FILE_SEPARATOR) != -1) {
			zipEntryName = fileName.substring(fileName
					.lastIndexOf(FILE_SEPARATOR) + 1);
		}
		out.putNextEntry(new ZipEntry(zipEntryName));

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				fileName), BUFFER);

		byte[] buffer = new byte[BUFFER];
		int c;
		while ((c = in.read(buffer, 0, BUFFER)) != -1) {
			out.write(buffer, 0, c);
		}
		out.flush();
		in.close();
		out.close();
	}

	// �t�@�C�����k�igZip�t�@�C���i���j
	protected static void gzipCompress(final String fileName, final String zipFileName)
			throws IOException {
		// �ژ^�𐶐�����
		if (zipFileName.indexOf(FILE_SEPARATOR) != -1) {
			File file = new File(zipFileName.substring(0, zipFileName
					.lastIndexOf(FILE_SEPARATOR)));
			file.mkdirs();
		}

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				fileName));
		BufferedOutputStream out = new BufferedOutputStream(
				new GZIPOutputStream(new FileOutputStream(zipFileName)));

		int c;
		while ((c = in.read()) != -1) {
			out.write(c);
		}
		out.flush();
		in.close();
		out.close();
	}

	private static void zipExtract(final String zipFileName, final String fileName)
			throws IOException {
		boolean bNeedRetrySjis = false;
		try {
			zipExtractUtf8(zipFileName, fileName);

		} catch (IllegalArgumentException IllegalArgument) {
			// IllegalArgument.printStackTrace();
			bNeedRetrySjis = true;
		} catch (IOException e) {
			throw e;
		}

		if (bNeedRetrySjis) {
			zipExtractSjis(zipFileName, fileName);
		}
	}

	public static void main(final String args[]) {
		try {
			// zipExtract("c:/temp/���U��test_0002.zip",
			// "c:/temp/���U��BatchController111.java");
			compress("ZipTest.java", "newtest.zip");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// �t�@�C���𓀁izip�t�@�C���i���j
	private static void zipExtractUtf8(final String zipFileName, final String fileName)
			throws IOException {
		ZipInputStream in = new ZipInputStream(new BufferedInputStream(
				new CheckedInputStream(new FileInputStream(zipFileName),
						new Adler32())));

		ZipEntry ze = null;
		while ((ze = in.getNextEntry()) != null) {
			// �ژ^����������A�X�L�b�v
			if (ze.isDirectory()) {
				continue;
			}

			// �ژ^�𐶐�����
			if (fileName.indexOf(FILE_SEPARATOR) != -1) {
				File file = new File(fileName.substring(0, fileName
						.lastIndexOf(FILE_SEPARATOR)));
				file.mkdirs();
			}

			// �t�@�C�����𓀂��A�t�@�C���𐶐�����
			FileOutputStream f = new FileOutputStream(fileName);
			int x;
			while ((x = in.read()) != -1) {
				f.write(x);
			}
			f.flush();
			f.close();
		}
		in.close();
	}

	// �t�@�C���𓀁izip�t�@�C���i���j
	private static void zipExtractSjis(final String zipFileName, final String fileName)
			throws IOException {

		BufferedInputStream temp = new BufferedInputStream(
				new CheckedInputStream(new FileInputStream(zipFileName),
						new Adler32()));

		// InputStream

		MroSjisZipInputStream in = new MroSjisZipInputStream(temp);

		// in.

		ZipEntry ze = null;
		while ((ze = in.getNextEntry()) != null) {
			// �ژ^����������A�X�L�b�v
			if (ze.isDirectory()) {
				continue;
			}

			// �ژ^�𐶐�����
			if (fileName.indexOf(FILE_SEPARATOR) != -1) {
				File file = new File(fileName.substring(0, fileName
						.lastIndexOf(FILE_SEPARATOR)));
				file.mkdirs();
			}

			// �t�@�C�����𓀂��A�t�@�C���𐶐�����
			FileOutputStream f = new FileOutputStream(fileName);
			int x;
			while ((x = in.read()) != -1) {
				f.write(x);
			}
			f.flush();
			f.close();
		}
		in.close();
	}

	// �t�@�C���𓀁igzip�t�@�C���i���j
	private static void gzipExtract(final String zipFileName, final String fileName)
			throws IOException {
		// �ژ^�𐶐�����
		if (fileName.indexOf(FILE_SEPARATOR) != -1) {
			File file = new File(fileName.substring(0, fileName
					.lastIndexOf(FILE_SEPARATOR)));
			file.mkdirs();
		}

		FileOutputStream f = new FileOutputStream(fileName);
		GZIPInputStream in = new GZIPInputStream(new BufferedInputStream(
				new CheckedInputStream(new FileInputStream(zipFileName),
						new Adler32())));

		int c;
		while ((c = in.read()) != -1) {
			f.write(c);
		}
		f.close();
		in.close();
	}

	public static void extract(final String zipFileName, final String fileName)
			throws IOException {
		try {
			new ZipFile(zipFileName);
			zipExtract(zipFileName, fileName);
		} catch (ZipException zex) {
			gzipExtract(zipFileName, fileName);
		}
	}

	public static void compress(final String fileName, final String zipFileName)
			throws IOException {
		String FileName = new String(fileName);
		boolean bNeedAddPrefix = true;
		/** \\server\sharefolder\xxx���FullPath�@ */
		if (FileName.substring(0, 2).equals("\\\\")) {
			bNeedAddPrefix = false;
		}
		/** x:\���FullPath�@ */
		if (bNeedAddPrefix && (FileName.indexOf(":\\") != -1)) {
			bNeedAddPrefix = false;
		}

		/*
		 * if ( FileName.indexOf('\\') == -1 && FileName.indexOf('/') == -1 ) {
		 * FileName = ".\\" + FileName ; }
		 */
		if (bNeedAddPrefix) {
			FileName = ".\\" + FileName;
		}

		zipCompress(FileName, zipFileName);
	}
}