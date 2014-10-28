package org.jr.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��11��
 * @author Cherami
 */

/**
 * ������������࣬�ṩ���������������Ҫ�ķ�����
 * @since  0.5
 */

public class ArrayUtil {
  /**
   * ˽�й��췽������ֹ���ʵ��������Ϊ�����಻��Ҫʵ������
   */
  private ArrayUtil() {
  }

  /**
   * �õ���ʼ���õ�int���顣
   * @param length ���鳤��
   * @return ��ʼ�����int���飬����Ԫ�ص�ֵ��������ֵ��ȡ�
   * @since  0.5
   */
  public static int[] getInitedIntArray(int length) {
    int[] indexes = new int[length];
    for (int i = 0; i < length; i++) {
      indexes[i] = i;
    }
    return indexes;
  }

  /**
   * �õ���ʼ���õ�int���顣
   * @param length ���鳤��
   * @param value ��ʼֵ
   * @return ��ʼ�����int���飬����Ԫ�ص�ֵ������ָ����value��
   * @since  0.5
   */
  public static int[] getInitedIntArray(int length, int value) {
    int[] indexes = new int[length];
    for (int i = 0; i < length; i++) {
      indexes[i] = value;
    }
    return indexes;
  }

  /**
   * �õ���ʼ���õ�boolean���顣
   * @param length ���鳤��
   * @param value ��ʼֵ
   * @return ��ʼ�����boolean���飬����Ԫ�ص�ֵ������value��
   * @since  0.5
   */
  public static boolean[] getInitedBooleanArray(int length, boolean value) {
    boolean[] indexes = new boolean[length];
    for (int i = 0; i < length; i++) {
      indexes[i] = value;
    }
    return indexes;
  }

  /**
   * �õ�ָ���Ķ����ڶ��������е�������
   * @param objects ��������
   * @param object ����
   * @return �����ڶ��������е�λ�ã���������������ʱ����-1
   * @since  0.5
   */
  public static int indexOf(Object[] objects, Object object) {
    for (int i = 0; i < objects.length; i++) {
      if (objects[i].equals(object)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * ��ԭ�����ֵ������Ŀ�����顣
   * Ŀ������Ĵ�С������ڵ���ԭ���飬һ��Ӧ���Ǵ�С��ȣ����Ŀ������Ĵ�С�ϴ�����ڵĲ��ֱ���ԭֵ��
   * @param orginalArray ԭ����
   * @param targetArray Ŀ������
   * @since  0.5
   */
  public static void copyArrayValue(int[] orginalArray, int[] targetArray) {
    for (int i = 0; i < orginalArray.length; i++) {
      targetArray[i] = orginalArray[i];
    }
  }

  /**
   * �������е�ֵ��λ��
   * �ƶ��ķ�ʽ�ǽ�ָ��λ���Ժ��ÿ��Ԫ��������ǰ�ƶ�һλ��ָ��λ�õ�ֵ�Ƶ����
   * @param array ����
   * @param index λ��
   */
  public static void shiftArray(int[] array, int index) {
    int temp = array[index];
    int length = array.length - 1;
    for (int i = index; i < length; i++) {
      array[i] = array[i + 1];
    }
    array[length] = temp;
  }

  /**
   * ��ӡ�����ֵ��
   * ����ǰ��Ϊһ��"["��"]"���м��ö��ŷָ���
   * @param array ����
   */
  public static void printArray(int[] array) {
    int length = array.length - 1;
    StringBuffer buffer = new StringBuffer(length * 5);
    buffer.append("[");
    for (int i = 0; i < length; i++) {
      buffer.append(array[i]);
      buffer.append(",");
    }
    buffer.append(array[length] + "]");
    System.out.println(buffer.toString());
  }

  /**
   * ��ָ����ֵ���뵽�����е�ָ��λ�á�
   * �������һ��Ԫ�ص�ֵ����������
   * @param array
   * @param index
   * @param value
   */
  public static void insertValueToArray(int[] array, int index, int value) {
    int length = array.length - 1;
    for (int i = length; i > index; i--) {
      array[i] = array[i - 1];
    }
    array[index] = value;
  }
}