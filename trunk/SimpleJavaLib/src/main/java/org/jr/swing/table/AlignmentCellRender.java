package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月16日
 * @author Cherami
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * 可以一次性设置表格的各列的对齐方式的表格单元渲染器。
 * @since  0.1
 */

public class AlignmentCellRender
    extends DefaultTableCellRenderer {
  int columns[];
  int aligments[];
  int defaultAligment;
  /**
   * 构造一个缺省的AlignmentCellRender。
   * 缺省对齐方式为靠左对齐。
   * @since  0.1
   */
  public AlignmentCellRender() {
    defaultAligment = LEFT;
    columns = new int[0];
    aligments = new int[0];
  }

  /**
   * 根据指定的缺省对齐方式构造一个AlignmentCellRender。
   * @param defaultAligment 缺省的对齐方式
   * @since  0.3
   */
  public AlignmentCellRender(int defaultAligment) {
    this.defaultAligment = defaultAligment;
    columns = new int[0];
    aligments = new int[0];
  }

  /**
   * 根据指定行列的对齐方式的设置构造一个AlignmentCellRender。
   * 缺省对齐方式为靠左对齐。
   * @param columns 要设置对齐方式的列
   * @param aligments 对应的对齐方式
   * @since  0.1
   */
  public AlignmentCellRender(int columns[], int aligments[]) {
    this(LEFT, columns, aligments);
  }

  /**
   * 根据缺省对齐方式和指定行列的对齐方式的设置构造一个AlignmentCellRender。
   * @param defaultAligment 缺省对齐方式
   * @param columns  要设置对齐方式的列
   * @param aligments 对应的对齐方式
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
   * 对表格单元进行渲染。
   * @param table 要渲染的表格
   * @param value 表格单元的值
   * @param isSelected 是否选中
   * @param hasFocus 是否得到焦点
   * @param row 行数
   * @param column 列数
   * @return 经渲染后的表格单元
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
   * 设置缺省对齐方式。
   * @param aligment 缺省对齐方式
   * @since  0.1
   */
  public void setDefaultAligment(int aligment) {
    defaultAligment = aligment;
  }

  /**
   * 重新设置各列的对齐方式。
   * @param columns 要设置对齐方式的列
   * @param aligments 对应的对齐方式
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
   * 设置某一列的对齐方式。
   * 如果该列已经被设置过则用新的值替换原来的值。
   * @param column 要设置对齐方式的列
   * @param aligment 对应的对齐方式
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
   * 拷贝数组。
   * @param columnArray 列数组
   * @param aligmentArray 对齐方式数组
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