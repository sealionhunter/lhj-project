package lhj.learn.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateAdd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		System.out.println(sdf.format(cl.getTime()));
		
//		cl.add(Calendar.MONTH, -1);
//		System.out.println(sdf.format(cl.getTime()));
//
//		cl.add(Calendar.MONTH, -11);
//		System.out.println(sdf.format(cl.getTime()));
//
//		cl.add(Calendar.MONTH, -12);
//		System.out.println(sdf.format(cl.getTime()));
//
//		cl.add(Calendar.MONTH, -13);
//		System.out.println(sdf.format(cl.getTime()));
//
//		cl.add(Calendar.MONTH, -23);
//		System.out.println(sdf.format(cl.getTime()));
//
//		cl.add(Calendar.MONTH, -24);
//		System.out.println(sdf.format(cl.getTime()));
//		
//		cl.add(Calendar.MONTH, -25);
//		System.out.println(sdf.format(cl.getTime()));
		
		cl.add(Calendar.DAY_OF_MONTH, -97);
		System.out.println(sdf.format(cl.getTime()));
		cl.add(Calendar.DAY_OF_MONTH, -367);
		System.out.println(sdf.format(cl.getTime()));
		cl.add(Calendar.DAY_OF_MONTH, -367);
		System.out.println(sdf.format(cl.getTime()));
		cl.add(Calendar.DAY_OF_MONTH, -367);
		System.out.println(sdf.format(cl.getTime()));
		cl.add(Calendar.DAY_OF_MONTH, -367);
		System.out.println(sdf.format(cl.getTime()));

	}

}
