package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月4日
 * @author Cherami
 */
/**
 * 表格的一行数据的接口。
 * @since  0.5
 */

public interface TableLineData {
  /**
   * 得到表格的一行数据所包含的数据的列数。
   * 另外建议实现此接口的类定义一个静态的Class getColumnClass(int column)方法方便表格模型得到对应的数据类型。
   * @return 一行数据所包含的数据的列数
   */
  public int getCount();

  /**
   * 得到指定列的值。
   * @param column 列
   * @return 指定列的值
   */
  public Object get(int column);

  /**
   * 设置指定列的值。
   * @param column 列
   * @param value 值
   */
  public void set(int column, Object value);

}