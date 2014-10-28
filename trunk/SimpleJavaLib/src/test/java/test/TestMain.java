//package lm.test;
//
//
//public class TestMain {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) throws Exception {
//		int index = 0;
//		int tmp = 8183;
//		while (tmp > 0) {
//			if ((tmp & 1) != 0) {
//				// selectList.add(LMLogAutoReportConst.DV_DISP_ITEM[index]);
//				System.out.println(index);
//			}
//			index++;
//			tmp = tmp >> 1;
//		}		
////		for (int i = 0; i < 40; i++) {
////			new Test("test" + i).start();
////		}
//	}
//}
//
//class Test extends Thread {
//	private static final Object lock = new Object();
//	private static int execCount = 0;
//	private String name;
//
//	public Test(String name) {
//		this.name = name;
//		// c = (count++);
//	}
//
//	public void run() {
//		try {
//			synchronized (lock) {
//				while (execCount >= 5) {
//					lock.wait();
//				}
//				execCount++;
//			}
//			System.out.println("0" + name);
//			Thread.sleep(100);
//			System.out.println("1" + name);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//
//			System.out.println(name + e.getMessage());
//			e.printStackTrace();
//		} finally {
//			synchronized (lock) {
//				execCount--;
//				lock.notify();
//			}
//		}
//	}
//}