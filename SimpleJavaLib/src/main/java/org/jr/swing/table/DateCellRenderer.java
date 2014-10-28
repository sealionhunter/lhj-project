package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月21日
 * @author Cherami
 */

import java.text.*;

/**
 * 日期型数据的单元渲染器。
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
   * 构造一个显示日期的DateCellRenderer。
   * @since  0.5
   */
  public DateCellRenderer() {
    this(DATETIME, defaultDateStyle, defaultTimeStyle);
  }

  /**
   * 构造一个显示日期的DateCellRenderer。
   * @param type 日期类型（日期、时间或者日期加时间）
   * @since  0.5
   */
  public DateCellRenderer(int type) {
    this(type, defaultDateStyle, defaultTimeStyle);
  }

  /**
   * 构造一个显示日期的DateCellRenderer。
   * @param type 日期类型（日期、时间或者日期加时间）
   * @param style 显示风格
   * @since  0.5
   */
  public DateCellRenderer(int type, int style) {
    this(type, style, defaultTimeStyle);
  }

  /**
   * 构造一个显示日期的DateCellRenderer。
   * <b>需要注意的是如果type的类型不是日期加时间，那么将使用dateStyle作为风格。
   * 只有在type是日期加时间的时候第二个风格才有用。</b>
   * @param type 日期类型（日期、时间或者日期加时间）
   * @param dateStyle 日期的显示风格
   * @param timeStyle 时间的显示风格
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
