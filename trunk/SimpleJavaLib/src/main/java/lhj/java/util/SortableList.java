/*
 * Created on 2006/02/27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lhj.java.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * @author sealion
 * 
 */
@SuppressWarnings("serial")
public class SortableList<E extends Comparable<?>> extends ArrayList<E>
		implements List<E> {

	public static void main(String[] args) {
		// SortableList sortList = new SortableList();
		Object[] a = new Integer[1000000];
		for (int i = 0; i < a.length; i++) {
			a[i] = Integer.valueOf(i);
		}
		long start = Calendar.getInstance().getTimeInMillis();
		System.out.println(start);
		Object[] b = new Integer[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = a[i];
		}
		long end = Calendar.getInstance().getTimeInMillis();
		System.out.println(end);
		System.out.println(end - start);
		Object[] c = new Integer[a.length];

		start = Calendar.getInstance().getTimeInMillis();
		System.out.println(start);
		System.arraycopy(a, 0, c, 0, a.length);
		end = Calendar.getInstance().getTimeInMillis();
		System.out.println(end);
		System.out.println(end - start);
	}

	/**
	 * @see java.util.List#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		if (checkComparable(o)) {
			return super.contains(o);
		}
		return false;
	}

	/**
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public Object[] toArray(Object[] arg0) {
		if (arg0 instanceof Comparable[]) {
			return super.toArray(arg0);
		}
		return new Object[0];
	}

	/**
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(E arg0) {
		if (checkComparable(arg0)) {
			return super.add(arg0);
		}
		return false;
	}

	/**
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		if (!checkComparable(o)) {
			return super.remove(o);
		}
		return false;
	}

	/**
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@SuppressWarnings({ "rawtypes" })
	public boolean containsAll(Collection arg0) {
		if (arg0 instanceof SortableList) {
			return super.containsAll(arg0);
		}
		return false;
	}

	/**
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean addAll(Collection arg0) {
		if (arg0 instanceof SortableList) {
			return super.addAll(arg0);
		}
		return false;
	}

	/**
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean addAll(int arg0, Collection arg1) {
		if (arg1 instanceof SortableList) {
			return super.addAll(arg0, arg1);
		}
		return false;
	}

	/**
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@SuppressWarnings({ "rawtypes" })
	public boolean removeAll(Collection arg0) {
		if (arg0 instanceof SortableList) {
			return super.removeAll(arg0);
		}
		return false;
	}

	/**
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@SuppressWarnings("rawtypes")
	public boolean retainAll(Collection arg0) {
		if (arg0 instanceof SortableList) {
			return super.retainAll(arg0);
		}
		return false;
	}

	/**
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	public E set(int arg0, E arg1) {
		if (checkComparable(arg1)) {
			return super.set(arg0, arg1);
		}
		return null;
	}

	/**
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	public void add(int arg0, E arg1) {
		if (checkComparable(arg1)) {
			super.add(arg0, arg1);
		}
	}

	/**
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	public int indexOf(Object o) {
		if (checkComparable(o)) {
			return super.indexOf(o);
		}
		return -1;
	}

	/**
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf(Object o) {
		if (checkComparable(o)) {
			return super.lastIndexOf(o);
		}
		return -1;
	}

	/**
	 * Check Is Object(o) comparable
	 * 
	 * @param o
	 *            The Object been checked
	 * @return true if o is instanceof Comparable, Otherwise false.
	 */
	public static boolean checkComparable(Object o) {
		if (o instanceof Comparable) {
			return true;
		}
		return false;
	}
}
