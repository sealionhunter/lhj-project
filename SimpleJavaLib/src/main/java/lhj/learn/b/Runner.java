package lhj.learn.b;

public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sA = "test";
		changeStr1(sA);
		System.out.println(sA);

		changeStr2(sA);
		System.out.println(sA);

		A a = new A();
		change1(a);
		System.out.println(a.getAll());
		change2(a);
		System.out.println(a.getAll());
		change3(a);
		System.out.println(a.getAll());
	}

	private static void changeStr1(String str) {
		str = "test1";
	}

	private static void changeStr2(String str) {
		str = new String("test2");
	}

	private static void change1(A a) {
		a.setA(3);
		a.setB(6);
	}

	private static void change2(A a) {
		a = new A();
		a.setA(9);
		a.setB(8);
	}

	private static void change3(A a) {
		a.setA(6);
		a = new A();
		a.setA(5);
		a.setB(7);
	}

}
