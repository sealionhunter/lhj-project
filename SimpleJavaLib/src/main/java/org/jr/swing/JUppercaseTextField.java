package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月12日
 * @author Cherami
 */

import javax.swing.*;
import javax.swing.text.*;

import org.jr.swing.text.*;

/**
 * 将输入的小写字母自动转换为大写的Swing组件。
 * @since  0.1
 */

public class JUppercaseTextField
    extends JTextField {
  /**
   * 缺省构造方法，构造一个新的JUppercaseTextField。
   * @since  0.3
   */
  public JUppercaseTextField() {
    super();
  }

  /**
   * 使用指定的字符列数构造具有最佳宽度的JUppercaseTextField。
   * @param columns 用于计算最佳宽度的列数
   * @since  0.1
   * @see javax.swing.JTextField#JTextField(int columns) javax.swing.JTextField.JTextField(int columns)
   */
  public JUppercaseTextField(int columns) {
    super(columns);
  }

  /**
   * 以指定的文本构造一个新的JUppercaseTextField。
   * @param text 初始显示文本
   * @since  0.3
   */
  public JUppercaseTextField(String text) {
    super(text);
  }

  /**
   * 以指定的文本和列数构造一个具有最佳宽度的新的JUppercaseTextField。
   * @param text 初始显示文本
   * @param columns 初始的列数
   * @since  0.3
   */
  public JUppercaseTextField(String text, int columns) {
    super(text, columns);
  }

  /**
   * 创建缺省的文档模型。
   * @return 自动将小写字母转换为大小字母的文档模型
   * @since  0.1
   */
  protected Document createDefaultModel() {
    return new UppercaseDocument();
  }

}
