package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月1日
 * @author Cherami
 */

import java.util.*;

import org.jr.util.*;

/**
 * 表格排序器。
 * @since  0.1
 */

public class TableSorter {
  SortableTableModel model;
  /**
   * 根据指定的可排序表格模型构造一个TableSorter。
   * @param model 可排序表格模型
   * @since  0.1
   */
  public TableSorter(SortableTableModel model) {
    this.model = model;
  }

  /**
   * 排序。
   * @param column 要排序的列
   * @param isAscent 排序方式
   * @since  0.1
   */
  public void sort(int column, boolean isAscent) {
    int n = model.getRowCount();
    int[] indexes = model.getIndexes();

    for (int i = 0; i < n - 1; i++) {
      int k = i;
      for (int j = i + 1; j < n; j++) {
        if (isAscent) {
          if (compare(column, j, k) < 0) {
            k = j;
          }
        }
        else {
          if (compare(column, j, k) > 0) {
            k = j;
          }
        }
      }
      int tmp = indexes[i];
      indexes[i] = indexes[k];
      indexes[k] = tmp;
    }
  }

  /**
   * 比较指定列的两行的大小。
   * @param column 要比较的列
   * @param row1 第一行
   * @param row2 第二行
   * @return 第一个值大于第二个值则返回1，等于则等于0，否则返回-1。
   * @since  0.1
   */
  public int compare(int column, int row1, int row2) {
    Object o1 = model.getValueAt(row1, column);
    Object o2 = model.getValueAt(row2, column);
    if (o1 == null && o2 == null) {
      return 0;
    }
    else if (o1 == null) {
      return -1;
    }
    else if (o2 == null) {
      return 1;
    }
    else {
      Class type = model.getColumnClass(column);
      if (ClassUtil.isSubclass(Number.class, type)) {
        return CompareUtil.compare( (Number) o1, (Number) o2);
      }
      else if (type == String.class) {
        return ( (String) o1).compareTo( (String) o2);
      }
      else if (ClassUtil.isSubclass(Date.class, type)) {
        return ( (Date) o1).compareTo( (Date) o2);
      }
      else if (type == Boolean.class) {
        return CompareUtil.compare( (Boolean) o1, (Boolean) o2);
      }
      else {
        return ( (String) o1).compareTo( (String) o2);
      }
    }
  }

}