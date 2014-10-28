package org.jr.awt.image;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月13日
 * @author Cherami
 */

import java.awt.image.*;

/**
 * 颜色反转变换模型。
 * @since  0.1
 */
public class ReverseColorModel
    extends ColorModel {
  ColorModel sourceModel;
  /**
   * 构造方法，根据原颜色变换模型构造颜色反转变换模型。
   * @param sourceModel 原颜色变换模型
   */
  public ReverseColorModel(ColorModel sourceModel) {
    super(sourceModel.getPixelSize());
    this.sourceModel = sourceModel;
  }

  /**
   * 得到Alpha值
   * @param pixel 象素的原RGB值
   * @return 返回原色彩模型的Alpha值
   */
  public int getAlpha(int pixel) {
    return sourceModel.getAlpha(pixel);
  }

  /**
   * 得到红色分量值
   * @param pixel 象素的原RGB值
   * @return 返回原色彩模型的红色分量值的取反
   */
  public int getRed(int pixel) {
    return~sourceModel.getRed(pixel);
  }

  /**
   * 得到绿色分量值
   * @param pixel 象素的原RGB值
   * @return 返回原色彩模型的绿色分量值的取反
   */
  public int getGreen(int pixel) {
    return~sourceModel.getGreen(pixel);
  }

  /**
   * 得到蓝色分量值
   * @param pixel 象素的原RGB值
   * @return 返回原色彩模型的蓝色分量值的取反
   */
  public int getBlue(int pixel) {
    return~sourceModel.getBlue(pixel);
  }

  /**
   * 得到变换后的RGB值
   * @param pixel 象素的原RGB值
   * @return 返回原色彩模型的RGB值的RGB分量值取反
   */
  public int getRGB(int pixel) {
    return (getAlpha(pixel) << 24) + (getRed(pixel) << 16) +
        (getGreen(pixel) << 8) + getBlue(pixel);
  }
}