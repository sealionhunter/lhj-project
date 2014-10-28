package org.jr.awt.image;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月13日
 * @author Cherami
 */

import java.awt.image.*;

/**
 * 灰度变换过滤器。
 * @since  0.1
 */

public class GrayFilter
    extends RGBImageFilter {
  int modelStyle;
  /**
   * 缺省构造方法，使用GrayModel.CS_MAX模式构造灰度变换过滤器。
   * @since  0.1
   * @see GrayModel#CS_MAX GrayModel.CS_MAX
   */
  public GrayFilter() {
    modelStyle = GrayModel.CS_MAX;
    canFilterIndexColorModel = true;
  }

  /**
   * 使用指定的模式构造灰度变换过滤器。
   * @param style 灰度模式
   * @since  0.1
   */
  public GrayFilter(int style) {
    modelStyle = style;
    canFilterIndexColorModel = true;
  }

  /**
   * 设置色彩模型。
   * @param colorModel 色彩模型
   * @since  0.1
   */
  public void setColorModel(ColorModel colorModel) {
    substituteColorModel(colorModel, new GrayModel(colorModel, modelStyle));
  }

  /**
   * 返回经过灰度变换后的色彩的RGB值。
   * @param x X坐标值
   * @param y Y坐标值
   * @param pixel 原来的色彩的RGB值
   * @return 经过灰度变换后的色彩的RGB值
   * @since  0.1
   */
  public int filterRGB(int x, int y, int pixel) {
    return pixel;
  }
}