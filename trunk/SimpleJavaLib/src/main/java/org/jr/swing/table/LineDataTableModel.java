package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月14日
 * @author Cherami
 */

import java.util.*;

import javax.swing.table.*;

import org.jr.util.*;

/**
 * 使用TableLineData作为数据的表格模型。
 * 根据TableLineData的特性，表格是不能新增列的，但是表格的某一列是可以完全隐藏的，包括其表格头。
 * @since  0.5
 */
public class LineDataTableModel
    extends AbstractTableModel {
  protected ArrayList data;
  protected int columnCount;
  protected ArrayList columnNames;
  protected int[] columnIndexes;
  protected String[] originalColumnNames;
  protected boolean[] status;
  protected int hiddenColumnCount;

  /**
   * 根据指定的列头构造一个LineDataTableModel。
   * @param columnNames 列头的名字
   * @since  0.5
   */
  public LineDataTableModel(String[] columnNames) {
    this.columnNames = (ArrayList) ListUtil.ArrayToList(columnNames);
    originalColumnNames = columnNames;
    if (this.columnNames == null) {
      this.columnNames = new ArrayList(10);
      originalColumnNames = new String[0];
    }
    columnCount = this.columnNames.size();
    status = ArrayUtil.getInitedBooleanArray(columnCount, true);
    data = new ArrayList(10);
    columnIndexes = ArrayUtil.getInitedIntArray(columnCount);
  }

  /**
   * 根据指定的初始数据构造一个LineDataTableModel。
   * @param columnNames 列头名字
   * @param data 初始数据
   * @since  0.5
   */
  public LineDataTableModel(String[] columnNames, TableLineData[] data) {
    //TOTHINK 同质类型的判断及处理？
    this(columnNames);
    ListUtil.addArrayToList(data, this.data);
  }

  /**
   * 得到表格单元的值。
   * @param row 行
   * @param col 列
   * @return 得到排序后的表格单元的值。
   * @since  0.5
   */
  public Object getValueAt(int row, int col) {
    return ( (TableLineData) (data.get(row))).get(columnIndexes[col]);
  }

  /**
   * 设置表格单元的值。
   * @param value 表格单元的值
   * @param row 行
   * @param col 列
   * @since  0.5
   */
  public void setValueAt(Object value, int row, int col) {
    ( (TableLineData) (data.get(row))).set(columnIndexes[col], value);
  }

  /**
   * 得到表格模型中的数据的行数。
   * @return 表格模型中的数据的行数
   * @since  0.5
   */
  public int getRowCount() {
    if (data != null) {
      return data.size();
    }
    else {
      return 0;
    }
  }

  /**
   * 得到表格模型中的可见列的列数
   * @return 可见列的列数
   * @since  0.5
   */
  public int getColumnCount() {
    return columnCount;
  }

  /**
   * 得到指定列的列名，可见列。
   * @param column 列
   * @return 对应的列名
   * @since  0.5
   */
  public String getColumnName(int column) {
    return (String) columnNames.get(column);
  }

  /**
   * 增加一行数据到模型的最后。
   * @param rowData 一行数据
   * @since  0.5
   */
  public void addRow(TableLineData rowData) {
    data.add(rowData);
    fireTableRowsInserted(data.size(), data.size());
  }

  /**
   * 插入一行数据到指定行。
   * @param row 行数
   * @param rowData 一行数据
   * @since  0.5
   */
  public void insertRow(int row, TableLineData rowData) {
    data.add(row, rowData);
    fireTableRowsInserted(row, row);
  }

  /**
   * 添加多行数据到模型的最后。
   * @param data 多行数据
   * @since  0.5
   */
  public void addRows(TableLineData[] data) {
    ListUtil.addArrayToList(data, this.data);
    fireTableRowsInserted(this.data.size() - data.length, this.data.size());
  }

  /**
   * 插入多行数据到指定行。
   * @param row 行号
   * @param data 多行数据
   * @since  0.5
   */
  public void insertRows(int row, TableLineData[] data) {
    ListUtil.addArrayToList(data, this.data, row);
    fireTableRowsInserted(row, row + data.length);
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

  /**
   * 清除所有数据。
   * @since  0.5
   */
  public void clear() {
    data.clear();
    fireTableDataChanged();
  }

  /**
   * 将指定行的内容移动到指定位置。
   * @param start 开始行
   * @param end 结束行
   * @param to 移动到的行号
   * @since  0.5
   */
  public void moveRows(int start, int end, int to) {
    if (to != start) {
      ListUtil.moveElements(data, start, end, to);
      fireTableDataChanged();
    }
  }

  /**
   * 设置某一列的可见性。
   * 这里的列是指初始化时原始的列的序号。
   * 如果状态和原来相同则不会进行任何操作。
   * @param column 列
   * @param visible 可见性
   * @since  0.5
   */
  public void setVisible(int column, boolean visible) {
    if (column > 0 && column < columnCount && status[column] != visible) {
      if (visible == true) {
        showColumn(column);
        fireTableStructureChanged();
      }
      else if (visible == false) {
        hiddenColumn(column);
        fireTableStructureChanged();
      }
    }
  }

  /**
   * 设置指定列的状态为隐藏。
   * @param column 列
   */
  private void hiddenColumn(int column) {
    status[column] = false;
    columnNames.remove(originalColumnNames[column]);
    ArrayUtil.shiftArray(columnIndexes, column - hiddenColumnCount);
    columnCount--;
    hiddenColumnCount++;
  }

  /**
   * 设置指定列的状态为显示。
   * @param column 列
   */
  private void showColumn(int column) {
    hiddenColumnCount--;
    status[column] = true;
    columnNames.add(column, originalColumnNames[column]);
    ArrayUtil.insertValueToArray(columnIndexes, column, column);
    columnCount++;
  }

  /**
   * 得到所有被隐藏的列的列名。
   * @return 被隐藏的列的列名数组
   * @since  0.5
   */
  public String[] getHiddenColumns() {
    int length = originalColumnNames.length;
    int index = 0;
    String[] hiddenColumns = new String[length - columnCount];
    for (int i = 0; i < length; i++) {
      if (status[i] == false) {
        hiddenColumns[index] = originalColumnNames[i];
        index++;
      }
    }
    return hiddenColumns;
  }

  /**
   * 设置列的显示状态。
   * 如果该位的值为1则表示显示该列，否则不显示。
   * 由于长整型数的长度限制，这个方法只能对64列以内的列进行控制。
   * 例如status为5则表示显示最初的第一列和第三列，其他的都不显示。
   * @param status 如果由低到高的位为0则该列不显示，否则显示。
   * @since  0.5
   */
  public void setVisibleStatus(long status) {
    long value = 1;
    boolean haveChanged = false;
    for (int i = 0; i < columnCount; i++) {
      if ( (value & status) != 0 && this.status[i] == false) {
        showColumn(i);
        haveChanged = true;
      }
      else if ( (value & status) == 0 && this.status[i] == true) {
        hiddenColumn(i);
        haveChanged = true;
      }
      value = value << 1;
    }
    if (haveChanged == true) {
      fireTableStructureChanged();
    }
  }

  /**
   * 设置列的显示状态。
   * 如果boolean数组的对应的元素的值为true则显示该列，否则不显示。
   * @param status 状态数组。
   * @since  0.5
   */
  public void setVisibleStatus(boolean[] status) {
    boolean haveChanged = false;
    int length = Math.min(status.length, this.status.length);
    for (int i = 0; i < length; i++) {
      if (status[i] != this.status[i]) {
        if (status[i] == true) {
          showColumn(i);
        }
        else {
          hiddenColumn(i);
        }
        haveChanged = true;
      }
    }
    if (haveChanged == true) {
      fireTableStructureChanged();
    }
  }

  public int getRealIndex(int column) {
    return columnIndexes[column];
  }
}