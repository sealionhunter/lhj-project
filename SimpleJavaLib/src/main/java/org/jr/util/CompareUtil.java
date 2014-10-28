package org.jr.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��4��
 * @author Cherami
 */

import org.jr.*;

/**
 * ���򹤾��࣬�ṩ��������Ҫ��������û��ʵ��Comparable�ӿڵ��ࡣ
 * ����Ҳ�ṩһЩ�����������㷨�ķ�����
 * @since  0.5
 */

public class CompareUtil {
  /**
   * ˽�й��췽������ֹ���ʵ��������Ϊ�����಻��Ҫʵ������
   */
  private CompareUtil() {
  }

  /**
   * �Ƚ��������֡�
   * �������ȡNumber��doubleֵ���бȽϣ����ֻ�����������ϸ���ȵ�����²ŷ���0��
   * �ֿ�����ΪJava��Double�ͺ�Float�ľ��Ȳ�ͬ������������ȵ���������0��
   * @param o1 ��һ������
   * @param o2 �ڶ�������
   * @return ��һ�����ִ��ڵڶ�������1�����ڷ���0�����򷵻أ�1
   * @since  0.5
   */
  public static int compare(Number o1, Number o2) {
    double n1 = o1.doubleValue();
    double n2 = o2.doubleValue();
    if (n1 < n2) {
      return -1;
    }
    else if (n1 > n2) {
      return 1;
    }
    else {
      return 0;
    }
  }

  /**
   * �Ƚ�����������ֵ��
   * ���������ֵ��ͬ��true����Ϊ����1����false����0��
   * @param o1 ��һ��ֵ
   * @param o2 �ڶ���ֵ
   * @return ��һ��ֵ���ڵڶ�������1�����ڷ���0�����򷵻�-1
   * @since  0.5
   */
  public static int compare(Boolean o1, Boolean o2) {
    boolean b1 = o1.booleanValue();
    boolean b2 = o2.booleanValue();
    if (b1 == b2) {
      return 0;
    }
    else if (b1) {
      return 1;
    }
    else {
      return -1;
    }
  }

  /**
   * ð����������
   * @param objects �������
   * @param isAscent ����˳��
   * @return �����Ӧ���е���������
   * @since  0.5
   */
  public static int[] n2sort(Comparable<Object>[] objects, boolean isAscent) {
    int length = objects.length;
    int[] indexes = ArrayUtil.getInitedIntArray(length);
    for (int i = 0; i < length; i++) {
      for (int j = i + 1; j < length; j++) {
        if (isAscent == true) {
          if (objects[indexes[i]].compareTo(objects[indexes[j]]) > 0) {
            swap(indexes, i, j);
          }
        }
        else {
          if (objects[indexes[i]].compareTo(objects[indexes[j]]) < 0) {
            swap(indexes, i, j);
          }
        }
      }
    }
    return indexes;
  }

  /**
   * ð����������
   * @param objects �������
   * @param key ����ؼ���
   * @param isAscent ����˳��
   * @return �����Ӧ���е���������
   * @since  0.5
   */
  public static int[] n2sort(PropertyComparable[] objects, int key,
                             boolean isAscent) {
    int length = objects.length;
    int[] indexes = ArrayUtil.getInitedIntArray(length);
    for (int i = 0; i < length; i++) {
      for (int j = i + 1; j < length; j++) {
        if (isAscent == true) {
          if (objects[indexes[i]].compareTo(objects[indexes[j]], key) > 0) {
            swap(indexes, i, j);
          }
        }
        else {
          if (objects[indexes[i]].compareTo(objects[indexes[j]], key) < 0) {
            swap(indexes, i, j);
          }
        }
      }
    }
    return indexes;
  }

  /**
   * ��������Ԫ�ص�ֵ��
   * @param indexes ԭ��������
   * @param i ��һ��
   * @param j �ڶ���
   */
  private static void swap(int[] indexes, int i, int j) {
    int tmp = indexes[i];
    indexes[i] = indexes[j];
    indexes[j] = tmp;
  }

}