package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月13日
 * @author Cherami
 */

import java.awt.*;
import javax.swing.*;

/**
 * 垂直显示文本的Swing组件。
 * @since  0.1
 */
public class VerticalLabel
    extends JLabel {
  String text = "";
  int width = 0;
  int height = 0;
  int charHeight = 0;
  /**
   * 构造一个缺省的VerticalLabel。
   * @since  0.1
   */
  public VerticalLabel() {
  }

  /**
   * 根据文本构造一个VerticalLabel。
   * @since  0.1
   */
  public VerticalLabel(String text) {
    this.text = text;
  }

  /**
   * 绘制组件。
   * @param g 图形设备对象
   * @since  0.1
   */
  protected void paintComponent(Graphics g) {
    int count = text.length();
    int corp_x = 5;
    int corp_y = 15;
    char chars[] = text.toCharArray();
    for (int i = 0; i < count; i++) {
      g.drawString(text.substring(i, i + 1), corp_x, corp_y);
      corp_y += (charHeight);
    }
    width += 5;
  }

  /**
   * 得到组件的最佳大小。
   * 最佳大小的宽度是指在所使用的字体下要显示的文本中最宽的字符的宽度加上10个象素宽度。
   * 最佳大小的高度是指在所使用的字体下要显示的每个字符的高度加上5个象素高度的和再加上10个象素高度。
   * @return 组件的最佳大小。
   * @since  0.1
   */
  public Dimension getPreferredSize() {
    getBound();
    return new Dimension(width, height);
  }

  /**
   * 得到组件的最小大小。
   * @return 和getPreferredSize方法相同
   * @since  0.1
   */
  public Dimension getMinimumSize() {
    getBound();
    return new Dimension(width, height);
  }

  /**
   * 根据文本内容得到最佳的高度和宽度。
   */
  private void getBound() {
    if (width > 0) {
      return;
    }
    Graphics g = this.getGraphics();
    FontMetrics fm = g.getFontMetrics();
    charHeight = fm.getHeight();
    int count = text.length();
    height = (charHeight + 5) * count + 10;
    char chars[] = text.toCharArray();
    for (int i = 0; i < count; i++) {
      int charWidth = fm.charWidth(chars[i]);
      if (width < charWidth) {
        width = charWidth;
      }
    }
    width += 10;
  }

}