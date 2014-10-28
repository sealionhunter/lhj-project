package org.jr.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��11��
 * @author Cherami
 */

/**
 * boolean����������ݵĲ��������࣬�ṩ������boolean���Ͳ�����Ҫ�ķ�����
 * @since  0.5
 */

public class BooleanUtil {

  /**
   * ˽�й��췽������ֹ���ʵ��������Ϊ�����಻��Ҫʵ������
   */
  private BooleanUtil() {
  }

  /**
   * �����͵���ת��Ϊboolean���飬�����Ӧ��λ��ֵΪ1�������еĶ�Ӧ��Ԫ�ظ�ֵΪtrue������Ϊfalse��
   * ����valueΪ5����õ�һ������Ϊ32��ֻ��0��2����Ԫ�ص�ֵΪtrue�����顣
   * @param value ֵ
   * @return ת�����boolean����
   * @since  0.5
   */
  public static boolean[] convertToArray(int value) {
    int length = 32;
    boolean[] result = new boolean[length];
    int mark = 1;
    for (int i = 0; i < length; i++) {
      if ( (mark & value) != 0) {
        result[i] = true;
      }
      else {
        result[i] = false;
      }
      mark = mark << 1;
    }
    return result;
  }

  /**
   * ���ֽ��͵���ת��Ϊboolean���飬�����Ӧ��λ��ֵΪ1�������еĶ�Ӧ��Ԫ�ظ�ֵΪtrue������Ϊfalse��
   * ����valueΪ5����õ�һ������Ϊ8��ֻ��0��2����Ԫ�ص�ֵΪtrue�����顣
   * @param value ֵ
   * @return ת�����boolean����
   * @since  0.5
   */
  public static boolean[] convertToArray(byte value) {
    int length = 8;
    boolean[] result = new boolean[length];
    int mark = 1;
    for (int i = 0; i < length; i++) {
      if ( (mark & value) != 0) {
        result[i] = true;
      }
      else {
        result[i] = false;
      }
      mark = mark << 1;
    }
    return result;
  }

  /**
   * �������͵���ת��Ϊboolean���飬�����Ӧ��λ��ֵΪ1�������еĶ�Ӧ��Ԫ�ظ�ֵΪtrue������Ϊfalse��
   * ����valueΪ5����õ�һ������Ϊ64��ֻ��0��2����Ԫ�ص�ֵΪtrue�����顣
   * @param value ֵ
   * @return ת�����boolean����
   * @since  0.5
   */
  public static boolean[] convertToArray(long value) {
    int length = 64;
    boolean[] result = new boolean[length];
    long mark = 1;
    for (int i = 0; i < length; i++) {
      if ( (mark & value) != 0) {
        result[i] = true;
      }
      else {
        result[i] = false;
      }
      mark = mark << 1;
    }
    return result;
  }

  /**
   * ���̽��͵���ת��Ϊboolean���飬�����Ӧ��λ��ֵΪ1�������еĶ�Ӧ��Ԫ�ظ�ֵΪtrue������Ϊfalse��
   * ����valueΪ5����õ�һ������Ϊ16��ֻ��0��2����Ԫ�ص�ֵΪtrue�����顣
   * @param value ֵ
   * @return ת�����boolean����
   * @since  0.5
   */
  public static boolean[] convertToArray(short value) {
    int length = 16;
    boolean[] result = new boolean[length];
    long mark = 1;
    for (int i = 0; i < length; i++) {
      if ( (mark & value) != 0) {
        result[i] = true;
      }
      else {
        result[i] = false;
      }
      mark = mark << 1;
    }
    return result;
  }

  /**
   * ��boolean����ת��Ϊһ������ֵ������ĳ����������32������Ĳ��ֱ����ԡ�
   * ����һ������Ϊ8�����飬ֻ��0��3����Ԫ�ص�ֵΪtrue��������ֵ9��
   * @param values boolean����
   * @return ת���������ֵ
   * @since  0.5
   */
  public static int convertToInt(boolean[] values) {
    int length = 8;
    int value = 0;
    int mark = 1;
    for (int i = 0; i < length; i++) {
      if (values[i] == true) {
        value = value + mark;
      }
      mark = mark << 1;
    }
    return value;
  }

  /**
   * ��boolean����ת��Ϊһ���ֽ���ֵ������ĳ����������8������Ĳ��ֱ����ԡ�
   * ����һ������Ϊ8�����飬ֻ��0��3����Ԫ�ص�ֵΪtrue��������ֵ9��
   * @param values boolean����
   * @return ת������ֽ���ֵ
   * @since  0.5
   */
  public static byte convertToByte(boolean[] values) {
    int length = 8;
    byte value = 0;
    byte mark = 1;
    for (int i = 0; i < length; i++) {
      if (values[i] == true) {
        value = (byte) (value + mark);
      }
      mark = (byte) (mark << 1);
    }
    return value;
  }

  /**
   * ��boolean����ת��Ϊһ��������ֵ������ĳ����������16������Ĳ��ֱ����ԡ�
   * ����һ������Ϊ8�����飬ֻ��0��3����Ԫ�ص�ֵΪtrue��������ֵ9��
   * @param values boolean����
   * @return ת����Ķ�����ֵ
   * @since  0.5
   */
  public static short convertToShort(boolean[] values) {
    int length = 16;
    short value = 0;
    short mark = 1;
    for (int i = 0; i < length; i++) {
      if (values[i] == true) {
        value = (short) (value + mark);
      }
      mark = (short) (mark << 1);
    }
    return value;
  }

  /**
   * ��boolean����ת��Ϊһ��������ֵ������ĳ����������64������Ĳ��ֱ����ԡ�
   * ����һ������Ϊ8�����飬ֻ��0��3����Ԫ�ص�ֵΪtrue��������ֵ9��
   * @param values boolean����
   * @return ת����ĳ�����ֵ
   * @since  0.5
   */
  public static long convertToLong(boolean[] values) {
    int length = 64;
    long value = 0;
    long mark = 1;
    for (int i = 0; i < length; i++) {
      if (values[i] == true) {
        value = value + mark;
      }
      mark = mark << 1;
    }
    return value;
  }
}