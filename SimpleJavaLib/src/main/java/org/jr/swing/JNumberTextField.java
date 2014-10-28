package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��12��
 * @author Cherami
 */

import javax.swing.*;
import javax.swing.text.*;

import org.jr.swing.text.*;

/**
 * ֻ���������ֵ�Swing�����
 * �������ÿ�����������ֵ����ֵ����Сֵ��
 * @since  0.1
 */

public class JNumberTextField
    extends JTextField {
  private double max, min;
  private boolean isLimit = false;

  /**
   * ȱʡ���췽��������һ���µ�JNumberTextField��
   * @since  0.3
   */
  public JNumberTextField() {
    super();
  }

  /**
   * ����ָ������������һ��������ѿ�ȵ��µ�JNumberTextField��
   * @param columns ��ʼ������
   * @since  0.1
   */
  public JNumberTextField(int columns) {
    super(columns);
  }

  /**
   * ��ָ�����ı�����һ���µ�JNumberTextField��
   * @param text ��ʼ��ʾ�ı�
   * @since  0.3
   */
  public JNumberTextField(String text) {
    super(text);
  }

  /**
   * ��ָ�����ı�����������һ��������ѿ�ȵ��µ�JNumberTextField��
   * @param text ��ʼ��ʾ�ı�
   * @param columns ��ʼ������
   * @since  0.3
   */
  public JNumberTextField(String text, int columns) {
    super(text, columns);
  }

  /**
   * ����ָ��������������ֵ��Сֵ����һ��������ѿ�ȵ��µ�JNumberTextField��
   * ���maxС��min�������������ֵ����Сֵ��������Ч����Ϊû�д�С���Ƶ��������
   * @param max ������������ֵ
   * @param min �����������Сֵ
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
   * ����ָ�����ı��Ϳ�����������ֵ��Сֵ����һ��������ѿ�ȵ��µ�JNumberTextField��
   * ���maxС��min�������������ֵ����Сֵ��������Ч����Ϊû�д�С���Ƶ��������
   * @param text ��ʼ���ı�
   * @param max ������������ֵ
   * @param min �����������Сֵ
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
   * ����ָ���������Ϳ�����������ֵ��Сֵ����һ��������ѿ�ȵ��µ�JNumberTextField��
   * ���maxС��min�������������ֵ����Сֵ��������Ч����Ϊû�д�С���Ƶ��������
   * @param columns ��ʼ������
   * @param max ������������ֵ
   * @param min �����������Сֵ
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
   * ����ָ�����ı��ͳ�ʼ�����Լ�������������ֵ��Сֵ����һ��������ѿ�ȵ��µ�JNumberTextField��
   * ���maxС��min�������������ֵ����Сֵ��������Ч����Ϊû�д�С���Ƶ��������
   * @param text ��ʼ���ı�
   * @param columns ��ʼ������
   * @param max ������������ֵ
   * @param min �����������Сֵ
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
   * ���ÿ��������ֵ�ķ�Χ��
   * ���maxС��min�������������ֵ����Сֵ��������Ч����Ϊû�д�С���Ƶ��������
   * @param max ������������ֵ
   * @param min �����������Сֵ
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
   * �����Ƿ������������ֵĴ�С��
   * ���������trueʱֻ����ԭ�������ֵ������Сֵ��������ò���Ч������˷�������û���κ�Ч����
   * �������ֵ��false��ȡ���������ִ�С������
   * @param isLimit �Ƿ������������ֵĴ�С
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
   * ����Ƿ������������ֵĴ�С��
   * @return ���������Сʱ����true�����򷵻�false��
   * @since  0.3
   */
  public boolean isLimit() {
    return isLimit;
  }

  /**
   * ����������������ֵ��
   * @return ������������ֵ������ǲ����������򷵻�Double.MIN_VALUE��
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
   * ���������������Сֵ��
   * @return �����������Сֵ������ǲ����������򷵻�Double.MAX_VALUE��
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
   * ����ȱʡ���ĵ�ģ�͡�
   * @return ֻ������������ص����ݵ��ĵ�ģ��
   * @since  0.1
   */
  protected Document createDefaultModel() {
    return new NumberOnlyDocument(max, min, isLimit);
  }

}
