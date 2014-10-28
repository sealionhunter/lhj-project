package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月9日
 * @author Cherami
 */
import org.jr.*;
import org.jr.util.*;

/**
 * 使用SortableTableLineData作为数据的表格模型。
 * 相对其父类LineDataTableModel，这个类新增的特性就是根据列名排序，
 * 而排序方式已经由SortableTableLineData的compareTo方法指定了。
 * @since  0.5
 */

public class SortableLineDataTableModel
    extends LineDataTableModel {
  protected int[] indexes;
  protected int sortColumn = -1;
  protected boolean isAscent;

  /**
   * 根据指定的列头构造一个SortableLineDataTableModel。
   * @param columnNames 列头的名字
   * @since  0.5
   */
  public SortableLineDataTableModel(String[] columnNames) {
    super(columnNames);
    indexes = ArrayUtil.getInitedIntArray(0);
  }

  /**
   * 根据指定的初始数据构造一个SortableLineDataTableModel。
   * @param columnNames 列头名称
   * @param data 初始数据
   * @since  0.5
   */
  public SortableLineDataTableModel(String[] columnNames, TableLineData[] data) {
    super(columnNames, data);
    indexes = ArrayUtil.getInitedIntArray(data.length);
  }

  /**
   * 根据指定的列名和顺序排序。
   * @param column 排序的列
   * @param isAscent 排序顺序
   * @since  0.5
   */
  public void sortByColumn(int column, boolean isAscent) {
    sortColumn = column;
    this.isAscent = isAscent;
    sort();
    fireTableDataChanged();
  }

  /**
   * 排序设置排序后的索引
   * @since  0.5
   */
  protected void sort() {
    if (sortColumn >= 0 && sortColumn < columnCount) {
      indexes = CompareUtil.n2sort( (PropertyComparable[]) data.toArray(new
          PropertyComparable[0]), sortColumn, isAscent);
    }
    else {
      indexes = ArrayUtil.getInitedIntArray(data.size());
    }
  }

  /**
   * 得到排序列的列名
   * @return 排序列的列名
   * @since  0.5
   */
  public int getSortColumn() {
    return sortColumn;
  }

  /**
   * 得到排序顺序
   * @return 顺序的时候返回true，否则返回false
   * @since  0.5
   */
  public boolean getSortOrder() {
    return isAscent;
  }

  /**
   * 清除排序状态回到未排序状态。
   * @since  0.5
   */
  public void clearSort() {
    indexes = ArrayUtil.getInitedIntArray(columnCount);
    sortColumn = -1;
    fireTableDataChanged();
  }

  /**
   * 得到排序后的表格单元的值。
   * @param row 行
   * @param col 列
   * @return 得到排序后的表格单元的值。
   * @since  0.5
   */
  public Object getValueAt(int row, int col) {
    Object object = super.getValueAt(indexes[row], col);
    return object;
  }

  /**
   * 设置表格单元的值。
   * @param value 表格单元的值
   * @param row 行
   * @param col 列
   * @since  0.5
   */
  public void setValueAt(Object value, int row, int col) {
    super.setValueAt(value, indexes[row], col);
  }

  /**
   * 得到排序后的索引数组。
   * @return 排序后的索引数组，如果没有排过序得到的就是初始数组。
   * @since  0.5
   */
  public int[] getIndexes() {
    return indexes;
  }

  /**
   * 增加一行数据到模型的最后。
   * @param rowData 一行数据
   * @since  0.5
   */
  public void addRow(TableLineData rowData) {
    data.add(rowData);
    sort();
    fireTableDataChanged();
  }

  /**
   * 添加多行数据到模型的最后。
   * @param data 多行数据
   * @since  0.5
   */
  public void addRows(TableLineData[] data) {
    ListUtil.addArrayToList(data, this.data);
    sort();
    fireTableDataChanged();
  }

  /**
   * 删除指定行。
   * @param row 行号
   * @since  0.5
   */
  public void removeRow(int row) {
    TableLineData removedRow = (TableLineData) data.remove(row);
    fireTableRowsDeleted(row, row);
  }

}