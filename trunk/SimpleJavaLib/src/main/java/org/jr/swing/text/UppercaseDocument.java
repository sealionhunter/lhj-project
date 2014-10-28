package org.jr.swing.text;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月12日
 * @author Cherami
 */

import javax.swing.text.*;

/**
 * 自动将小写转换为大写的Swing文档模型。
 * <p><b>注意：</b>本方法和0.12版相比功能发生了变化。
 * 现在可以控制暂时取消将小写转换为大写的功能。
 * @since  0.1
 */

public class UppercaseDocument
    extends PlainDocument {
  /**
   * 是否将小写转换为大写。
   */
  protected boolean toUppercase = true;
  /**
   * 构造一个将小写转换为大写的UppercaseDocument。
   * @since  0.1
   */
  public UppercaseDocument() {
    super();
  }

  /**
   * 根据指定的转换状态构造一个UppercaseDocument。
   * @param toUppercase 是否将小写转换为大写，true的时候进行转换，否则不转换。
   * @since  0.3
   */
  public UppercaseDocument(boolean toUppercase) {
    this.toUppercase = toUppercase;
  }

  /**
   * 设置是否将小写转换为大写。
   * @param toUppercase 是否将小写转换为大写，true的时候进行转换，否则不转换。
   * @since  0.3
   */
  public void setToUppercase(boolean toUppercase) {
    this.toUppercase = toUppercase;
  }

  /**
   * 返回是否将小写转换为大写。
   * @return 将小写转换为大写时返回true，否则返回false。
   * @since  0.3
   */
  public boolean isToUppercase() {
    return toUppercase;
  }

  /**
   * 插入某些内容到文档中。
   * 自动将小写输入转换为大写输入。
   * 此方法是线程安全的。
   * @param offs 插入位置的偏移量
   * @param str 插入内容
   * @param a 属性集合
   * @throws BadLocationException 给出的插入位置不是文档中的有效位置
   * @since  0.1
   */
  public void insertString(int offs, String str, AttributeSet a) throws
      BadLocationException {

    if (str == null) {
      return;
    }
    if (toUppercase) {
      str = str.toUpperCase();
    }
    super.insertString(offs, str, a);
  }
}