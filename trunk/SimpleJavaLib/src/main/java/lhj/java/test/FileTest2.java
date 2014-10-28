package lhj.java.test;


public class FileTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test(args);
	}
	
	public static void test(String[] args)  {
		for (int i = 0; i < 20; i++) {
			System.out.println((2 << i) + " " + i);
		}
//		try {
//			RandomAccessFile raf = new RandomAccessFile("test.txt", "rw");
//			if (raf.length() > 100) {
//			raf.setLength(raf.length() - 110);
//			}
//			raf.close();
//			FileWriter writer = new FileWriter("test.txt",true);
//			FileReader reader = new FileReader("test.txt");
//			for (int i = 0; i < 1024; i++)
//			writer.write("this is a test file\n");
//			char[] ch = new char[1024];
//			int count = reader.read(ch);
//			System.out.println(count);
//			writer.flush();
//			writer.close();
//			reader.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
