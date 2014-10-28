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
 * 只能输入数字的Swing组件。
 * 可以设置可以输入的数字的最大值和最小值。
 * @since  0.1
 */

public class JNumberTextField
    extends JTextField {
  private double max, min;
  private boolean isLimit = false;

  /**
   * 缺省构造方法，构造一个新的JNumberTextField。
   * @since  0.3
   */
  public JNumberTextField() {
    super();
  }

  /**
   * 根据指定的列数构造一个具有最佳宽度的新的JNumberTextField。
   * @param columns 初始的列数
   * @since  0.1
   */
  public JNumberTextField(int columns) {
    super(columns);
  }

  /**
   * 以指定的文本构造一个新的JNumberTextField。
   * @param text 初始显示文本
   * @since  0.3
   */
  public JNumberTextField(String text) {
    super(text);
  }

  /**
   * 以指定的文本和列数构造一个具有最佳宽度的新的JNumberTextField。
   * @param text 初始显示文本
   * @param columns 初始的列数
   * @since  0.3
   */
  public JNumberTextField(String text, int columns) {
    super(text, columns);
  }

  /**
   * 根据指定可以输入的最大值最小值构造一个具有最佳宽度的新的JNumberTextField。
   * 如果max小于min则可以输入的最大值和最小值的限制无效，作为没有大小限制的情况处理。
   * @param max 可以输入的最大值
   * @param min 可以输入的最小值
   * @since  0.3
   */
  public JNumberTextField(double max, double min) {
    if (max >= min) {
      this.max = max;
      this.min = min;
      isLimit = true;
    }
  }

  /**
   * 根据指定的文本和可以输入的最大值最小值构造一个具有最佳宽度的新的JNumberTextField。
   * 如果max小于min则可以输入的最大值和最小值的限制无效，作为没有大小限制的情况处理。
   * @param text 初始的文本
   * @param max 可以输入的最大值
   * @param min 可以输入的最小值
   * @since  0.3
   */
  public JNumberTextField(String text, double max, double min) {
    super(text);
    if (max >= min) {
      this.max = max;
      this.min = min;
      isLimit = true;
    }
  }

  /**
   * 根据指定的列数和可以输入的最大值最小值构造一个具有最佳宽度的新的JNumberTextField。
   * 如果max小于min则可以输入的最大值和最小值的限制无效，作为没有大小限制的情况处理。
   * @param columns 初始的列数
   * @param max 可以输入的最大值
   * @param min 可以输入的最小值
   * @since  0.1
   */
  public JNumberTextField(int columns, double max, double min) {
    super(columns);
    if (max >= min) {
      this.max = max;
      this.min = min;
      isLimit = true;
    }
  }

  /**
   * 根据指定的文本和初始列数以及可以输入的最大值最小值构造一个具有最佳宽度的新的JNumberTextField。
   * 如果max小于min则可以输入的最大值和最小值的限制无效，作为没有大小限制的情况处理。
   * @param text 初始的文本
   * @param columns 初始的列数
   * @param max 可以输入的最大值
   * @param min 可以输入的最小值
   * @since  0.3
   */
  public JNumberTextField(String text, int columns, double max, double min) {
    super(text, columns);
    if (max >= min) {
      this.max = max;
      this.min = min;
      isLimit = true;
    }
  }

  /**
   * 设置可以输入的值的范围。
   * 如果max小于min则可以输入的最大值和最小值的限制无效，作为没有大小限制的情况处理。
   * @param max 可以输入的最大值
   * @param min 可以输入的最小值
   * @since  0.1
   */
  public void setRange(double max, double min) {
    if (max >= min) {
      this.max = max;
      this.min = min;
      isLimit = true;
      ( (NumberOnlyDocument) getDocument()).setRange(max, min);
    }
  }

  /**
   * 设置是否限制输入数字的大小。
   * 如果参数是true时只有在原来的最大值大于最小值是这个设置才起效，否则此方法调用没有任何效果。
   * 如果参数值是false则取消输入数字大小的限制
   * @param isLimit 是否限制输入数字的大小
   * @since  0.3
   */
  public void setLimit(boolean isLimit) {
    if (isLimit == true && max >= min) {
      this.isLimit = isLimit;
      ( (NumberOnlyDocument) getDocument()).setRange(max, min);
    }
    else if (isLimit == false) {
      ( (NumberOnlyDocument) getDocument()).setLimit(isLimit);
    }
  }

  /**
   * 组件是否限制输入数字的大小。
   * @return 限制输入大小时返回true，否则返回false。
   * @since  0.3
   */
  public boolean isLimit() {
    return isLimit;
  }

  /**
   * 返回限制输入的最大值。
   * @return 限制输入的最大值。如果是不限制输入则返回Double.MIN_VALUE。
   * @since  0.3
   */
  public double getLimitedMax() {
    if (!isLimit) {
      return Double.MIN_VALUE;
    }
    else {
      return max;
    }
  }

  /**
   * 返回限制输入的最小值。
   * @return 限制输入的最小值。如果是不限制输入则返回Double.MAX_VALUE。
   * @since  0.3
   */
  public double getLimitedMin() {
    if (!isLimit) {
      return Double.MAX_VALUE;
    }
    else {
      return min;
    }
  }

  /**
   * 创建缺省的文档模型。
   * @return 只能输入数字相关的内容的文档模型
   * @since  0.1
   */
  protected Document createDefaultModel() {
    return new NumberOnlyDocument(max, min, isLimit);
  }

}
