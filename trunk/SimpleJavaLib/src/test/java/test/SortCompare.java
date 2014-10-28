package test;

import java.util.Comparator;

public class SortCompare implements Comparator<SortBean> {
	private String sortField;

	public SortCompare(String sortField) {
		this.sortField = sortField;
	}

	public int compare(SortBean o1, SortBean o2) {
		if (o1 == null || o1.getValue(sortField) == null) {
			return -1;
		}
		if (o2 == null || o2.getValue(sortField) == null) {
			return 1;
		}

		return String.valueOf(o1.getValue(sortField)).compareTo(
				String.valueOf(o2.getValue(sortField)));
	}

}
