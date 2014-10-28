package org.jr.awt.icon;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月16日
 * @author Cherami
 */

import java.awt.*;
import javax.swing.*;

/**
 * 空白图标。
 * @since  0.1
 */
public class BlankIcon
    implements Icon {
  static final int DEFAULT_SIZE = 16;
  private Color fillColor;
  private int size;
  /**
   * 构造一个缺省的BlankIcon。
   * @since  0.1
   */
  public BlankIcon() {
    this(null, DEFAULT_SIZE);
  }

  /**
   * 使用指定的填充色和大小构造一个BlankIcon。
   * @param color 填充色
   * @param size 图标大小
   * @since  0.1
   */
  public BlankIcon(Color color, int size) {

    if (color == null) {
      fillColor = Color.white;
    }
    else {
      fillColor = color;
    }
    if (size < 0) {
      this.size = DEFAULT_SIZE;
    }
    else {
      this.size = size;
    }
  }

  /**
   * 绘制图标
   * @param c 绘制组件
   * @param g 图形设备
   * @param x 绘制的x坐标的起始点
   * @param y 绘制的y坐标的起始点
   * @since  0.1
   */
  public void paintIcon(Component c, Graphics g, int x, int y) {

    g.setColor(fillColor);
    g.drawRect(x, y, size - 1, size - 1);
  }

  /**
   * 图标宽度。
   * @return 图标宽度
   * @since  0.1
   */
  public int getIconWidth() {
    return size;
  }

  /**
   * 图标高度。
   * @return 图标高度
   * @since  0.1
   */
  public int getIconHeight() {
    return size;
  }
}
