package org.jr.awt.icon;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月16日
 * @author Cherami
 */
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import org.jr.awt.image.*;

/**
 * 灰度变换图标。
 * @since  0.1
 */

public class GrayIcon
    implements Icon {
  Icon icon;
  Image image;
  /**
   * 根据原图标构造一个GrayIcon。
   * @param icon 原图标
   * @since  0.1
   */
  public GrayIcon(Icon icon) {
    this.icon = icon;
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
    if (image == null) {
      Image orgImage = c.createImage(getIconWidth(), getIconHeight());
      Graphics imageG = orgImage.getGraphics();
      Color background = c.getBackground();
      imageG.setColor(background);
      imageG.fillRect(0, 0, getIconWidth(), getIconHeight());

      icon.paintIcon(c, imageG, x, y);

      ImageFilter colorfilter = new org.jr.awt.image.GrayFilter(GrayModel.
          CS_AVERAGE);
      image = c.createImage(
          new FilteredImageSource(orgImage.getSource(), colorfilter));

    }
    g.drawImage(image, x, y, null);
  }

  /**
   * 图标宽度。
   * @return 图标宽度
   * @since  0.1
   */
  public int getIconWidth() {
    return icon.getIconWidth();
  }

  /**
   * 图标高度。
   * @return 图标高度
   * @since  0.1
   */
  public int getIconHeight() {
    return icon.getIconHeight();
  }

}