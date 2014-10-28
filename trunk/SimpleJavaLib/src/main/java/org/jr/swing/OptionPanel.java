package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月13日
 * @author Cherami
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * 选项面板。
 * 简化了选项面板的创建，一般情况下只需要设置面板的标题和元素的布局即可。
 * 使用GridLayout布局管理器，推荐的用法是：
 * panel=new OptionPanel(title, rows, 1);
 * 即创建一个带有标题，选项内容垂直排列的面板。
 * @since  0.5
 */

public class OptionPanel
    extends JPanel {
  public static final Color defaultShadowColor = new Color(148, 145, 140);
  public static final Color defaultHighlightColor = Color.white;
  protected Color shadowColor;
  protected Color highlightColor;
  protected String title;
  protected int rows;
  protected int columns;
  /**
   * 根据指定的参数创建一个OptionPanel。
   * 边框颜色使用缺省的颜色。
   * @param title 标题文字
   * @param rows 行数
   * @param columns 列数
   * @since  0.5
   */
  public OptionPanel(String title, int rows, int columns) {
    this(title, rows, columns, defaultHighlightColor, defaultShadowColor);
  }

  /**
   * 根据指定的参数创建一个OptionPanel。
   * @param title 标题文字
   * @param rows 行数
   * @param columns 列数
   * @param highlightColor 高亮的颜色
   * @param shadowColor 阴暗的颜色
   * @since  0.5
   */
  public OptionPanel(String title, int rows, int columns, Color highlightColor,
                     Color shadowColor) {
    this.title = title;
    this.rows = rows;
    this.columns = columns;
    this.highlightColor = highlightColor;
    this.shadowColor = shadowColor;

    setLayout(new GridLayout(rows, columns));
    setBorder(new TitledBorder(BorderFactory.
                               createEtchedBorder(highlightColor,
                                                  shadowColor),
                               title));
  }

}