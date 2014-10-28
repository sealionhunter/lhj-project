package lhj.learn.b;

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

	public int getAll() {
		return a * b;
	}

}
