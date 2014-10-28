package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月14日
 * @author Cherami
 */

import org.jr.text.*;

/**
 * 可以自动的以可读的方式显示大小的表格单元渲染器。
 * 注意，这个渲染器只能对Number类型的值有效并且是显示其long值的。
 * @since  0.5
 */

public class ReadableSizeCellRenderer
    extends FormatCellRenderer {
  boolean autoReadable = true;
  int unit = -1;

  /**
   * 构造一个自动显示可读大小并且显示单位的ReadableSizeCellRenderer。
   * @since  0.5
   */
  public ReadableSizeCellRenderer() {
    formatter = new FileSizeFormat(true);
  }

  /**
   * 根据指定的参数构造一个ReadableSizeCellRenderer。
   * 显示尺寸单位。
   * @param autoReadable 是否自动设置大小的单位
   * @param unit 单位，此单位只有在autoReadable设置为false时才有效
   * @since  0.5
   */
  public ReadableSizeCellRenderer(boolean autoReadable, int unit) {
    this.autoReadable = autoReadable;
    this.unit = unit;
    formatter = new FileSizeFormat(true);
  }

  /**
   * 根据指定的参数构造一个ReadableSizeCellRenderer。
   * @param autoReadable 是否自动设置大小的单位
   * @param showUnit 是否显示单位
   * @since  0.5
   */
  public ReadableSizeCellRenderer(boolean autoReadable, boolean showUnit) {
    this.autoReadable = autoReadable;
    formatter = new FileSizeFormat(showUnit);
  }

  /**
   * 根据指定的参数构造一个ReadableSizeCellRenderer。
   * @param autoReadable 是否自动设置大小的单位
   * @param showUnit 是否显示单位
   * @param unit 单位，此单位只有在autoReadable设置为false时才有效
   * @since  0.5
   */
  public ReadableSizeCellRenderer(boolean autoReadable, boolean showUnit,
                                  int unit) {
    this.autoReadable = autoReadable;
    formatter = new FileSizeFormat(showUnit);
    this.unit = unit;
  }

  /**
   * 将表格单元的值格式化。
   * @param value 原始值
   * @return 格式化以后的值
   * @since  0.5
   */
  protected String format(Object value) {
    String resultValue;
    if ( ( (Number) value).longValue() > 0) {
      if (autoReadable == true) {
        resultValue = formatter.format( (Number) value);
      }
      else {
        resultValue = ( (FileSizeFormat) formatter).format( (Number) value,
            unit);
      }
    }
    else {
      resultValue = "";
    }
    return resultValue;
  }

  /**
   * 设置是否自动判断大小。
   * @param autoReadable 是否自动判断大小
   * @since  0.5
   */
  public void setAutoReadable(boolean autoReadable) {
    this.autoReadable = autoReadable;
    if (autoReadable == true && unit == -1) {
      unit = FileSizeFormat.BYTE;
    }
  }

  /**
   * 是否自动判断大小
   * @return 自动判断时返回true，否则返回false
   * @since  0.5
   */
  public boolean isAutoReadable() {
    return autoReadable;
  }

  /**
   * 设置显示的单位。
   * 调用此方法会将自动判断模式设置为false并使用指定的单位进行显示。
   * @param unit 单位
   * @since  0.5
   */
  public void setUnit(int unit) {
    autoReadable = false;
    this.unit = unit;
  }

  /**
   * 得到所使用的单位。
   * <b>注意：</b>在自动模式下此方法的返回值是没有意义的，因此返回的是-1。
   * @return 自动模式下返回-1，否则返回指定的单位
   * @since  0.5
   */
  public int getUnit() {
    if (autoReadable == true) {
      return unit;
    }
    else {
      return -1;
    }
  }

  /**
   * 设置是否显示单位。
   * @param unitNameVisible 是否显示单位
   * @since  0.5
   */
  public void setUnitNameVisible(boolean unitNameVisible) {
    ( (FileSizeFormat) formatter).setUnitNameVisible(unitNameVisible);
  }

  /**
   * 是否显示单位
   * @return 显示时返回true，否则返回false
   * @since  0.5
   */
  public boolean isUnitNameVisible() {
    return ( (FileSizeFormat) formatter).isUnitNameVisible();
  }

}