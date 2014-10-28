package org.jr.text;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����?������:2003�?3��3��
 * @author Cherami
 */

import java.text.*;

/**
 * �ļ?��С��ʽ���ࡣ
 * ����౾��Ӧ���Ǵ�NumberFormat��̳ж�������������NumberFormat��format(long number)������Ϊ
 * final�ģ���˲�ȡ��Format�̳У�������ʵ�ʾ���һ��NumberFormat��
 * @since  0.5
 */
public class FileSizeFormat
    extends Format {
  public static final int BYTE = 0;
  public static final int KILO = 1;
  public static final int MEGA = 2;
  public static final int GIGA = 3;
  public static final long KILOBYTE = 1024;
  public static final long MEGABYTE = 1024 << 10;
  public static final long GIGABYTE = 1024 << 20;
  public static final String[] defaultUnitNames = {
      "B", "K", "M", "G"};
  public static final String defaultFormat = "#,##0.##";
  String format;
  String[] unitNames;
  boolean showUnitName = false;
  NumberFormat formatter;
  /**
   * �õ�һ��ȱʡ��FileSizeFormat��
   * ������ʾ�ߴ�ĵ�λ����ʽΪȱʡ��ʽ"#,##0.##"��
   * @since  0.5
   */
  public FileSizeFormat() {
    this(defaultFormat, false, null);
  }

  /**
   * ����ָ���ĸ�ʽ���?һ��FileSizeFormat��
   * ����ʾ�ߴ�ĵ�λ��
   * @param format ��ʽ
   * @since  0.5
   */
  public FileSizeFormat(String format) {
    this(format, false, null);
  }

  /**
   * ����ָ�������Թ��?һ��FileSizeFormat��
   * @param showUnitName �Ƿ���ʾ��λ
   * @since  0.5
   */
  public FileSizeFormat(boolean showUnitName) {
    this(defaultFormat, showUnitName, defaultUnitNames);
  }

  /**
   * ����ָ����λ���?һ��FileSizeFormat��
   * ��ʾ�ߴ�ĵ�λ��
   * @param unitNames ��λ������Ϊ�ֽڡ�ǧ�ֽڡ����ֽںͰ����ֽڵĵ�λ��
   * @since  0.5
   */
  public FileSizeFormat(String[] unitNames) {
    this(defaultFormat, true, unitNames);
  }

  /**
   * ����ָ���ĸ�ʽ�͵�λ���?һ��FileSizeFormat��
   * ��ʾ�ߴ�ĵ�λ��
   * @param format ��ʽ
   * @param unitNames ��λ������Ϊ�ֽڡ�ǧ�ֽڡ����ֽںͰ����ֽڵĵ�λ��
   * @since  0.5
   */
  public FileSizeFormat(String format, String[] unitNames) {
    this(format, true, unitNames);
  }

  /**
   * ����ָ���ĸ�ʽ���Ƿ���ʾ�ߴ絥λ���?һ��FileSizeFormat��
   * @param format ��ʽ
   * @param showUnitName true��ʱ����ʾ��λ��ʹ��ȱʡ�ĵ�λ��
   * @since  0.5
   */
  public FileSizeFormat(String format, boolean showUnitName) {
    this(format, showUnitName, defaultUnitNames);
  }

  /**
   * ����ָ���ĸ�ʽ���Ƿ���ʾ�ߴ絥λ���?һ��FileSizeFormat��
   * @param format ��ʽ
   * @param showUnitName true��ʱ����ʾ��λ��ʹ��ȱʡ�ĵ�λ��
   * @param unitNames ��λ������Ϊ�ֽڡ�ǧ�ֽڡ����ֽںͰ����ֽڵĵ�λ��
   * @since  0.5
   */
  public FileSizeFormat(String format, boolean showUnitName, String[] unitNames) {
    this.format = format;
    formatter = new DecimalFormat(format);
    this.showUnitName = showUnitName;
    this.unitNames = unitNames;
  }

  /**
   * �õ��ɶ����?�ִ�С��һ�������ļ?�ߴ硣
   * @param number �?�֣�һ��Ӧ����Long���͵�
   * @return ��ʽ���Ժ���ַ���
   * @since  0.5
   */
  public String format(Number number) {
    return format(number.longValue(), getUnit(number.longValue()));
  }

  /**
   * �õ��ɶ����?�ִ�С��
   * @param size ԭʼ��С
   * @return ��ʽ���Ժ���ַ���
   * @since  0.5
   */
  public String format(long size) {
    return format(size, getUnit(size));
  }

  /**
   * �õ��?�ֵĵ�λ��һ�������ļ?�ߴ硣
   * @param number �?�֣�һ��Ӧ����Long���͵�
   * @return number��longֵ��1024����ʱ����BYTE����������ֱ��GIGA��
   * @since  0.5
   */
  public int getUnit(Number number) {
    return getUnit(number.longValue());
  }

  /**
   * �õ��?�ֵĵ�λ��
   * @param size ԭʼ��С
   * @return size��ֵ��1024����ʱ����BYTE����������ֱ��GIGA��
   * @since  0.5
   */
  public int getUnit(long size) {
    if (size < KILOBYTE) {
      return BYTE;
    }
    else if (size < MEGABYTE) {
      return KILO;
    }
    else if (size < GIGABYTE) {
      return MEGA;
    }
    else {
      return GIGA;
    }
  }

  /**
   * �õ���ʽ���Ĵ�С��
   * @param number ԭʼ��С
   * @param unit ��λ
   * @return ���ݵ�λ���и�ʽ������ַ�����С
   * @since  0.5
   */
  public String format(Number number, int unit) {
    return format(number.longValue(), unit);
  }

  /**
   * ���ø�ʽ��ʱ�ĸ�ʽ��
   * @param format ��ʽ
   * @since  0.5
   */
  public void setFormat(String format) {
    this.format = format;

  }

  /**
   * �õ���ʽʱ�ĸ�ʽ��
   * @return ��ʽʱ�ĸ�ʽ
   * @since  0.5
   */
  public String getFormat() {
    return format;
  }

  /**
   * �õ���ʽ���Ĵ�С��
   * @param size ԭʼ��С
   * @param unit ��λ
   * @return ���ݵ�λ���и�ʽ������ַ�����С
   * @since  0.5
   */
  public String format(long size, int unit) {
    String result;
    switch (unit) {
      case BYTE:
        result = formatter.format(size);
        break;
      case KILO:
        result = formatter.format( ( (double) size) / ( (double) KILOBYTE));
        break;
      case MEGA:
        result = formatter.format( ( (double) size) / ( (double) MEGABYTE));
        break;
      case GIGA:
        result = formatter.format( ( (double) size) / ( (double) GIGABYTE));
        break;
      default:
        result = formatter.format(size);
        break;
    }
    if (showUnitName == true) {
      result += unitNames[unit];
    }
    return result;
  }

  /**
   * ������ʾ��λ�����ơ�
   * �������ͬʱҲ�Ὣ������ʾ��λ���ơ�
   * @param unitNames �����?�?
   * @since  0.5
   */
  public void setUnitNames(String[] unitNames) {
    showUnitName = true;
    this.unitNames = unitNames;
  }

  /**
   * ������ʾ�����?�顣
   * @return �����?�?
   * @since  0.5
   */
  public String[] getUnitNames() {
    return unitNames;
  }

  /**
   * �����Ƿ���ʾ��λ���ơ�
   * �����ʾ��λ���Ƶ��������?��Ϊnullʱ����Ϊȱʡ��λ���ơ�
   * @param visible �Ƿ���ʾ��λ����
   * @since  0.5
   */
  public void setUnitNameVisible(boolean visible) {
    showUnitName = visible;
    if (showUnitName == true && unitNames == null) {
      unitNames = defaultUnitNames;
    }
  }

  /**
   * �Ƿ���ʾ��λ���ơ�
   * @return ��ʾʱ����true�����򷵻�false
   * @since  0.5
   */
  public boolean isUnitNameVisible() {
    return showUnitName;
  }

  /**
   * �����ַ���Ϊһ������
   * @param source Ҫ�����Ķ����ַ���
   * @param pos ����λ��
   * @return ��NumberFormat�����Ľ�?
   * @since  0.5
   */
  public Object parseObject(String source,
                            ParsePosition pos) {
    return formatter.parseObject(source, pos);
  }

  /**
   * ����ָ���Ķ���͸��Ӷ�����и�ʽ����
   * @param obj ��ʽ���Ķ��?
   * @param toAppendTo ���?���ı���Ϣ
   * @param pos ��ʽ�����ı��е�λ����Ϣ
   * @return ��NumberFormat��ʽ���Ժ��StringBuffer
   * @since  0.5
   */
  public StringBuffer format(Object obj,
                             StringBuffer toAppendTo,
                             FieldPosition pos) {
    return formatter.format(obj, toAppendTo, pos);

  }

}