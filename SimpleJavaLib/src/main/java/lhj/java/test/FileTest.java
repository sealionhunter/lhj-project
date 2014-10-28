package lhj.java.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test(args);
	}
	
	public static void test(String[] args) {
		System.out.println("==============");
		Writer out = null;
		try {
		File dir = new File(args.length > 0 ? args[0] : "");
		checkFileAttr(dir);
		File f = new File(dir,System.currentTimeMillis()+ "");
		checkFileAttr(f);
		f = new File(dir, System.currentTimeMillis()+ "_test.txt");
		if (args.length > 1 && args[1].equals("1")) {
		if (!f.exists()) {
			if (!f.createNewFile()) {
				System.out.println("file not created! " + f.getAbsolutePath());
			}
		}}
		// f.c
		out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
		for (int i = 0; i < 1000000; i++)
		out.write("It was hard to decide.");
		out.write("DALM111/src/lmreport/flex_src/com/abdulqabiz/utils/ConstantForLocal.as");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/previewGraphStyleColorByPage.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/previewGraphStyleColorBySheet.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/previewGraphStyleNoneByPage.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/previewGraphStyleNoneBySheet.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/previewGraphStyleServiceByPage.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/previewGraphStyleServiceBySheet.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/searchGraphStyleColorByPage.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/searchGraphStyleColorBySheet.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/searchGraphStyleNoneByPage.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/searchGraphStyleNoneBySheet.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/searchGraphStyleServiceByPage.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/searchGraphStyleServiceBySheet.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/showGraphStyleColorByPage.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/showGraphStyleColorBySheet.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/showGraphStyleNoneByPage.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/showGraphStyleNoneBySheet.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/showGraphStyleServiceByPage.swf");
		out.write("DALM111/src/webui/src/webapps/jetspeed/swf/showGraphStyleServiceBySheet.swf");
		out.flush();
		} catch (Exception e) {
			System.out.println(e);
		} finally{
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace(System.out);
				}
			}
		}
	}
	
	private static void checkFileAttr(File f) {
		System.out.println();
		System.out.println("=======" + f.getAbsolutePath() + "=======");
		System.out.println("exists(): " + f.exists());
		if (!f.exists()) {
			System.out.println("mkdirs(): " + f.mkdirs());
		}
		System.out.println("getUsableSpace(): " + f.getUsableSpace());
		System.out.println("getFreeSpace(): " + f.getFreeSpace());
		System.out.println("getTotalSpace(): " + f.getTotalSpace());
		System.out.println("canExecute(): " + f.canExecute());
		System.out.println("canRead(): " + f.canRead());
		System.out.println("canWrite(): " + f.canWrite());
		System.out.println("isDirectory(): " + f.isDirectory());
		System.out.println("isFile(): " + f.isFile());
		System.out.println("isHidden(): " + f.isHidden());
		System.out.println();
	}

}
