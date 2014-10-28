package org.jr.swing.text;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月12日
 * @author Cherami
 */

import java.awt.*;
import javax.swing.text.*;

/**
 * 只能输入数字的Swing文档模型。
 * 可以设置可以保存的数字的最大值和最小值。
 * @since  0.1
 */

public class NumberOnlyDocument
    extends PlainDocument {
  private boolean haveDot = false;
  private int length = 0;
  private double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
  private boolean isLimit = false;
  /**
   * 构造一个缺省的NumberOnlyDocument。
   * @since  0.1
   */
  public NumberOnlyDocument() {

  }

  /**
   * 构造一个限制数字大小的NumberOnlyDocument。
   * 只有在max不小于min的情况下max和min的设置才生效。
   * @param max 最大值
   * @param min 最小值
   * @since  0.3
   */
  public NumberOnlyDocument(double max, double min) {
    if (max >= min) {
      this.max = max;
      this.min = min;
    }
    this.isLimit = true;
  }

  /**
   * 构造一个限制数字大小的NumberOnlyDocument。
   * 只有在max不小于min的情况下max和min的设置才生效。
   * @param max 最大值
   * @param min 最小值
   * @param isLimit 是否限制数字大小
   * @since  0.3
   */
  public NumberOnlyDocument(double max, double min, boolean isLimit) {
    if (max >= min) {
      this.max = max;
      this.min = min;
    }
    this.isLimit = isLimit;
  }

  /**
   * 设置是否限制数字的大小。
   * 如果参数是true时只有在原来的最大值大于最小值是这个设置才起效，否则此方法调用没有任何效果。
   * 如果参数值是false则取消输入数字大小的限制
   * @param isLimit 是否限制输入数字的大小
   * @since  0.3
   */
  public void setLimit(boolean isLimit) {
    if (isLimit == true && max >= min) {
      this.isLimit = isLimit;
    }
    else if (isLimit == false) {
      this.isLimit = isLimit;
    }
  }

  /**
   * 设置可以输入的值的范围。
   * 如果max小于min则可以输入的最大值和最小值的限制无效，作为没有大小限制的情况处理。
   * @param max 可以输入的最大值
   * @param min 可以输入的最小值
   * @since  0.3
   */
  public void setRange(double max, double min) {
    if (max >= min) {
      this.max = max;
      this.min = min;
      isLimit = true;
    }
  }

  /**
   * 是否限制数字的大小。
   * @return 限制输入大小时返回true，否则返回false。
   * @since  0.3
   */
  public boolean isLimit() {
    return isLimit;
  }

  /**
   * 返回限制的最大值。
   * @return 限制的最大值。如果是不限制则返回Double.MIN_VALUE。
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
   * 返回限制的最小值。
   * @return 限制的最小值。如果是不限制则返回Double.MAX_VALUE。
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
   * 插入某些内容到文档中。
   * 只有当输入的是数字或者和数字相关的“.”、“-”等符号并且符合构成合法数字的规则时才被插入。
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
    char[] number = str.toCharArray();
    for (int i = 0; i < number.length; i++) {
      if (offs == 0) {
        if (! (number[i] >= '0' && number[i] <= '9' || number[i] == '.' ||
               number[i] == '-' || number[i] == '+')) {
          if (offs == length - 1) {
            remove(offs + i, 1);
          }
          else {
            return;
          }

        }
        else {
          length++;
        }
      }
      else {
        if (!haveDot) {
          if (! (number[i] >= '0' && number[i] <= '9' || number[i] == '.')) {
            if (offs == length - 1) {
              remove(offs + i, 1);
            }
            else {
              return;
            }
          }
          else {
            if (number[i] == '.') {
              haveDot = true;
            }
            length++;
          }
        }
        else {
          if (! (number[i] >= '0' && number[i] <= '9')) {
            if (offs == length - 1) {
              remove(offs + i, 1);
            }
            else {
              Toolkit.getDefaultToolkit().beep();
              return;
            }
          }
          else {
            length++;
          }
        }
      }
    }
    if (isLimit == true) {
      String text = getText(0, offs) + str + getText(offs, getLength());
      double result = Double.parseDouble(text);
      if (result > max || result < min) {
        return;
      }
    }
    super.insertString(offs, new String(number), a);
  }
}