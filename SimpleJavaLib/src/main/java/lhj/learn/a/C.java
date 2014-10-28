package lhj.learn.a;

public class C extends A {

	protected void innerMethod() {
		setA(7);
		setB(8);
	}

	protected void aMethod() {
		System.out.println(getA() * getB());
	}
}
