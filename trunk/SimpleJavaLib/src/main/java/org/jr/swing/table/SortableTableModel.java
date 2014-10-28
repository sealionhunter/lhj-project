package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��2��
 * @author Cherami
 */

import javax.swing.table.*;

import org.jr.util.*;

/**
 * ����������ģ�͡�
 * @since  0.1
 */

public class SortableTableModel
    extends DefaultTableModel {
  int[] indexes;
  TableSorter sorter;
  /**
   * ����һ��SortableTableModel��
   * @since  0.1
   */
  public SortableTableModel() {
  }

  /**
   * �õ������ı��Ԫ��ֵ��
   * @param row ��
   * @param col ��
   * @return �õ������ı��Ԫ��ֵ��
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
   * ���ñ��Ԫ��ֵ��
   * @param value ���Ԫ��ֵ
   * @param row ��
   * @param col ��
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
   * ��������
   * @param column Ҫ�������
   * @param isAscent ����ʽ
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
   * �õ��������������顣
   * @return ��������������
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
