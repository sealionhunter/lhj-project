package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月28日
 * @author Cherami
 */
import org.jr.*;

/**
 * 菜单项属性的抽象。
 * <p>包括菜单的显示文字、提示信息、图像文件的文件名、快捷键以及加速建。
 * 快捷键可以使用整型值也可以使用字符，而加速建的设置需要使用KeyStroke中的说明。
 * @see "Java API中的public static KeyStroke getKeyStroke(String s)的说明"
 * @since  0.4
 */

public class MenuItemProperty
    extends CharSplitProperty {
  /**
   * 菜单项的显示文字。
   * @since  0.4
   */
  public final String text;
  /**
   * 菜单项的提示信息。
   * @since  0.4
   */
  public final String tooltip;
  /**
   * 菜单项图像的路径。
   * <b>请注意从0.5beta版开始这个属性要求是一个合法的URL路径，这样便于从jar包中提取图像。</b>
   * @since  0.4
   */
  public final String image;
  /**
   * 菜单项的快捷键。
   * @since  0.4
   */
  public final String mnemonic;
  /**
   * 菜单项的加速键。
   * @since  0.4
   */
  public final String accelerator;
  /**
   * 菜单项的选中状态。
   * @since  0.4
   */
  public final String selected;
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
  /**
   * 快捷键的属性索引。
   * @since  0.4
   */
  public static final int MNEMONIC = 3;
  /**
   * 加速键的属性索引。
   * @since  0.4
   */
  public static final int ACCELERATOR = 4;
  /**
   * 选中状态的属性索引。
   * @since  0.4
   */
  public static final int SELECTED = 5;

  private static final int LEAST_PROPERTY = 1;
  private static final int PROPERTY_COUNT = 5;

  /**
   * 构造方法，根据原始信息解析得到需要的各个子信息，解析的分隔符为'*'。
   * 假设使用'V'作为快捷键而使用CTRL+B作为加速建，那么source的内容应该为：
   * "菜单项*菜单项说明*images/menuitem.jpg*V*control b"
   * @param source 未经解析的原始信息
   * @since  0.4
   */
  public MenuItemProperty(String source) {
    this(source, '*');
  }

  /**
   * 构造方法，根据原始信息解析得到需要的各个子信息。
   * 参考另一个构造方法中的说明构造source。
   * @param source 未经解析的原始信息
   * @param splitChar 解析的分隔符
   * @since  0.4
   */
  public MenuItemProperty(String source, char splitChar) {
    super(source, splitChar);
    text = getProperty(TEXT);
    tooltip = getProperty(TOOLTIP);
    image = getProperty(IMAGE);
    mnemonic = getProperty(MNEMONIC);
    accelerator = getProperty(ACCELERATOR);
    selected = getProperty(SELECTED);
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
        return "Menu item " + getNumber();
      case TOOLTIP:
        return "press this menu item";
      case IMAGE:
        return "";
      case MNEMONIC:
        return "";
      case ACCELERATOR:
        return "";
      case SELECTED:
        return "false";
      default:
        return "";
    }
  }
}
