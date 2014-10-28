package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��1��
 * @author Cherami
 */

import java.util.*;

import org.jr.util.*;

/**
 * �����������
 * @since  0.1
 */

public class TableSorter {
  SortableTableModel model;
  /**
   * ����ָ���Ŀ�������ģ�͹���һ��TableSorter��
   * @param model ��������ģ��
   * @since  0.1
   */
  public TableSorter(SortableTableModel model) {
    this.model = model;
  }

  /**
   * ����
   * @param column Ҫ�������
   * @param isAscent ����ʽ
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
   * �Ƚ�ָ���е����еĴ�С��
   * @param column Ҫ�Ƚϵ���
   * @param row1 ��һ��
   * @param row2 �ڶ���
   * @return ��һ��ֵ���ڵڶ���ֵ�򷵻�1�����������0�����򷵻�-1��
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