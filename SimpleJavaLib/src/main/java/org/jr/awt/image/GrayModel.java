package org.jr.awt.image;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��13��
 * @author Cherami
 */

import java.awt.image.*;

/**
 * �Ҷȱ任ģ�͡�
 * @since  0.1
 */

public class GrayModel
    extends ColorModel {
  /**
   * ���ֵ�Ҷ�ģʽ��
   * ��ȡԭRGB��ɫ�е����ֵ��Ϊ�Ҷ�ֵ��
   */
  public static final int CS_MAX = 0;
  /**
   * ����ƽ���Ҷ�ģʽ��
   * ��ȡԭRGB��ɫ�ı�������Ϊ�Ҷ�ֵ��
   * ���㹫ʽΪ��R*0.3+G*0.59+B*0.11
   */
  public static final int CS_FLOAT = 1;
  /**
   * ����ƽ���Ҷ�ģʽ��
   * ��ȡԭRGB��ɫ�ĺ͵�ƽ��ֵ��Ϊ�Ҷ�ֵ��
   * ���㹫ʽΪ��(R+G+B)/3
   */
  public static final int CS_AVERAGE = 2;
  ColorModel sourceModel;
  int modelStyle;
  /**
   * ���췽��������ԭ��ɫ�任ģ�͹���Ҷ�ģ�ͣ�ʹ�����ֵ�Ҷ�ģʽ��
   * @param sourceModel ԭ��ɫ�任ģ��
   */
  public GrayModel(ColorModel sourceModel) {
    super(sourceModel.getPixelSize());
    this.sourceModel = sourceModel;
    modelStyle = 0;
  }

  /**
   * ���췽��������ԭ��ɫ�任ģ�ͺ�ָ���ĻҶ�ģʽ����Ҷ�ģ�͡�
   * @param sourceModel ԭ��ɫ�任ģ��
   * @param style �Ҷ�ģʽ
   */
  public GrayModel(ColorModel sourceModel, int style) {
    super(sourceModel.getPixelSize());
    this.sourceModel = sourceModel;
    modelStyle = style;
  }

  /**
   * ���ûҶ�ģʽ��
   * @param style �Ҷ�ģʽ
   */
  public void setGrayStyle(int style) {
    modelStyle = style;
  }

  /**
   * ���ݻҶ�ģʽ�õ��Ҷ�ֵ
   * @param pixel ���ص�ԭRGBֵ
   * @return �任��ĻҶ�ֵ
   */
  protected int getGrayLevel(int pixel) {
    if (modelStyle == CS_MAX) {
      return Math.max(sourceModel.getRed(pixel),
                      Math.max(sourceModel.getGreen(pixel),
                               sourceModel.getBlue(pixel)));
    }
    else if (modelStyle == CS_FLOAT) {
      return (int) (sourceModel.getRed(pixel) * 0.3 +
                    sourceModel.getGreen(pixel) * 0.59 +
                    sourceModel.getBlue(pixel) * 0.11);
    }
    else if (modelStyle == CS_AVERAGE) {
      return (int) ( (sourceModel.getRed(pixel) + sourceModel.getGreen(pixel) +
                      sourceModel.getBlue(pixel)) / 3);
    }
    else {
      return Math.max(sourceModel.getRed(pixel),
                      Math.max(sourceModel.getGreen(pixel),
                               sourceModel.getBlue(pixel)));
    }
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
   * @return ���ݻҶ�ģ�ͱ任��ĻҶ�ֵ
   */
  public int getRed(int pixel) {
    return getGrayLevel(pixel);
  }

  /**
   * �õ���ɫ����ֵ
   * @param pixel ���ص�ԭRGBֵ
   * @return ���ݻҶ�ģ�ͱ任��ĻҶ�ֵ
   */
  public int getGreen(int pixel) {
    return getGrayLevel(pixel);
  }

  /**
   * �õ���ɫ����ֵ
   * @param pixel ���ص�ԭRGBֵ
   * @return ���ݻҶ�ģ�ͱ任��ĻҶ�ֵ
   */
  public int getBlue(int pixel) {
    return getGrayLevel(pixel);
  }

  /**
   * �õ��任���RGBֵ
   * @param pixel ���ص�ԭRGBֵ
   * @return ���ݻҶ�ģ�ͱ任���RGBֵ
   */
  public int getRGB(int pixel) {
    int gray = getGrayLevel(pixel);
    return (getAlpha(pixel) << 24) + (gray << 16) + (gray << 8) + gray;
  }
}