package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��4��
 * @author Cherami
 */
/**
 * ����һ�����ݵĽӿڡ�
 * @since  0.5
 */

public interface TableLineData {
  /**
   * �õ�����һ�����������������ݵ�������
   * ���⽨��ʵ�ִ˽ӿڵ��ඨ��һ����̬��Class getColumnClass(int column)����������ģ�͵õ���Ӧ���������͡�
   * @return һ�����������������ݵ�����
   */
  public int getCount();

  /**
   * �õ�ָ���е�ֵ��
   * @param column ��
   * @return ָ���е�ֵ
   */
  public Object get(int column);

  /**
   * ����ָ���е�ֵ��
   * @param column ��
   * @param value ֵ
   */
  public void set(int column, Object value);

}