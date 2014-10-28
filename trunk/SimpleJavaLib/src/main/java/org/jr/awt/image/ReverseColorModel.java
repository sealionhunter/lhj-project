package org.jr.awt.image;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��13��
 * @author Cherami
 */

import java.awt.image.*;

/**
 * ��ɫ��ת�任ģ�͡�
 * @since  0.1
 */
public class ReverseColorModel
    extends ColorModel {
  ColorModel sourceModel;
  /**
   * ���췽��������ԭ��ɫ�任ģ�͹�����ɫ��ת�任ģ�͡�
   * @param sourceModel ԭ��ɫ�任ģ��
   */
  public ReverseColorModel(ColorModel sourceModel) {
    super(sourceModel.getPixelSize());
    this.sourceModel = sourceModel;
  }

  /**
   * �õ�Alphaֵ
   * @param pixel ���ص�ԭRGBֵ
   * @return ����ԭɫ��ģ�͵�Alphaֵ
   */
  public int getAlpha(int pixel) {
    return sourceModel.getAlpha(pixel);
  }

  /**
   * �õ���ɫ����ֵ
   * @param pixel ���ص�ԭRGBֵ
   * @return ����ԭɫ��ģ�͵ĺ�ɫ����ֵ��ȡ��
   */
  public int getRed(int pixel) {
    return~sourceModel.getRed(pixel);
  }

  /**
   * �õ���ɫ����ֵ
   * @param pixel ���ص�ԭRGBֵ
   * @return ����ԭɫ��ģ�͵���ɫ����ֵ��ȡ��
   */
  public int getGreen(int pixel) {
    return~sourceModel.getGreen(pixel);
  }

  /**
   * �õ���ɫ����ֵ
   * @param pixel ���ص�ԭRGBֵ
   * @return ����ԭɫ��ģ�͵���ɫ����ֵ��ȡ��
   */
  public int getBlue(int pixel) {
    return~sourceModel.getBlue(pixel);
  }

  /**
   * �õ��任���RGBֵ
   * @param pixel ���ص�ԭRGBֵ
   * @return ����ԭɫ��ģ�͵�RGBֵ��RGB����ֵȡ��
   */
  public int getRGB(int pixel) {
    return (getAlpha(pixel) << 24) + (getRed(pixel) << 16) +
        (getGreen(pixel) << 8) + getBlue(pixel);
  }
}