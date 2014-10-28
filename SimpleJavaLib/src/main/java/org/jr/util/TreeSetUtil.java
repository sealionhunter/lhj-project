package org.jr.util;
/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��26��
 * @author Cherami
 */
import java.util.*;

/**
 * �����з�װһЩ���õ�TreeSet����������
 * ���з������Ǿ�̬����������Ҫ���ɴ����ʵ����
 * Ϊ�������ɴ����ʵ�������췽��������Ϊprivate���͵ġ�
 * @since  0.5
 */

public class TreeSetUtil {
  /**
   * ˽�й��췽������ֹ���ʵ��������Ϊ�����಻��Ҫʵ������
   */
  private TreeSetUtil() {
  }
  /**
   * ������ת��ΪTreeSet��
   * @param array ����
   * @return ת�����TreeSet���������Ϊnull�򷵻�null
   * @since  0.5
   */
  public static TreeSet ArrayToTreeSet(Object[] array) {
    if (array != null) {
      TreeSet<Object> treeSet = new TreeSet<Object>();
      for (int i = 0; i < array.length; i++) {
        treeSet.add(array[i]);
      }
      return treeSet;
    }
    else {
      return null;
    }
  }
}