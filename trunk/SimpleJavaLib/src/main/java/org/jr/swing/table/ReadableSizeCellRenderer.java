package org.jr.swing.table;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��14��
 * @author Cherami
 */

import org.jr.text.*;

/**
 * �����Զ����Կɶ��ķ�ʽ��ʾ��С�ı��Ԫ��Ⱦ����
 * ע�⣬�����Ⱦ��ֻ�ܶ�Number���͵�ֵ��Ч��������ʾ��longֵ�ġ�
 * @since  0.5
 */

public class ReadableSizeCellRenderer
    extends FormatCellRenderer {
  boolean autoReadable = true;
  int unit = -1;

  /**
   * ����һ���Զ���ʾ�ɶ���С������ʾ��λ��ReadableSizeCellRenderer��
   * @since  0.5
   */
  public ReadableSizeCellRenderer() {
    formatter = new FileSizeFormat(true);
  }

  /**
   * ����ָ���Ĳ�������һ��ReadableSizeCellRenderer��
   * ��ʾ�ߴ絥λ��
   * @param autoReadable �Ƿ��Զ����ô�С�ĵ�λ
   * @param unit ��λ���˵�λֻ����autoReadable����Ϊfalseʱ����Ч
   * @since  0.5
   */
  public ReadableSizeCellRenderer(boolean autoReadable, int unit) {
    this.autoReadable = autoReadable;
    this.unit = unit;
    formatter = new FileSizeFormat(true);
  }

  /**
   * ����ָ���Ĳ�������һ��ReadableSizeCellRenderer��
   * @param autoReadable �Ƿ��Զ����ô�С�ĵ�λ
   * @param showUnit �Ƿ���ʾ��λ
   * @since  0.5
   */
  public ReadableSizeCellRenderer(boolean autoReadable, boolean showUnit) {
    this.autoReadable = autoReadable;
    formatter = new FileSizeFormat(showUnit);
  }

  /**
   * ����ָ���Ĳ�������һ��ReadableSizeCellRenderer��
   * @param autoReadable �Ƿ��Զ����ô�С�ĵ�λ
   * @param showUnit �Ƿ���ʾ��λ
   * @param unit ��λ���˵�λֻ����autoReadable����Ϊfalseʱ����Ч
   * @since  0.5
   */
  public ReadableSizeCellRenderer(boolean autoReadable, boolean showUnit,
                                  int unit) {
    this.autoReadable = autoReadable;
    formatter = new FileSizeFormat(showUnit);
    this.unit = unit;
  }

  /**
   * �����Ԫ��ֵ��ʽ����
   * @param value ԭʼֵ
   * @return ��ʽ���Ժ��ֵ
   * @since  0.5
   */
  protected String format(Object value) {
    String resultValue;
    if ( ( (Number) value).longValue() > 0) {
      if (autoReadable == true) {
        resultValue = formatter.format( (Number) value);
      }
      else {
        resultValue = ( (FileSizeFormat) formatter).format( (Number) value,
            unit);
      }
    }
    else {
      resultValue = "";
    }
    return resultValue;
  }

  /**
   * �����Ƿ��Զ��жϴ�С��
   * @param autoReadable �Ƿ��Զ��жϴ�С
   * @since  0.5
   */
  public void setAutoReadable(boolean autoReadable) {
    this.autoReadable = autoReadable;
    if (autoReadable == true && unit == -1) {
      unit = FileSizeFormat.BYTE;
    }
  }

  /**
   * �Ƿ��Զ��жϴ�С
   * @return �Զ��ж�ʱ����true�����򷵻�false
   * @since  0.5
   */
  public boolean isAutoReadable() {
    return autoReadable;
  }

  /**
   * ������ʾ�ĵ�λ��
   * ���ô˷����Ὣ�Զ��ж�ģʽ����Ϊfalse��ʹ��ָ���ĵ�λ������ʾ��
   * @param unit ��λ
   * @since  0.5
   */
  public void setUnit(int unit) {
    autoReadable = false;
    this.unit = unit;
  }

  /**
   * �õ���ʹ�õĵ�λ��
   * <b>ע�⣺</b>���Զ�ģʽ�´˷����ķ���ֵ��û������ģ���˷��ص���-1��
   * @return �Զ�ģʽ�·���-1�����򷵻�ָ���ĵ�λ
   * @since  0.5
   */
  public int getUnit() {
    if (autoReadable == true) {
      return unit;
    }
    else {
      return -1;
    }
  }

  /**
   * �����Ƿ���ʾ��λ��
   * @param unitNameVisible �Ƿ���ʾ��λ
   * @since  0.5
   */
  public void setUnitNameVisible(boolean unitNameVisible) {
    ( (FileSizeFormat) formatter).setUnitNameVisible(unitNameVisible);
  }

  /**
   * �Ƿ���ʾ��λ
   * @return ��ʾʱ����true�����򷵻�false
   * @since  0.5
   */
  public boolean isUnitNameVisible() {
    return ( (FileSizeFormat) formatter).isUnitNameVisible();
  }

}