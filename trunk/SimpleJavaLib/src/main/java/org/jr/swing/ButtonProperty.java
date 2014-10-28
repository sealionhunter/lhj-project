package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月7日
 * @author Cherami
 */
import org.jr.*;

/**
 * 工具栏的图像按钮的相关属性的抽象类。
 * <p>包括按钮的显示文字，提示信息，图像文件的文件名
 * @since  0.4
 */
public class ButtonProperty
    extends CharSplitProperty {
  /**
   * 按钮的显示文字。
   * @since  0.4
   */
  public final String text;
  /**
   * 按钮的提示信息。
   * @since  0.4
   */
  public final String tooltip;
  /**
   * 图像文件的路径。
   * @since  0.4
   */
  public final String image;
  /**
   * 显示文字的属性索引。
   * @since  0.4
   */
  public static final int TEXT = 0;
  /**
   * 提示信息的属性索引。
   * @since  0.4
   */
  public static final int TOOLTIP = 1;
  /**
   * 图像路径的属性索引。
   * @since  0.4
   */
  public static final int IMAGE = 2;

  private static final int LEAST_PROPERTY = 1;
  private static final int PROPERTY_COUNT = 3;

  /**
   * 构造方法，根据原始信息解析得到需要的各个子信息，解析的分隔符为'*'。
   * @param source 未经解析的原始信息
   * @since  0.4
   */
  public ButtonProperty(String source) {
    this(source, '*');
  }

  /**
   * 构造方法，根据原始信息解析得到需要的各个子信息。
   * @param source 未经解析的原始信息
   * @param splitChar 解析的分隔符
   * @since  0.4
   */
  public ButtonProperty(String source, char splitChar) {
    super(source, splitChar);
    text = getProperty(TEXT);
    tooltip = getProperty(TOOLTIP);
    image = getProperty(IMAGE);
  }

  /**
   * 得到属性应有的最少个数。
   * @return 属性应有的最少个数，现在值为1。
   * @since  0.4
   */
  public int getLeastPropertyCount() {
    return LEAST_PROPERTY;
  }

  /**
   * 得到属性应有的个数。
   * 由于对于具体的的项目而言，其需要的属性个数是不同的，因此此方法被定义为抽象的。
   * @return 应有的属性个数
   * @since  0.4
   */
  public int getPropertyCount() {
    return PROPERTY_COUNT;
  }

  /**
   * 得到属性数组中的指定索引的属性不存在时的返回值。
   * @param index 属性数组中的索引
   * @return 属性数组中的指定索引的属性不存在时的返回值
   * @since  0.4
   */
  public String getDefaultProperty(int index) {
    switch (index) {
      case TEXT:
        return "Button " + getNumber();
      case TOOLTIP:
        return "press this button";
      case IMAGE:
        return "";
      default:
        return "";
    }
  }
}
