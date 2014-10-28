package org.jr.text;
/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��26��
 * @author Cherami
 */

/**
 * HTML����ʵ���ࡣ
 * @since  0.5
 */

public class HTMLStyle extends DefaultStyle{
  /**
   * ����һ���յ�HTMLStyle��
   * ����Ϊ""��
   */
  public HTMLStyle() {
    super();
  }
  /**
   * ��ָ���Ŀ�ʼ���������һ��HTMLStyle��
   * @param begin ���Ŀ�ʼ����
   * @param end ���Ľ�������
   * @since  0.5
   */
  public HTMLStyle(String begin,String end) {
    super(begin,end);
    if (begin!=null) {
      this.begin = begin;
    }
    else {
      this.begin="";
    }
    if (end!=null) {
      this.end = end;
    }
    else {
      this.end="";
    }
  }

}