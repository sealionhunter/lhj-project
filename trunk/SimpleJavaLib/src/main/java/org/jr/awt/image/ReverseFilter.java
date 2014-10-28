package org.jr.awt.image;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��13��
 * @author Cherami
 */

import java.awt.image.*;

/**
 * ��ɫ��ת�任��������
 * @since  0.1
 */

public class ReverseFilter
    extends RGBImageFilter {
  /**
   * ȱʡ���췽����������ɫ��ת�任��������
   */
  public ReverseFilter() {
    canFilterIndexColorModel = true;
  }

  /**
   * ����ɫ��ģ�͡�
   * @param colorModel ɫ��ģ��
   * @since  0.1
   */
  public void setColorModel(ColorModel colorModel) {
    substituteColorModel(colorModel, new ReverseColorModel(colorModel));
  }

  /**
   * ���ؾ�����ɫ��ת�任���ɫ�ʵ�RGBֵ��
   * @param x X����ֵ
   * @param y Y����ֵ
   * @param pixel ԭ����ɫ�ʵ�RGBֵ
   * @return ������ɫ��ת�任���ɫ�ʵ�RGBֵ
   * @since  0.1
   */
  public int filterRGB(int x, int y, int pixel) {
    return pixel;
  }
}