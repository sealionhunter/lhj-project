package lhj.learn.c;

public class C extends Thread {

	A b;

	public void run() {
		for (int i = 0; i < 10; i++) {
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print("C");
				synchronized (b) {
					b.notifyAll();
				}
			}
		}
	}
}
