package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��13��
 * @author Cherami
 */

import java.awt.*;
import javax.swing.*;

/**
 * ��ֱ��ʾ�ı���Swing�����
 * @since  0.1
 */
public class VerticalLabel
    extends JLabel {
  String text = "";
  int width = 0;
  int height = 0;
  int charHeight = 0;
  /**
   * ����һ��ȱʡ��VerticalLabel��
   * @since  0.1
   */
  public VerticalLabel() {
  }

  /**
   * �����ı�����һ��VerticalLabel��
   * @since  0.1
   */
  public VerticalLabel(String text) {
    this.text = text;
  }

  /**
   * ���������
   * @param g ͼ���豸����
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
   * �õ��������Ѵ�С��
   * ��Ѵ�С�Ŀ����ָ����ʹ�õ�������Ҫ��ʾ���ı��������ַ��Ŀ�ȼ���10�����ؿ�ȡ�
   * ��Ѵ�С�ĸ߶���ָ����ʹ�õ�������Ҫ��ʾ��ÿ���ַ��ĸ߶ȼ���5�����ظ߶ȵĺ��ټ���10�����ظ߶ȡ�
   * @return �������Ѵ�С��
   * @since  0.1
   */
  public Dimension getPreferredSize() {
    getBound();
    return new Dimension(width, height);
  }

  /**
   * �õ��������С��С��
   * @return ��getPreferredSize������ͬ
   * @since  0.1
   */
  public Dimension getMinimumSize() {
    getBound();
    return new Dimension(width, height);
  }

  /**
   * �����ı����ݵõ���ѵĸ߶ȺͿ�ȡ�
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