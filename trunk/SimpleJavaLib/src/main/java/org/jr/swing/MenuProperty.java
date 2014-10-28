package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月7日
 * @author Cherami
 */
import org.jr.*;

/**
 * 主菜单属性的抽象。
 * <p>目前只包括菜单的显示文字
 * @since  0.4
 */

public class MenuProperty
    extends CharSplitProperty {
  /**
   * 菜单的显示文字。
   * @since  0.4
   */
  public final String text;
  /**
   * 菜单的加速键。
   * @since  0.4
   */
  public final String mnemonic;
  /**
   * 显示文字的属性索引。
   * @since  0.4
   */
  public static final int TEXT = 0;
  /**
   * 快捷键的属性索引。
   * @since  0.4
   */
  public static final int MNEMONIC = 1;

  private static final int LEAST_PROPERTY = 1;
  private static final int PROPERTY_COUNT = 2;

  /**
   * 构造方法，根据原始信息解析得到需要的各个子信息，解析的分隔符为'*'。
   * @param source 未经解析的原始信息
   * @since  0.4
   */
  public MenuProperty(String source) {
    this(source, '*');
  }

  /**
   * 构造方法，根据原始信息解析得到需要的各个子信息。
   * @param source 未经解析的原始信息
   * @param splitChar 解析的分隔符
   * @since  0.4
   */
  public MenuProperty(String source, char splitChar) {
    super(source, splitChar);
    text = getProperty(TEXT);
    mnemonic = getProperty(MNEMONIC);
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
        return "Menu " + getNumber();
      case MNEMONIC:
        return "";
      default:
        return "";
    }
  }
}
