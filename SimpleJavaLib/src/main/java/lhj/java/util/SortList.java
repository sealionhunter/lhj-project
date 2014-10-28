/*
 * Created on 2006/02/27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lhj.java.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sealion
 * 
 */
public class SortList {
	public static <T extends Comparable<?>> void quickSort(T[] t, int low,
			int high) {
		if (low < high) {
			int index = ddd(t, low, high);
			if (index == low) {
				quickSort(t, index + 1, high);
			} else if (index == high) {
				quickSort(t, low, high - 1);
			} else {
				quickSort(t, low, index);
				quickSort(t, index + 1, high);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends Comparable> int ddd(final T[] t, int low, int high) {
		T temp = t[low];
		while (low < high) {
			while (low < high && temp.compareTo(t[high]) < 0) {
				high--;
			}
			if (low < high) {
				t[low] = t[high];
				low++;
			}
			while (low < high && temp.compareTo(t[low]) > 0) {
				low++;
			}
			if (low < high) {
				t[high] = t[low];
				high--;
			}
		}
		t[low] = temp;
		return low;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends Comparable> void sort(T[] lst, int low, int high) {
		boolean isCompleted = true;
		do {
			isCompleted = true;
			T com = lst[low];

			for (int j = low + 1; j <= high; j++) {
				T c = lst[j];
				if (com.compareTo(c) > 0) {
					lst[j] = com;
					lst[j - 1] = c;
					isCompleted = false;
				} else {
					com = c;
				}
			}
		} while (!isCompleted);
	}

	@SuppressWarnings("rawtypes")
	public static <T extends Comparable> void sort1(T[] lst, int low, int high) {
		List<T> sl = new ArrayList<T>();
		sl.add(lst[low]);
		for (int i = low + 1; i <= high; i++) {
			T c = lst[i];
			int cnt = search(sl, c);
			sl.add(cnt, c);

		}
		for (int i = 0; i < high - low; i++) {
			lst[low + i] = sl.get(i);
		}
		// T[] t = (T[]) Array.newInstance(sl.get(0).getClass(), sl.size());
		// return sl.toArray(t);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends Comparable> int search(List<T> sl, T com) {
		int i = 0;
		int j = sl.size() - 1;
		while (i <= j) {
			int k = (i + j) >> 1;
			T c = sl.get(k);
			if (c.compareTo(com) < 0) {
				i = k + 1;
			} else if (c.compareTo(com) > 0) {
				j = k - 1;
			} else {
				return k;
			}
		}
		return i;
	}

	@SuppressWarnings("rawtypes")
	public static <T extends Comparable> int search(T[] sl, int from,
			Integer com) {
		return search(sl, from, sl.length - 1, com);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends Comparable> int search(T[] sl, int from, int to,
			T com) {
		int i = from;
		int j = to;
		while (i <= j) {
			int k = (i + j) >> 1;
			T c = sl[k];
			if (c.compareTo(com) < 0) {
				i = k + 1;
			} else if (c.compareTo(com) > 0) {
				j = k - 1;
			} else {
				return k;
			}
		}
		return -1;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private static final <T extends Comparable> int searchInsertIndex(T[] t,
			T o, int low, int high) {
		int i = low;
		int j = high;
		while (i <= j) {
			int k = (i + j) >> 1;
			if (t[k].compareTo(o) < 0) {
				i = k + 1;
			} else if (t[k].compareTo(o) > 0) {
				j = k - 1;
			} else {
				System.arraycopy(t, k, t, k + 1, high - k + 1);
				return k;
			}
		}
		System.arraycopy(t, i, t, i + 1, high - i + 1);
		return i;
	}

	protected static final int INSERTIONSORT_THRESHOLD = 1;

	public static <T extends Comparable<?>> void swap(T[] t, int i, int j) {
		T temp = t[i];
		t[i] = t[j];
		t[j] = temp;
	}

	/**
	 * Check that fromIndex and toIndex are in range, and throw an appropriate
	 * exception if they aren't.
	 */
	@SuppressWarnings("unused")
	private static void rangeCheck(int arrayLen, int fromIndex, int toIndex) {
		if (fromIndex > toIndex)
			throw new IllegalArgumentException("fromIndex(" + fromIndex
					+ ") > toIndex(" + toIndex + ")");
		if (fromIndex < 0)
			throw new ArrayIndexOutOfBoundsException(fromIndex);
		if (toIndex > arrayLen)
			throw new ArrayIndexOutOfBoundsException(toIndex);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends Comparable> T middle(T[] t, int one, int two,
			int three) {
		if (t[one].compareTo(t[two]) > 0 && t[one].compareTo(t[three]) > 0) {
			if (t[two].compareTo(t[three]) > 0) {
				return t[two];
			} else {
				return t[three];
			}
		} else if (t[one].compareTo(t[two]) < 0
				&& t[one].compareTo(t[three]) < 0) {
			if (t[two].compareTo(t[three]) < 0) {
				return t[two];
			} else {
				return t[three];
			}
		}
		return t[one];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T extends Comparable> T middle(T a, T b, T c) {
		if (a.compareTo(b) > 0 && a.compareTo(c) > 0) {
			if (b.compareTo(c) > 0) {
				return b;
			} else {
				return c;
			}
		} else if (a.compareTo(b) < 0 && a.compareTo(c) < 0) {
			if (b.compareTo(c) < 0) {
				return b;
			} else {
				return c;
			}
		}
		return a;
	}
}
