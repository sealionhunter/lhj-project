package org.jr.awt.image;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月13日
 * @author Cherami
 */

import java.awt.image.*;

/**
 * 灰度变换模型。
 * @since  0.1
 */

public class GrayModel
    extends ColorModel {
  /**
   * 最大值灰度模式。
   * 即取原RGB三色中的最大值作为灰度值。
   */
  public static final int CS_MAX = 0;
  /**
   * 浮点平均灰度模式。
   * 即取原RGB三色的比例和作为灰度值。
   * 计算公式为：R*0.3+G*0.59+B*0.11
   */
  public static final int CS_FLOAT = 1;
  /**
   * 绝对平均灰度模式。
   * 即取原RGB三色的和的平均值作为灰度值。
   * 计算公式为：(R+G+B)/3
   */
  public static final int CS_AVERAGE = 2;
  ColorModel sourceModel;
  int modelStyle;
  /**
   * 构造方法，根据原颜色变换模型构造灰度模型，使用最大值灰度模式。
   * @param sourceModel 原颜色变换模型
   */
  public GrayModel(ColorModel sourceModel) {
    super(sourceModel.getPixelSize());
    this.sourceModel = sourceModel;
    modelStyle = 0;
  }

  /**
   * 构造方法，根据原颜色变换模型和指定的灰度模式构造灰度模型。
   * @param sourceModel 原颜色变换模型
   * @param style 灰度模式
   */
  public GrayModel(ColorModel sourceModel, int style) {
    super(sourceModel.getPixelSize());
    this.sourceModel = sourceModel;
    modelStyle = style;
  }

  /**
   * 设置灰度模式。
   * @param style 灰度模式
   */
  public void setGrayStyle(int style) {
    modelStyle = style;
  }

  /**
   * 根据灰度模式得到灰度值
   * @param pixel 象素的原RGB值
   * @return 变换后的灰度值
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
   * @return 根据灰度模型变换后的灰度值
   */
  public int getRed(int pixel) {
    return getGrayLevel(pixel);
  }

  /**
   * 得到绿色分量值
   * @param pixel 象素的原RGB值
   * @return 根据灰度模型变换后的灰度值
   */
  public int getGreen(int pixel) {
    return getGrayLevel(pixel);
  }

  /**
   * 得到蓝色分量值
   * @param pixel 象素的原RGB值
   * @return 根据灰度模型变换后的灰度值
   */
  public int getBlue(int pixel) {
    return getGrayLevel(pixel);
  }

  /**
   * 得到变换后的RGB值
   * @param pixel 象素的原RGB值
   * @return 根据灰度模型变换后的RGB值
   */
  public int getRGB(int pixel) {
    int gray = getGrayLevel(pixel);
    return (getAlpha(pixel) << 24) + (gray << 16) + (gray << 8) + gray;
  }
}