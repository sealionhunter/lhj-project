package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��21��
 * @author Cherami
 */

import java.text.*;

/**
 * ���������ݵĵ�Ԫ��Ⱦ����
 * @since  0.5
 */

public class DateCellRenderer
    extends FormatCellRenderer {
  public static final int DATE = 0;
  public static final int TIME = 1;
  public static final int DATETIME = 2;
  public static final int defaultDateStyle = DateFormat.LONG;
  public static final int defaultTimeStyle = DateFormat.LONG;
  int type;
  int dateStyle;
  int timeStyle;

  /**
   * ����һ����ʾ���ڵ�DateCellRenderer��
   * @since  0.5
   */
  public DateCellRenderer() {
    this(DATETIME, defaultDateStyle, defaultTimeStyle);
  }

  /**
   * ����һ����ʾ���ڵ�DateCellRenderer��
   * @param type �������ͣ����ڡ�ʱ��������ڼ�ʱ�䣩
   * @since  0.5
   */
  public DateCellRenderer(int type) {
    this(type, defaultDateStyle, defaultTimeStyle);
  }

  /**
   * ����һ����ʾ���ڵ�DateCellRenderer��
   * @param type �������ͣ����ڡ�ʱ��������ڼ�ʱ�䣩
   * @param style ��ʾ���
   * @since  0.5
   */
  public DateCellRenderer(int type, int style) {
    this(type, style, defaultTimeStyle);
  }

  /**
   * ����һ����ʾ���ڵ�DateCellRenderer��
   * <b>��Ҫע��������type�����Ͳ������ڼ�ʱ�䣬��ô��ʹ��dateStyle��Ϊ���
   * ֻ����type�����ڼ�ʱ���ʱ��ڶ����������á�</b>
   * @param type �������ͣ����ڡ�ʱ��������ڼ�ʱ�䣩
   * @param dateStyle ���ڵ���ʾ���
   * @param timeStyle ʱ�����ʾ���
   * @since  0.5
   */
  public DateCellRenderer(int type, int dateStyle, int timeStyle) {
    this.type = type;
    this.dateStyle = dateStyle;
    this.timeStyle = timeStyle;
    switch (type) {
      case DATE:
        formatter = DateFormat.getDateInstance(dateStyle);
        break;
      case TIME:
        formatter = DateFormat.getTimeInstance(dateStyle);
        break;
      case DATETIME:
        formatter = DateFormat.getDateTimeInstance(dateStyle, timeStyle);
        break;
      default:
        formatter = DateFormat.getDateInstance(dateStyle);
        this.type = DATE;
    }

  }

}
