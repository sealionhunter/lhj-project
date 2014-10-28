package org.jr.swing.text;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��12��
 * @author Cherami
 */

import java.awt.*;
import javax.swing.text.*;

/**
 * ֻ���������ֵ�Swing�ĵ�ģ�͡�
 * �������ÿ��Ա�������ֵ����ֵ����Сֵ��
 * @since  0.1
 */

public class NumberOnlyDocument
    extends PlainDocument {
  private boolean haveDot = false;
  private int length = 0;
  private double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
  private boolean isLimit = false;
  /**
   * ����һ��ȱʡ��NumberOnlyDocument��
   * @since  0.1
   */
  public NumberOnlyDocument() {

  }

  /**
   * ����һ���������ִ�С��NumberOnlyDocument��
   * ֻ����max��С��min�������max��min�����ò���Ч��
   * @param max ���ֵ
   * @param min ��Сֵ
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
   * ����һ���������ִ�С��NumberOnlyDocument��
   * ֻ����max��С��min�������max��min�����ò���Ч��
   * @param max ���ֵ
   * @param min ��Сֵ
   * @param isLimit �Ƿ��������ִ�С
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
   * �����Ƿ��������ֵĴ�С��
   * ���������trueʱֻ����ԭ�������ֵ������Сֵ��������ò���Ч������˷�������û���κ�Ч����
   * �������ֵ��false��ȡ���������ִ�С������
   * @param isLimit �Ƿ������������ֵĴ�С
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
   * ���ÿ��������ֵ�ķ�Χ��
   * ���maxС��min�������������ֵ����Сֵ��������Ч����Ϊû�д�С���Ƶ��������
   * @param max ������������ֵ
   * @param min �����������Сֵ
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
   * �Ƿ��������ֵĴ�С��
   * @return ���������Сʱ����true�����򷵻�false��
   * @since  0.3
   */
  public boolean isLimit() {
    return isLimit;
  }

  /**
   * �������Ƶ����ֵ��
   * @return ���Ƶ����ֵ������ǲ������򷵻�Double.MIN_VALUE��
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
   * �������Ƶ���Сֵ��
   * @return ���Ƶ���Сֵ������ǲ������򷵻�Double.MAX_VALUE��
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
   * ����ĳЩ���ݵ��ĵ��С�
   * ֻ�е�����������ֻ��ߺ�������صġ�.������-���ȷ��Ų��ҷ��Ϲ��ɺϷ����ֵĹ���ʱ�ű����롣
   * �˷������̰߳�ȫ�ġ�
   * @param offs ����λ�õ�ƫ����
   * @param str ��������
   * @param a ���Լ���
   * @throws BadLocationException �����Ĳ���λ�ò����ĵ��е���Чλ��
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