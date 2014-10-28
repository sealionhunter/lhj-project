package org.jr.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��4��
 * @author Cherami
 */

import java.util.*;

/**
 * �����з�װһЩ���õ�List����������
 * ���з������Ǿ�̬����������Ҫ���ɴ����ʵ����
 * Ϊ�������ɴ����ʵ�������췽��������Ϊprivate���͵ġ�
 * @since  0.5
 */

public class ListUtil {
  /**
   * ˽�й��췽������ֹ���ʵ��������Ϊ�����಻��Ҫʵ������
   */
  private ListUtil() {
  }

  /**
   * ������ת��Ϊһ��List��ʵ������һ��ArrayList��
   * @param array ԭ����
   * @return ԭ���鲻Ϊnull��ʱ�򷵻ذ����������ݵ�ArrayList�����򷵻�null
   * @since  0.5
   */
  public static List ArrayToList(Object[] array) {
    if (array != null) {
      ArrayList<Object> list = new ArrayList<Object>(array.length);
      for (int i = 0; i < array.length; i++) {
        list.add(array[i]);
      }
      return list;
    }
    else {
      return null;
    }
  }

  /**
   * �������е�����ȫ����ӵ��б��С�
   * @param array ����
   * @param list �б�
   * @since  0.5
   */
  public static void addArrayToList(Object[] array, List<Object> list) {
    if (array == null || list == null || array.length == 0) {
      return;
    }
    for (int i = 0; i < array.length; i++) {
      list.add(array[i]);
    }
  }

  /**
   * �������е�����ȫ����ӵ��б��С�
   * @param array ����
   * @param list �б�
   * @param start ��ʼλ��
   * @since  0.5
   */
  public static void addArrayToList(Object[] array, List<Object> list, int start) {
    if (array == null || list == null || array.length == 0) {
      return;
    }
    for (int i = 0; i < array.length; i++) {
      list.add(start + i, array[i]);
    }
  }

  /**
   * �ƶ��б��е�Ԫ��
   * @param list �б�
   * @param start �ƶ���Ԫ�صĿ�ʼ����
   * @param end �ƶ���Ԫ�ص�������������������
   * @param to �ƶ�����λ��
   * @since  0.5
   */
  public static void moveElements(List<Object> list, int start, int end, int to) {
    List<Object> subList = new ArrayList<Object>(list.subList(start, end));
    list.removeAll(subList);
    list.addAll(to, subList);
  }

}