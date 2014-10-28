package org.jr.text;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��26��
 * @author Cherami
 */

/**
 * ����ȱʡʵ���ࡣ
 * @since  0.5
 */

public class DefaultStyle
    implements Style {
  protected String begin;
  protected String end;
  /**
   * ����һ�����Ϊ�յ�DefaultStyle��
   * @since  0.5
   */
  public DefaultStyle() {
    begin="";
    end="";
  }

  /**
   * ��ָ���Ŀ�ʼ���������һ��DefaultStyle��
   * @param begin ���Ŀ�ʼ����
   * @param end ���Ľ�������
   * @since  0.5
   */
  public DefaultStyle(String begin, String end) {
    if (begin != null) {
      this.begin = begin;
    }
    else {
      this.begin = "";
    }
    if (end != null) {
      this.end = end;
    }
    else {
      this.end = "";
    }
  }

  /**
   * �õ����Ŀ�ʼ���֡�
   * @return ���Ŀ�ʼ����
   * @since  0.5
   */
  public String getBegin() {
    return begin;
  }
  /**
   * ���÷��Ŀ�ʼ���֡�
   * @param begin ���Ŀ�ʼ����
   * @since  0.5
   */
  public void setBegin(String begin) {
    if (begin != null) {
      this.begin = begin;
    }
    else {
      this.begin = "";
    }
  }

  /**
   * �õ����Ľ�β���֡�
   * @return ���Ľ�β����
   * @since  0.5
   */
  public String getEnd() {
    return end;
  }
  /**
   * ���÷��Ľ������֡�
   * @param end ���Ľ�������
   * @since  0.5
   */
  public void setEnd(String end) {
    if (end != null) {
      this.end = end;
    }
    else {
      this.end = "";
    }
  }

  /**
   * �õ�����ȫ�����ݡ�
   * @return ����ȫ������
   * @since  0.5
   */
  public String getStyle() {
    return begin + end;
  }

  /**
   * ����ַ�����ʾ��
   * @return �ַ�����ʾ
   * @since  0.5
   */
  public String toString() {
    return begin + end;
  }
}
