package lhj.learn.a;

public class B extends A {
	public B() {
		System.out.println("B class constructor");
	}

	protected void innerMethod() {
		setA(4);
		setB(2);
	}


	protected void aMethod() {
		innerMethod(3, 3);
		// System.out.println(a * b);
		super.aMethod();
	}
}


