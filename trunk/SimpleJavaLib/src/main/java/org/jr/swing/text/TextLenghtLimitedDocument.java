package org.jr.swing.text;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月12日
 * @author Cherami
 */

import javax.swing.text.*;

/**
 * 输入内容长度受限并且可能自动将小写输入转换为大写的Swing文档模型。
 * <p><b>注意：</b>本方法和0.12版相比功能发生了变化。
 * 现在可以控制暂时取消将小写转换为大写的功能。
 * @since  0.1
 */

public class TextLenghtLimitedDocument
    extends PlainDocument {
  /**
   * 可以输入的最大长度。
   */
  protected int limitLength;
  /**
   * 是否将小写转换为大写。
   */
  protected boolean toUppercase = false;
  /**
   * 根据指定的最大长度构造一个TextLenghtLimitedDocument。
   * @param limitLength 可以输入的最大长度
   * @since  0.1
   */
  public TextLenghtLimitedDocument(int limitLength) {
    super();
    this.limitLength = limitLength;
  }

  /**
   * 根据指定的最大长度和是否进行小写自动转换标志构造一个TextLenghtLimitedDocument。
   * @param limitLength 可以输入的最大长度
   * @param upper 是否自动将小写输入转换为大写，true的时候转换，否则不转换。
   * @since  0.1
   */
  public TextLenghtLimitedDocument(int limitLength, boolean toUppercase) {
    super();
    this.limitLength = limitLength;
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
   * 只有当输入的内容的长度没有超过最大长度时才可以插入，超过的部分被忽略。
   * 设置了自动将小写转换为大写时也会进行转换。
   * 此方法是线程安全的。
   * <p><b>注意：</b>本方法和0.12版相比功能发生了变化。
   * 原来是如果要插入的总的内容的长度加上已有内容的长度超过限制是不插入任何新内容，
   * 现在修改为尽最大可能插入多的内容，多出的内容被忽略。
   * @param offs 插入位置的偏移量
   * @param str 插入内容
   * @param a 属性集合
   * @throws BadLocationException 给出的插入位置不是文档中的有效位置
   * @since  0.1
   */
  public void insertString
      (int offset, String str, AttributeSet attr) throws BadLocationException {
    if (str == null) {
      return;
    }

    if ( (getLength() + str.length()) > limitLength) {
      str = str.substring(0, limitLength - getLength());
    }
    if (toUppercase) {
      str = str.toUpperCase();
    }
    super.insertString(offset, str, attr);
  }
}
