package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��16��
 * @author Cherami
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * ����һ�������ñ��ĸ��еĶ��뷽ʽ�ı��Ԫ��Ⱦ����
 * @since  0.1
 */

public class AlignmentCellRender
    extends DefaultTableCellRenderer {
  int columns[];
  int aligments[];
  int defaultAligment;
  /**
   * ����һ��ȱʡ��AlignmentCellRender��
   * ȱʡ���뷽ʽΪ������롣
   * @since  0.1
   */
  public AlignmentCellRender() {
    defaultAligment = LEFT;
    columns = new int[0];
    aligments = new int[0];
  }

  /**
   * ����ָ����ȱʡ���뷽ʽ����һ��AlignmentCellRender��
   * @param defaultAligment ȱʡ�Ķ��뷽ʽ
   * @since  0.3
   */
  public AlignmentCellRender(int defaultAligment) {
    this.defaultAligment = defaultAligment;
    columns = new int[0];
    aligments = new int[0];
  }

  /**
   * ����ָ�����еĶ��뷽ʽ�����ù���һ��AlignmentCellRender��
   * ȱʡ���뷽ʽΪ������롣
   * @param columns Ҫ���ö��뷽ʽ����
   * @param aligments ��Ӧ�Ķ��뷽ʽ
   * @since  0.1
   */
  public AlignmentCellRender(int columns[], int aligments[]) {
    this(LEFT, columns, aligments);
  }

  /**
   * ����ȱʡ���뷽ʽ��ָ�����еĶ��뷽ʽ�����ù���һ��AlignmentCellRender��
   * @param defaultAligment ȱʡ���뷽ʽ
   * @param columns  Ҫ���ö��뷽ʽ����
   * @param aligments ��Ӧ�Ķ��뷽ʽ
   * @since  0.3
   */
  public AlignmentCellRender(int defaultAligment, int columns[], int aligments[]) {
    if (columns.length != aligments.length) {
      throw new IllegalArgumentException(
          "The length of two arguments must be equal.");
    }
    this.defaultAligment = defaultAligment;
    copyArrayValue(columns, aligments);
  }

  /**
   * �Ա��Ԫ������Ⱦ��
   * @param table Ҫ��Ⱦ�ı��
   * @param value ���Ԫ��ֵ
   * @param isSelected �Ƿ�ѡ��
   * @param hasFocus �Ƿ�õ�����
   * @param row ����
   * @param column ����
   * @return ����Ⱦ��ı��Ԫ
   * @since  0.1
   */
  public Component getTableCellRendererComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 boolean hasFocus, int row,
                                                 int column) {
    for (int i = 0; i < columns.length; i++) {
      if (column == columns[i]) {
        setHorizontalAlignment(aligments[i]);
        setValue(value);
        return this;
      }
    }
    setHorizontalAlignment(defaultAligment);
    setValue(value);
    return this;
  }

  /**
   * ����ȱʡ���뷽ʽ��
   * @param aligment ȱʡ���뷽ʽ
   * @since  0.1
   */
  public void setDefaultAligment(int aligment) {
    defaultAligment = aligment;
  }

  /**
   * �������ø��еĶ��뷽ʽ��
   * @param columns Ҫ���ö��뷽ʽ����
   * @param aligments ��Ӧ�Ķ��뷽ʽ
   * @since  0.3
   */
  public void setAligments(int columns[], int aligments[]) {
    if (columns.length != aligments.length) {
      throw new IllegalArgumentException(
          "The length of two arguments must be equal.");
    }
    copyArrayValue(columns, aligments);
  }

  /**
   * ����ĳһ�еĶ��뷽ʽ��
   * ��������Ѿ������ù������µ�ֵ�滻ԭ����ֵ��
   * @param column Ҫ���ö��뷽ʽ����
   * @param aligment ��Ӧ�Ķ��뷽ʽ
   * @since  0.3
   */
  public void setAligment(int column, int aligment) {
    boolean haveOne = false;
    for (int i = 0; i < columns.length; i++) {
      if (column == columns[i]) {
        aligments[i] = aligment;
        haveOne = true;
        break;
      }
    }
    if (!haveOne) {
      int[] oldColumns = columns;
      int[] oldAligments = aligments;
      copyArrayValue(oldColumns, oldAligments);
      columns[oldColumns.length] = column;
      aligments[oldColumns.length] = aligment;
    }
  }

  /**
   * �������顣
   * @param columnArray ������
   * @param aligmentArray ���뷽ʽ����
   */
  private void copyArrayValue(int[] columnArray, int[] aligmentArray) {
    this.columns = new int[columnArray.length];
    this.aligments = new int[columnArray.length];
    for (int i = 0; i < columnArray.length; i++) {
      this.columns[i] = columnArray[i];
      this.aligments[i] = aligmentArray[i];
    }
  }
}