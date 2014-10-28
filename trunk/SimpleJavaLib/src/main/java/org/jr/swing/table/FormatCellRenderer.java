package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��21��
 * @author Cherami
 */

import java.text.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * ��һ����ʽ�������ʽ��Ϊ�ַ�����ʾ�ı��Ԫ��Ⱦ����
 * @since  0.5
 */

public class FormatCellRenderer
    extends DefaultTableCellRenderer
    implements TableCellRenderer {
  protected Format formatter;

  /**
   * ��ָ���ĸ�ʽ������һ��FormatCellRenderer��
   * @since  0.5
   */
  public FormatCellRenderer() {
    formatter = new MessageFormat("{0}");
  }

  /**
   * ��ָ���ĸ�ʽ������һ��FormatCellRenderer��
   * @since  0.5
   */
  public FormatCellRenderer(Format formatter) {
    this.formatter = formatter;
  }

  /**
   * �Ե�Ԫ������Ⱦ��
   * @param table Ҫ��Ⱦ�ı��
   * @param value ���Ԫ��ֵ��������Number���͵�ֵ
   * @param isSelected �Ƿ�ѡ��
   * @param hasFocus �Ƿ�õ�����
   * @param row ����
   * @param column ����
   * @return ����Ⱦ��ı��Ԫ
   * @since  0.5
   */
  public Component getTableCellRendererComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 boolean hasFocus,
                                                 int row,
                                                 int column) {
    return super.getTableCellRendererComponent(table, format(value), isSelected,
                                               hasFocus, row, column);
  }

  /**
   * �����Ԫ��ֵ��ʽ����
   * @param value ԭʼֵ
   * @return ��ʽ���Ժ��ֵ
   * @since  0.5
   */
  protected String format(Object value) {
    return formatter.format(value);
  }

  /**
   * ���ø�ʽ����
   * @param formatter ��ʽ��
   * @since  0.5
   */
  public void setFormatter(Format formatter) {
    this.formatter = formatter;
  }

  /**
   * �õ���ʹ�õĸ�ʽ����
   * @return ��ʹ�õĸ�ʽ��
   * @since  0.5
   */
  public Format getFormatter() {
    return formatter;
  }
}
