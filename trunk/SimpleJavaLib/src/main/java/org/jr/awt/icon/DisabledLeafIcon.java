package org.jr.awt.icon;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月16日
 * @author Cherami
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;

/**
 * 被禁止的叶子节点（文件）图标。
 * @since  0.1
 */
public class DisabledLeafIcon
    implements Icon {
  int width = 16;
  int height = 16;
  int additionalHeight = 4;
  /**
   * 绘制图标
   * @param c 绘制组件
   * @param g 图形设备
   * @param x 绘制的x坐标的起始点
   * @param y 绘制的y坐标的起始点
   * @since  0.1
   */

  public void paintIcon(Component c, Graphics g, int x, int y) {
    int right = width - 1;
    int bottom = height + 1;

    g.setColor(c.getBackground());
    g.fillRect(0, 0, width, getIconHeight());

    // Draw frame
    g.setColor(MetalLookAndFeel.getControlDisabled());
    g.drawLine(2, 2, 2, bottom); // left
    g.drawLine(2, 2, right - 4, 2); // top
    g.drawLine(2, bottom, right - 1, bottom); // bottom
    g.drawLine(right - 1, 8, right - 1, bottom); // right
    g.drawLine(right - 6, 4, right - 2, 8); // slant 1
    g.drawLine(right - 5, 3, right - 4, 3); // part of slant 2
    g.drawLine(right - 3, 4, right - 3, 5); // part of slant 2
    g.drawLine(right - 2, 6, right - 2, 7); // part of slant 2
  }

  /**
   * 图标宽度。
   * @return 图标宽度
   * @since  0.1
   */
  public int getIconWidth() {
    return width;
  }

  /**
   * 图标高度。
   * @return 图标高度
   * @since  0.1
   */
  public int getIconHeight() {
    return height + additionalHeight;
  }
}
