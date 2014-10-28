package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��9��
 * @author Cherami
 */
import org.jr.*;
import org.jr.util.*;

/**
 * ʹ��SortableTableLineData��Ϊ���ݵı��ģ�͡�
 * ����丸��LineDataTableModel����������������Ծ��Ǹ�����������
 * ������ʽ�Ѿ���SortableTableLineData��compareTo����ָ���ˡ�
 * @since  0.5
 */

public class SortableLineDataTableModel
    extends LineDataTableModel {
  protected int[] indexes;
  protected int sortColumn = -1;
  protected boolean isAscent;

  /**
   * ����ָ������ͷ����һ��SortableLineDataTableModel��
   * @param columnNames ��ͷ������
   * @since  0.5
   */
  public SortableLineDataTableModel(String[] columnNames) {
    super(columnNames);
    indexes = ArrayUtil.getInitedIntArray(0);
  }

  /**
   * ����ָ���ĳ�ʼ���ݹ���һ��SortableLineDataTableModel��
   * @param columnNames ��ͷ����
   * @param data ��ʼ����
   * @since  0.5
   */
  public SortableLineDataTableModel(String[] columnNames, TableLineData[] data) {
    super(columnNames, data);
    indexes = ArrayUtil.getInitedIntArray(data.length);
  }

  /**
   * ����ָ����������˳������
   * @param column �������
   * @param isAscent ����˳��
   * @since  0.5
   */
  public void sortByColumn(int column, boolean isAscent) {
    sortColumn = column;
    this.isAscent = isAscent;
    sort();
    fireTableDataChanged();
  }

  /**
   * ������������������
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
   * �õ������е�����
   * @return �����е�����
   * @since  0.5
   */
  public int getSortColumn() {
    return sortColumn;
  }

  /**
   * �õ�����˳��
   * @return ˳���ʱ�򷵻�true�����򷵻�false
   * @since  0.5
   */
  public boolean getSortOrder() {
    return isAscent;
  }

  /**
   * �������״̬�ص�δ����״̬��
   * @since  0.5
   */
  public void clearSort() {
    indexes = ArrayUtil.getInitedIntArray(columnCount);
    sortColumn = -1;
    fireTableDataChanged();
  }

  /**
   * �õ������ı��Ԫ��ֵ��
   * @param row ��
   * @param col ��
   * @return �õ������ı��Ԫ��ֵ��
   * @since  0.5
   */
  public Object getValueAt(int row, int col) {
    Object object = super.getValueAt(indexes[row], col);
    return object;
  }

  /**
   * ���ñ��Ԫ��ֵ��
   * @param value ���Ԫ��ֵ
   * @param row ��
   * @param col ��
   * @since  0.5
   */
  public void setValueAt(Object value, int row, int col) {
    super.setValueAt(value, indexes[row], col);
  }

  /**
   * �õ��������������顣
   * @return �������������飬���û���Ź���õ��ľ��ǳ�ʼ���顣
   * @since  0.5
   */
  public int[] getIndexes() {
    return indexes;
  }

  /**
   * ����һ�����ݵ�ģ�͵����
   * @param rowData һ������
   * @since  0.5
   */
  public void addRow(TableLineData rowData) {
    data.add(rowData);
    sort();
    fireTableDataChanged();
  }

  /**
   * ��Ӷ������ݵ�ģ�͵����
   * @param data ��������
   * @since  0.5
   */
  public void addRows(TableLineData[] data) {
    ListUtil.addArrayToList(data, this.data);
    sort();
    fireTableDataChanged();
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

}