package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��21��
 * @author Cherami
 */

import java.text.*;

/**
 * �԰ٷֱȵķ�ʽ��ʾ����ֵ��
 * ע�⣬�����Ⱦ��ֻ�ܶ�Number���͵�ֵ��Ч��������ʾ��floatֵ�ġ�
 * @since  0.5
 */

public class PercentCellRenderer
    extends FormatCellRenderer {

  /**
   * ����һ����ʾ�ٷֱȵ�PercentCellRenderer��
   * @since  0.5
   */
  public PercentCellRenderer() {
    formatter = NumberFormat.getPercentInstance();
  }

  /**
   * �����Ԫ��ֵ��ʽ����
   * ԭʼֵ������Number�����࣬��ʽ����������NumberFormat��
   * ��ʽ������value�ĸ���ֵ��
   * @param value ԭʼֵ
   * @return ��ʽ���Ժ��ֵ
   * @since  0.5
   */
  protected String format(Object value) {
    return ( (NumberFormat) formatter).format( ( (Number) value).floatValue());
  }

}
