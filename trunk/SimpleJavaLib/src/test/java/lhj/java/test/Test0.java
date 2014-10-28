/**
 * 
 */
package lhj.java.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hjliang
 * 
 */
public class Test0 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> t = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			t.add(i);
		}
		Collections.shuffle(t);
		for (int i = 0; i < 10; i++) {
			System.out.println(t.get(i));
		}
	}

}
