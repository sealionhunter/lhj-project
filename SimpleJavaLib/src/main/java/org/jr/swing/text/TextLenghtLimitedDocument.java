package org.jr.swing.text;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��12��
 * @author Cherami
 */

import javax.swing.text.*;

/**
 * �������ݳ������޲��ҿ����Զ���Сд����ת��Ϊ��д��Swing�ĵ�ģ�͡�
 * <p><b>ע�⣺</b>��������0.12����ȹ��ܷ����˱仯��
 * ���ڿ��Կ�����ʱȡ����Сдת��Ϊ��д�Ĺ��ܡ�
 * @since  0.1
 */

public class TextLenghtLimitedDocument
    extends PlainDocument {
  /**
   * �����������󳤶ȡ�
   */
  protected int limitLength;
  /**
   * �Ƿ�Сдת��Ϊ��д��
   */
  protected boolean toUppercase = false;
  /**
   * ����ָ������󳤶ȹ���һ��TextLenghtLimitedDocument��
   * @param limitLength �����������󳤶�
   * @since  0.1
   */
  public TextLenghtLimitedDocument(int limitLength) {
    super();
    this.limitLength = limitLength;
  }

  /**
   * ����ָ������󳤶Ⱥ��Ƿ����Сд�Զ�ת����־����һ��TextLenghtLimitedDocument��
   * @param limitLength �����������󳤶�
   * @param upper �Ƿ��Զ���Сд����ת��Ϊ��д��true��ʱ��ת��������ת����
   * @since  0.1
   */
  public TextLenghtLimitedDocument(int limitLength, boolean toUppercase) {
    super();
    this.limitLength = limitLength;
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
   * ֻ�е���������ݵĳ���û�г�����󳤶�ʱ�ſ��Բ��룬�����Ĳ��ֱ����ԡ�
   * �������Զ���Сдת��Ϊ��дʱҲ�����ת����
   * �˷������̰߳�ȫ�ġ�
   * <p><b>ע�⣺</b>��������0.12����ȹ��ܷ����˱仯��
   * ԭ�������Ҫ������ܵ����ݵĳ��ȼ����������ݵĳ��ȳ��������ǲ������κ������ݣ�
   * �����޸�Ϊ�������ܲ��������ݣ���������ݱ����ԡ�
   * @param offs ����λ�õ�ƫ����
   * @param str ��������
   * @param a ���Լ���
   * @throws BadLocationException �����Ĳ���λ�ò����ĵ��е���Чλ��
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
