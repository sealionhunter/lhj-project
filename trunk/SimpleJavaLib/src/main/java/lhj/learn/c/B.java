package lhj.learn.c;

public class B extends Thread {

	C b;

	public void run() {
		for (int i = 0; i < 10; i++) {
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print("B");
				synchronized (b) {
					b.notifyAll();
				}
			}
		}
	}
}
