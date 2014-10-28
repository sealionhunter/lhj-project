package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月2日
 * @author Cherami
 */

import javax.swing.table.*;

import org.jr.util.*;

/**
 * 可以排序表格模型。
 * @since  0.1
 */

public class SortableTableModel
    extends DefaultTableModel {
  int[] indexes;
  TableSorter sorter;
  /**
   * 构造一个SortableTableModel。
   * @since  0.1
   */
  public SortableTableModel() {
  }

  /**
   * 得到排序后的表格单元的值。
   * @param row 行
   * @param col 列
   * @return 得到排序后的表格单元的值。
   * @since  0.1
   */
  public Object getValueAt(int row, int col) {
    int rowIndex = row;
    if (indexes != null) {
      rowIndex = indexes[row];
    }
    return super.getValueAt(rowIndex, col);
  }

  /**
   * 设置表格单元的值。
   * @param value 表格单元的值
   * @param row 行
   * @param col 列
   * @since  0.1
   */
  public void setValueAt(Object value, int row, int col) {
    int rowIndex = row;
    if (indexes != null) {
      rowIndex = indexes[row];
    }
    super.setValueAt(value, rowIndex, col);
  }

  /**
   * 按列排序。
   * @param column 要排序的列
   * @param isAscent 排序方式
   * @since  0.1
   */
  public void sortByColumn(int column, boolean isAscent) {
    if (sorter == null) {
      sorter = new TableSorter(this);
    }
    sorter.sort(column, isAscent);
    fireTableDataChanged();
  }

  /**
   * 得到排序后的索引数组。
   * @return 排序后的索引数组
   * @since  0.1
   */
  public int[] getIndexes() {
    int n = getRowCount();
    if (indexes != null) {
      return indexes;
    }
    else {
      return ArrayUtil.getInitedIntArray(n);
    }
  }
}
