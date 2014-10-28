package lhj.java.util;

import java.util.Arrays;
import java.util.Calendar;

public class SortListTest {

	public static int COUNT = 100;

	public void testSort() {
		Integer[] sortList = new Integer[COUNT];
		for (int i = 0; i < COUNT; i++) {
			sortList[i] = Integer.valueOf(Math.round((float) (COUNT * Math
					.random())));
		}
		System.out.println("COUNT = " + COUNT);
		System.out.println("COUNT * (COUNT - 1) / 2 = " + COUNT * (COUNT - 1)
				/ 2);
		System.out.println("COUNT * Math.log(COUNT) = " + COUNT
				* Math.log(COUNT));

		Integer[] array = new Integer[COUNT];
		System.arraycopy(sortList, 0, array, 0, COUNT);

		long end, start = Calendar.getInstance().getTimeInMillis();
		System.out.println("--------------1111111111111111------------");
		Arrays.sort(array);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("START = " + start);
		System.out.println("END = " + end);
		System.out.println("SPEND = " + (end - start));

//		System.out.println("--------------2222222222222222------------");
//		System.arraycopy(sortList, 0, array, 0, COUNT);
//		start = Calendar.getInstance().getTimeInMillis();
//		SortList.sort1(array, 0, COUNT - 1);
//		end = Calendar.getInstance().getTimeInMillis();
//		System.out.println("START = " + start);
//		System.out.println("END = " + end);
//		System.out.println("SPEND = " + (end - start));

		System.out.println("--------------3333333333333333------------");
		System.arraycopy(sortList, 0, array, 0, COUNT);
		start = Calendar.getInstance().getTimeInMillis();
		SortList.quickSort(array, 0, COUNT - 1);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println("START = " + start);
		System.out.println("END = " + end);
		System.out.println("SPEND = " + (end - start));

//		System.out.println("--------------4444444444444444------------");
//		System.arraycopy(sortList, 0, array, 0, COUNT);
//		start = Calendar.getInstance().getTimeInMillis();
//		SortList.sort(array, 0, COUNT - 1);
//		end = Calendar.getInstance().getTimeInMillis();
//		System.out.println("START = " + start);
//		System.out.println("END = " + end);
//		System.out.println("SPEND = " + (end - start));
	}

	public void testQuickSort() {
		Integer[] sortList = new Integer[COUNT];
		Integer[] array = new Integer[COUNT];
		Integer[] array2 = new Integer[COUNT];
		for (int k = 0; k < 100; k++ ) {
		for (int i = 0; i < COUNT; i++) {
			sortList[i] = Integer.valueOf(Math.round((float) (COUNT * Math
					.random())));
		}
		printArray(sortList);

		System.arraycopy(sortList, 0, array, 0, COUNT);
		SortList.quickSort(array, 0, COUNT - 1);
		printArray(array);

		System.arraycopy(sortList, 0, array2, 0, COUNT);
		Arrays.sort(array2);
		printArray(array2);

		boolean isSame = true;
		for (int i = 0; i < COUNT; i++) {
			if (!array[i].equals(array2[i])) {
				isSame = false;
				break;
			}
		}
		System.out.print(isSame ? "" : " -----------------NG----------------- ");
		}
	}
	
	private <T> void printArray(T[] t) {
//		for (T in : t) {
//			System.out.print(in+ " ");
//		}
//		System.out.println();
	}
}
