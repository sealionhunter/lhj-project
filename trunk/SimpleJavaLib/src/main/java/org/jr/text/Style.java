package org.jr.text;
/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��26��
 * @author Cherami
 */

/**
 * �ı����Ľӿڡ�
 * @since  0.5
 */

public interface Style {
  /**
   * �õ����Ŀ�ʼ���֡�
   * @return ���Ŀ�ʼ����
   */
  public String getBegin();
  /**
   * �õ����Ľ�β���֡�
   * @return ���Ľ�β����
   */
  public String getEnd();
  /**
   * �õ�����ȫ�����ݡ�
   * @return ����ȫ������
   */
  public String getStyle();
}