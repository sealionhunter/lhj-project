package lhj.learn.c;

public class A extends Thread {

	B b;

	public void run() {
		for (int i = 0; i < 10; i++) {
			synchronized (this) {
				try {
					wait(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print("A");

				synchronized (b) {
					b.notifyAll();
				}
			}
		}
	}
}
