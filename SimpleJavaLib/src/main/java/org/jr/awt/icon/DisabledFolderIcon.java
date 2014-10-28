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
 * 被禁止的文件夹图标。
 * @since  0.1
 */

public class DisabledFolderIcon
    implements Icon {
  int width = 16;
  int height = 16;
  int additionalHeight = 2;
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
    int bottom = height - 2;

    g.setColor(c.getBackground());
    g.fillRect(0, 0, width, getIconHeight());

    // Draw tab
    g.setColor(MetalLookAndFeel.getControlDisabled());
    g.drawLine(right - 5, 2, right, 2);
    g.drawLine(right - 6, 3, right - 6, 4);
    g.drawLine(right, 3, right, 4);

    // Draw outline
    g.setColor(MetalLookAndFeel.getControlDisabled());
    g.drawLine(0, 5, 0, bottom); // left side
    g.drawLine(1, 4, right - 7, 4); // first part of top
    g.drawLine(right - 6, 5, right - 1, 5); // second part of top
    g.drawLine(right, 4, right, bottom); // right side
    g.drawLine(0, bottom, right, bottom); // bottom
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
