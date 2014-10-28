package lhj.learn.c;

public class Runner {
	public static void main(String[] args) throws InterruptedException {
		//for (int i = 0; i < 10; i++) {
		A a = new A();
		B b = new B();
		C c = new C();
		a.b = b;
		b.b = c;
		c.b = a;
		a.start();
		b.start();
		c.start();
//		synchronized (a) {
//			a.notifyAll();
//		}
//		System.out.println();
	//	}
	}

}
