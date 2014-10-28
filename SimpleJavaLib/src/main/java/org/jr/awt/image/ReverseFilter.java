package org.jr.awt.image;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月13日
 * @author Cherami
 */

import java.awt.image.*;

/**
 * 颜色反转变换过滤器。
 * @since  0.1
 */

public class ReverseFilter
    extends RGBImageFilter {
  /**
   * 缺省构造方法，构造颜色反转变换过滤器。
   */
  public ReverseFilter() {
    canFilterIndexColorModel = true;
  }

  /**
   * 设置色彩模型。
   * @param colorModel 色彩模型
   * @since  0.1
   */
  public void setColorModel(ColorModel colorModel) {
    substituteColorModel(colorModel, new ReverseColorModel(colorModel));
  }

  /**
   * 返回经过颜色反转变换后的色彩的RGB值。
   * @param x X坐标值
   * @param y Y坐标值
   * @param pixel 原来的色彩的RGB值
   * @return 经过颜色反转变换后的色彩的RGB值
   * @since  0.1
   */
  public int filterRGB(int x, int y, int pixel) {
    return pixel;
  }
}