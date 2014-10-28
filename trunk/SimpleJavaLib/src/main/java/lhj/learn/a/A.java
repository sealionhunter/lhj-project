package lhj.learn.a;

public class A {
	private int a = 0;
	private int b = 0;

	protected void setA(int a) {
		this.a = a;
	}

	protected void setB(int b) {
		this.b = b;
	}

	protected int getA() {
		return a;
	}

	protected int getB() {
		return b;
	}

	public A() {
		System.out.println("A class constructor");
	}

	protected void innerMethod() {
		setA(5);
		setB(3);
	}

	protected void innerMethod(int numA, int numB) {
		setA(numA);
		setB(numB);
	}

	public void method() {
		this.aMethod();
	}

	protected void aMethod() {
		innerMethod();
		System.out.println(a * b);
	}
}


