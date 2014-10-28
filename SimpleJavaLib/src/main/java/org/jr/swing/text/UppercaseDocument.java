package org.jr.swing.text;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��12��
 * @author Cherami
 */

import javax.swing.text.*;

/**
 * �Զ���Сдת��Ϊ��д��Swing�ĵ�ģ�͡�
 * <p><b>ע�⣺</b>��������0.12����ȹ��ܷ����˱仯��
 * ���ڿ��Կ�����ʱȡ����Сдת��Ϊ��д�Ĺ��ܡ�
 * @since  0.1
 */

public class UppercaseDocument
    extends PlainDocument {
  /**
   * �Ƿ�Сдת��Ϊ��д��
   */
  protected boolean toUppercase = true;
  /**
   * ����һ����Сдת��Ϊ��д��UppercaseDocument��
   * @since  0.1
   */
  public UppercaseDocument() {
    super();
  }

  /**
   * ����ָ����ת��״̬����һ��UppercaseDocument��
   * @param toUppercase �Ƿ�Сдת��Ϊ��д��true��ʱ�����ת��������ת����
   * @since  0.3
   */
  public UppercaseDocument(boolean toUppercase) {
    this.toUppercase = toUppercase;
  }

  /**
   * �����Ƿ�Сдת��Ϊ��д��
   * @param toUppercase �Ƿ�Сдת��Ϊ��д��true��ʱ�����ת��������ת����
   * @since  0.3
   */
  public void setToUppercase(boolean toUppercase) {
    this.toUppercase = toUppercase;
  }

  /**
   * �����Ƿ�Сдת��Ϊ��д��
   * @return ��Сдת��Ϊ��дʱ����true�����򷵻�false��
   * @since  0.3
   */
  public boolean isToUppercase() {
    return toUppercase;
  }

  /**
   * ����ĳЩ���ݵ��ĵ��С�
   * �Զ���Сд����ת��Ϊ��д���롣
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
    if (toUppercase) {
      str = str.toUpperCase();
    }
    super.insertString(offs, str, a);
  }
}