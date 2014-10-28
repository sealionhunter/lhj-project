package org.jr.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��1��
 * @author Cherami
 */
import java.lang.reflect.*;
import java.util.*;
import java.io.*;
/**
 * �漰��ķ����ȵĹ����ࡣ
 * @since  0.4
 */

public class ClassUtil {

  /**
   * ˽�й��췽������ֹ���ʵ��������Ϊ�����಻��Ҫʵ������
   */
  private ClassUtil() {
  }

  /**
   * ���������õ��������е�pulbic��Ա�����ķ�������
   * @param className ����
   * @return ���е�pulbic��Ա�����ķ���������
   * @since  0.4
   */
  public static String[] getMethods(String className) {
    String methodNames[];
    try {
      Class c = Class.forName(className);
      Method methods[] = c.getMethods();
      methodNames = new String[methods.length];
      for (int i = 0; i < methods.length; i++) {
        methodNames[i] = methods[i].toString();
      }
      return methodNames;
    }

    catch (ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * ���������õ������������ķ����ķ�������
   * @param className ����
   * @return �������ķ����ķ���������
   * @since  0.4
   */
  public static String[] getDeclaredMethods(String className) {
    String methodNames[];
    try {
      Class c = Class.forName(className);
      Method methods[] = c.getDeclaredMethods();
      methodNames = new String[methods.length];
      for (int i = 0; i < methods.length; i++) {
        methodNames[i] = methods[i].toString();
      }
      return methodNames;
    }

    catch (ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * �ж�һ�����������Ƿ���ͬ�����顣
   * �����ͬ�����ϸ��ͬ�ʣ������ǵ�ʵ�������ͱ�����ȫ��ͬ��
   * @param objects Ҫ�ȽϵĶ�������
   * @return ����Ԫ�ص���������ȫ��ͬʱ����true�����������ֻ��һ��Ԫ��ʱҲ����true�����򷵻�false
   * @since  0.5
   */
  public static boolean isSameClassType(Object[] objects) {
    if (objects.length == 1) {
      return true;
    }
    Class c = objects[0].getClass();
    for (int i = 1; i < objects.length; i++) {
      if (!c.equals(objects[i].getClass())) {
        return false;
      }
    }
    return true;
  }

  /**
   * �ж�һ�����������Ƿ���ͬ����������ָ���������͡�
   * �����ͬ�����ϸ��ͬ�ʣ������ǵ�ʵ�������ͱ�����ȫ��ͬ��
   * @param objects Ҫ�ȽϵĶ�������
   * @param c ������
   * @return ����Ԫ�ص������ͺ�c��ȫ��ͬʱ����true�����������ֻ��һ��Ԫ��ʱҲ����true�����򷵻�false
   * @since  0.5
   */
  public static boolean isSameClassType(Object[] objects, Class c) {
    if (objects.length == 1) {
      return true;
    }
    for (int i = 1; i < objects.length; i++) {
      if (!c.equals(objects[i].getClass())) {
        return false;
      }
    }
    return true;
  }

  /**
   * �ж�child�Ƿ���c��һ�����ࡣ
   * @param c ����
   * @param child Ҫ�жϵĿ��ܵ�����
   * @return child��c�������ʱ�򷵻�true��������������¶�����false
   * @since  0.5
   */
  public static boolean isSubclass(Class c, Class child) {
    try {
      if (c.isInstance(child.newInstance())) {
        return true;
      }
      return false;
    }
    catch (IllegalAccessException e) {
      return false;
    }
    catch (InstantiationException ie) {
      return false;
    }
    catch (Exception oe) {
      return false;
    }
  }
  /**
   * �õ�JDK1.4���е��������ϣ�ֻ����org,java,javax�������Ӱ��µ����������
   * ÿ��Ԫ�ص�ֵֻ��������û�а����Լ���չ��java��
   * @return �����ļ���
   * @since  0.5
   */
  public static TreeSet getAllClassname() {
    TreeSet classnameSet=new TreeSet();
    try {
      InputStream istream = ClassUtil.class.getResourceAsStream("allclassname.treeset");
      ObjectInputStream p = new ObjectInputStream(istream);
      classnameSet = (TreeSet)p.readObject();
      istream.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (ClassNotFoundException e1) {
      e1.printStackTrace();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return classnameSet;
  }

}