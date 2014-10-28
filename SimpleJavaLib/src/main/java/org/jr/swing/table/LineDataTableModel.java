package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��14��
 * @author Cherami
 */

import java.util.*;

import javax.swing.table.*;

import org.jr.util.*;

/**
 * ʹ��TableLineData��Ϊ���ݵı��ģ�͡�
 * ����TableLineData�����ԣ�����ǲ��������еģ����Ǳ���ĳһ���ǿ�����ȫ���صģ���������ͷ��
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
   * ����ָ������ͷ����һ��LineDataTableModel��
   * @param columnNames ��ͷ������
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
   * ����ָ���ĳ�ʼ���ݹ���һ��LineDataTableModel��
   * @param columnNames ��ͷ����
   * @param data ��ʼ����
   * @since  0.5
   */
  public LineDataTableModel(String[] columnNames, TableLineData[] data) {
    //TOTHINK ͬ�����͵��жϼ�����
    this(columnNames);
    ListUtil.addArrayToList(data, this.data);
  }

  /**
   * �õ����Ԫ��ֵ��
   * @param row ��
   * @param col ��
   * @return �õ������ı��Ԫ��ֵ��
   * @since  0.5
   */
  public Object getValueAt(int row, int col) {
    return ( (TableLineData) (data.get(row))).get(columnIndexes[col]);
  }

  /**
   * ���ñ��Ԫ��ֵ��
   * @param value ���Ԫ��ֵ
   * @param row ��
   * @param col ��
   * @since  0.5
   */
  public void setValueAt(Object value, int row, int col) {
    ( (TableLineData) (data.get(row))).set(columnIndexes[col], value);
  }

  /**
   * �õ����ģ���е����ݵ�������
   * @return ���ģ���е����ݵ�����
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
   * �õ����ģ���еĿɼ��е�����
   * @return �ɼ��е�����
   * @since  0.5
   */
  public int getColumnCount() {
    return columnCount;
  }

  /**
   * �õ�ָ���е��������ɼ��С�
   * @param column ��
   * @return ��Ӧ������
   * @since  0.5
   */
  public String getColumnName(int column) {
    return (String) columnNames.get(column);
  }

  /**
   * ����һ�����ݵ�ģ�͵����
   * @param rowData һ������
   * @since  0.5
   */
  public void addRow(TableLineData rowData) {
    data.add(rowData);
    fireTableRowsInserted(data.size(), data.size());
  }

  /**
   * ����һ�����ݵ�ָ���С�
   * @param row ����
   * @param rowData һ������
   * @since  0.5
   */
  public void insertRow(int row, TableLineData rowData) {
    data.add(row, rowData);
    fireTableRowsInserted(row, row);
  }

  /**
   * ��Ӷ������ݵ�ģ�͵����
   * @param data ��������
   * @since  0.5
   */
  public void addRows(TableLineData[] data) {
    ListUtil.addArrayToList(data, this.data);
    fireTableRowsInserted(this.data.size() - data.length, this.data.size());
  }

  /**
   * ����������ݵ�ָ���С�
   * @param row �к�
   * @param data ��������
   * @since  0.5
   */
  public void insertRows(int row, TableLineData[] data) {
    ListUtil.addArrayToList(data, this.data, row);
    fireTableRowsInserted(row, row + data.length);
  }

  /**
   * ɾ��ָ���С�
   * @param row �к�
   * @since  0.5
   */
  public void removeRow(int row) {
    TableLineData removedRow = (TableLineData) data.remove(row);
    fireTableRowsDeleted(row, row);
  }

  /**
   * ����������ݡ�
   * @since  0.5
   */
  public void clear() {
    data.clear();
    fireTableDataChanged();
  }

  /**
   * ��ָ���е������ƶ���ָ��λ�á�
   * @param start ��ʼ��
   * @param end ������
   * @param to �ƶ������к�
   * @since  0.5
   */
  public void moveRows(int start, int end, int to) {
    if (to != start) {
      ListUtil.moveElements(data, start, end, to);
      fireTableDataChanged();
    }
  }

  /**
   * ����ĳһ�еĿɼ��ԡ�
   * ���������ָ��ʼ��ʱԭʼ���е���š�
   * ���״̬��ԭ����ͬ�򲻻�����κβ�����
   * @param column ��
   * @param visible �ɼ���
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
   * ����ָ���е�״̬Ϊ���ء�
   * @param column ��
   */
  private void hiddenColumn(int column) {
    status[column] = false;
    columnNames.remove(originalColumnNames[column]);
    ArrayUtil.shiftArray(columnIndexes, column - hiddenColumnCount);
    columnCount--;
    hiddenColumnCount++;
  }

  /**
   * ����ָ���е�״̬Ϊ��ʾ��
   * @param column ��
   */
  private void showColumn(int column) {
    hiddenColumnCount--;
    status[column] = true;
    columnNames.add(column, originalColumnNames[column]);
    ArrayUtil.insertValueToArray(columnIndexes, column, column);
    columnCount++;
  }

  /**
   * �õ����б����ص��е�������
   * @return �����ص��е���������
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
   * �����е���ʾ״̬��
   * �����λ��ֵΪ1���ʾ��ʾ���У�������ʾ��
   * ���ڳ��������ĳ������ƣ��������ֻ�ܶ�64�����ڵ��н��п��ơ�
   * ����statusΪ5���ʾ��ʾ����ĵ�һ�к͵����У������Ķ�����ʾ��
   * @param status ����ɵ͵��ߵ�λΪ0����в���ʾ��������ʾ��
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
   * �����е���ʾ״̬��
   * ���boolean����Ķ�Ӧ��Ԫ�ص�ֵΪtrue����ʾ���У�������ʾ��
   * @param status ״̬���顣
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