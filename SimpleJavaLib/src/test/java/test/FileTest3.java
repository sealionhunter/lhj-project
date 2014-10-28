package test;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileTest3 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile("test.txt", "rw");
		raf.writeUTF("http://y.10086.cn/i?f=20&i=88776655");
		raf.close();
		// TODO Auto-generated method stub

	}

}
