package org.jr.swing;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��15��
 * @author Cherami
 */
import javax.swing.*;

/**
 * ���Զ�̬����ť���ı�����ʾ��Ϣ�Լ�ͼ�����ʾ״̬�İ�ť�����
 * @since  0.3
 */

public class JDynamicButton
    extends JButton {
  String text = "";
  String tooltipText;
  Icon icon;
  /**
   * ����һ���հ׵�JDynamicButton��
   * @since  0.3
   */
  public JDynamicButton() {
  }

  /**
   * ����һ������ͼ���JDynamicButton��
   * @param icon ͼ��
   * @since  0.3
   */
  public JDynamicButton(Icon icon) {
    super(icon);
    this.icon = icon;
  }

  /**
   * ����һ�����а�ť�ı���JDynamicButton��
   * @param text ��ť�ı�
   * @since  0.4
   */
  public JDynamicButton(String text) {
    super(text);
    this.text = text;
  }

  /**
   * ����һ�����а�ť�ı�����ʾ��Ϣ��JDynamicButton��
   * @param text ��ť�ı�
   * @param tooltipText ��ʾ��Ϣ
   * @since  0.4
   */
  public JDynamicButton(String text, String tooltipText) {
    super(text);
    this.text = text;
    setToolTipText(tooltipText);
  }

  /**
   * ����һ�������ı���ͼ���JDynamicButton��
   * @param text ��ʾ�ı�
   * @param icon ͼ��
   * @since  0.3
   */
  public JDynamicButton(String text, Icon icon) {
    super(text, icon);
    this.text = text;
    this.icon = icon;
  }

  /**
   * ����һ�������ı�����ʾ��Ϣ��ͼ���JDynamicButton��
   * @param text ��ʾ�ı�
   * @param tooltipText ��ʾ��Ϣ
   * @param icon ͼ��
   * @since  0.3
   */
  public JDynamicButton(String text, String tooltipText, Icon icon) {
    super(text, icon);
    this.text = text;
    this.icon = icon;
    setToolTipText(tooltipText);
  }

  /**
   * ���ð�ť����ʾ�ı���
   * ���ԭ������ʾ�ı�����ʾģʽ������Ϊ����ʾ����ô���������Ժ󽫻���µ��ı���ʾ������
   * @param text ��ʾ�ı�
   * @since  0.3
   */
  public void setText(String text) {
    this.text = text;
    showText(true);
  }

  /**
   * ���ð�ť����ʾ��Ϣ��
   * ���ԭ������ʾ��Ϣ����ʾģʽ������Ϊ����ʾ����ô���������Ժ󽫻���µ���ʾ��Ϣ��ʾ������
   * @param tooltipText ��ʾ��Ϣ
   * @since  0.3
   */
  public void setToolTipText(String tooltipText) {
    this.tooltipText = tooltipText;
    showToolTipText(true);
  }

  /**
   * ���ð�ť��ͼ��
   * ���ԭ����ͼ�����ʾģʽ������Ϊ����ʾ����ô���������Ժ󽫻���µ�ͼ����ʾ������
   * @param defaultIcon ����״̬�µ�ȱʡͼ��
   * @since  0.3
   */
  public void setIcon(Icon defaultIcon) {
    this.icon = defaultIcon;
    showIcon(true);
  }

  /**
   * �����Ƿ���ʾ�ı�
   * @param show true��ʱ����ʾ��������ʾ��
   * @since  0.3
   */
  public void showText(boolean show) {
    if (show == true) {
      super.setText(text);
    }
    else {
      super.setText("");
    }
  }

  /**
   * �����Ƿ���ʾ��ʾ��Ϣ��
   * @param show true��ʱ����ʾ��������ʾ��
   * @since  0.3
   */
  public void showToolTipText(boolean show) {
    if (show == true) {
      super.setToolTipText(tooltipText);
    }
    else {
      super.setToolTipText(null);
    }

  }

  /**
   * ����ͼ���Ƿ���ʾ��
   * @param show true��ʱ����ʾ��������ʾ��
   * @since  0.3
   */
  public void showIcon(boolean show) {
    if (show == true) {
      super.setIcon(icon);
    }
    else {
      super.setIcon(null);
    }
  }

}