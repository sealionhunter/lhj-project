package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月21日
 * @author Cherami
 */

import java.text.*;

/**
 * 以百分比的方式显示浮点值。
 * 注意，这个渲染器只能对Number类型的值有效并且是显示其float值的。
 * @since  0.5
 */

public class PercentCellRenderer
    extends FormatCellRenderer {

  /**
   * 构造一个显示百分比的PercentCellRenderer。
   * @since  0.5
   */
  public PercentCellRenderer() {
    formatter = NumberFormat.getPercentInstance();
  }

  /**
   * 将表格单元的值格式化。
   * 原始值必须是Number的子类，格式化器必须是NumberFormat。
   * 格式化的是value的浮点值。
   * @param value 原始值
   * @return 格式化以后的值
   * @since  0.5
   */
  protected String format(Object value) {
    return ( (NumberFormat) formatter).format( ( (Number) value).floatValue());
  }

}
