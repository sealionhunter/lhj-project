package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月21日
 * @author Cherami
 */

import java.text.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * 以一定格式将对象格式化为字符串显示的表格单元渲染器。
 * @since  0.5
 */

public class FormatCellRenderer
    extends DefaultTableCellRenderer
    implements TableCellRenderer {
  protected Format formatter;

  /**
   * 以指定的格式器生成一个FormatCellRenderer。
   * @since  0.5
   */
  public FormatCellRenderer() {
    formatter = new MessageFormat("{0}");
  }

  /**
   * 以指定的格式器生成一个FormatCellRenderer。
   * @since  0.5
   */
  public FormatCellRenderer(Format formatter) {
    this.formatter = formatter;
  }

  /**
   * 对单元进行渲染。
   * @param table 要渲染的表格
   * @param value 表格单元的值，必须是Number类型的值
   * @param isSelected 是否选中
   * @param hasFocus 是否得到焦点
   * @param row 行数
   * @param column 列数
   * @return 经渲染后的表格单元
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
   * 将表格单元的值格式化。
   * @param value 原始值
   * @return 格式化以后的值
   * @since  0.5
   */
  protected String format(Object value) {
    return formatter.format(value);
  }

  /**
   * 设置格式器。
   * @param formatter 格式器
   * @since  0.5
   */
  public void setFormatter(Format formatter) {
    this.formatter = formatter;
  }

  /**
   * 得到所使用的格式器。
   * @return 所使用的格式器
   * @since  0.5
   */
  public Format getFormatter() {
    return formatter;
  }
}
