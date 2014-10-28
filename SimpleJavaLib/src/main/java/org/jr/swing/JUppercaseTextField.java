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
 * �������Сд��ĸ�Զ�ת��Ϊ��д��Swing�����
 * @since  0.1
 */

public class JUppercaseTextField
    extends JTextField {
  /**
   * ȱʡ���췽��������һ���µ�JUppercaseTextField��
   * @since  0.3
   */
  public JUppercaseTextField() {
    super();
  }

  /**
   * ʹ��ָ�����ַ��������������ѿ�ȵ�JUppercaseTextField��
   * @param columns ���ڼ�����ѿ�ȵ�����
   * @since  0.1
   * @see javax.swing.JTextField#JTextField(int columns) javax.swing.JTextField.JTextField(int columns)
   */
  public JUppercaseTextField(int columns) {
    super(columns);
  }

  /**
   * ��ָ�����ı�����һ���µ�JUppercaseTextField��
   * @param text ��ʼ��ʾ�ı�
   * @since  0.3
   */
  public JUppercaseTextField(String text) {
    super(text);
  }

  /**
   * ��ָ�����ı�����������һ��������ѿ�ȵ��µ�JUppercaseTextField��
   * @param text ��ʼ��ʾ�ı�
   * @param columns ��ʼ������
   * @since  0.3
   */
  public JUppercaseTextField(String text, int columns) {
    super(text, columns);
  }

  /**
   * ����ȱʡ���ĵ�ģ�͡�
   * @return �Զ���Сд��ĸת��Ϊ��С��ĸ���ĵ�ģ��
   * @since  0.1
   */
  protected Document createDefaultModel() {
    return new UppercaseDocument();
  }

}
