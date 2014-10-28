package org.jr.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��19��
 * @author Cherami
 */

import java.io.*;
import java.util.*;

/**
 * �����з�װһЩ���õ��ַ���������
 * ���з������Ǿ�̬����������Ҫ���ɴ����ʵ����
 * Ϊ�������ɴ����ʵ�������췽��������Ϊprivate���͵ġ�
 * @since  0.1
 */
public class StringUtil {
  /**
   * ˽�й��췽������ֹ���ʵ��������Ϊ�����಻��Ҫʵ������
   */
  private StringUtil() {
  }

  /**
   * �˷������������ַ���sourceʹ��delim����Ϊ�������顣
   * @param source ��Ҫ���л��ֵ�ԭ�ַ���
   * @param delim ���ʵķָ��ַ���
   * @return �����Ժ�����飬���sourceΪnull��ʱ�򷵻���sourceΪΨһԪ�ص����飬
   *         ���delimΪnull��ʹ�ö�����Ϊ�ָ��ַ�����
   * @since  0.1
   */
  public static String[] split(String source, String delim) {
    String[] wordLists;
    if (source == null) {
      wordLists = new String[1];
      wordLists[0] = source;
      return wordLists;
    }
    if (delim == null) {
      delim = ",";
    }
    StringTokenizer st = new StringTokenizer(source, delim);
    int total = st.countTokens();
    wordLists = new String[total];
    for (int i = 0; i < total; i++) {
      wordLists[i] = st.nextToken();
    }
    return wordLists;
  }

  /**
   * �˷������������ַ���sourceʹ��delim����Ϊ�������顣
   * @param source ��Ҫ���л��ֵ�ԭ�ַ���
   * @param delim ���ʵķָ��ַ�
   * @return �����Ժ�����飬���sourceΪnull��ʱ�򷵻���sourceΪΨһԪ�ص����顣
   * @since  0.2
   */
  public static String[] split(String source, char delim) {
    return split(source, String.valueOf(delim));
  }

  /**
   * �˷������������ַ���sourceʹ�ö��Ż���Ϊ�������顣
   * @param source ��Ҫ���л��ֵ�ԭ�ַ���
   * @return �����Ժ�����飬���sourceΪnull��ʱ�򷵻���sourceΪΨһԪ�ص����顣
   * @since  0.1
   */
  public static String[] split(String source) {
    return split(source, ",");
  }

  /**
   * ѭ����ӡ�ַ������顣
   * �ַ�������ĸ�Ԫ�ؼ���ָ���ַ��ָ�������ַ������Ѿ�����ָ���ַ������ַ��������˼���˫���š�
   * @param strings �ַ�������
   * @param delim �ָ���
   * @param out ��ӡ���������
   * @since  0.4
   */
  public static void printStrings(String[] strings, String delim,
                                  OutputStream out) {
    try {
      if (strings != null) {
        int length = strings.length - 1;
        for (int i = 0; i < length; i++) {
          if (strings[i] != null) {
            if (strings[i].indexOf(delim) > -1) {
              out.write( ("\"" + strings[i] + "\"" + delim).getBytes());
            }
            else {
              out.write( (strings[i] + delim).getBytes());
            }
          }
          else {
            out.write("null".getBytes());
          }
        }
        if (strings[length] != null) {
          if (strings[length].indexOf(delim) > -1) {
            out.write( ("\"" + strings[length] + "\"").getBytes());
          }
          else {
            out.write(strings[length].getBytes());
          }
        }
        else {
          out.write("null".getBytes());
        }
      }
      else {
        out.write("null".getBytes());
      }
      out.write(Constants.LINE_SEPARATOR.getBytes());
    }
    catch (IOException e) {

    }
  }

  /**
   * ѭ����ӡ�ַ������鵽��׼�����
   * �ַ�������ĸ�Ԫ�ؼ���ָ���ַ��ָ�������ַ������Ѿ�����ָ���ַ������ַ��������˼���˫���š�
   * @param strings �ַ�������
   * @param delim �ָ���
   * @since  0.4
   */
  public static void printStrings(String[] strings, String delim) {
    printStrings(strings, delim, System.out);
  }

  /**
   * ѭ����ӡ�ַ������顣
   * �ַ�������ĸ�Ԫ�ؼ��Զ��ŷָ�������ַ������Ѿ��������������ַ��������˼���˫���š�
   * @param strings �ַ�������
   * @param out ��ӡ���������
   * @since  0.2
   */
  public static void printStrings(String[] strings, OutputStream out) {
    printStrings(strings, ",", out);
  }

  /**
   * ѭ����ӡ�ַ������鵽ϵͳ��׼�����System.out��
   * �ַ�������ĸ�Ԫ�ؼ��Զ��ŷָ�������ַ������Ѿ��������������ַ��������˼���˫���š�
   * @param strings �ַ�������
   * @since  0.2
   */
  public static void printStrings(String[] strings) {
    printStrings(strings, ",", System.out);
  }

  /**
   * ���ַ����еı���ʹ��values�����е����ݽ����滻��
   * �滻�Ĺ����ǲ�����Ƕ�׵ģ�������滻�������а����������ʽʱ�����滻��
   * @param prefix ����ǰ׺�ַ���
   * @param source ��������ԭ�ַ���
   * @param values �滻�õ��ַ�������
   * @return �滻����ַ�����
   *         ���ǰ׺Ϊnull��ʹ�á�%����Ϊǰ׺��
   *         ���source����valuesΪnull����values�ĳ���Ϊ0�򷵻�source��
   *         ���values�ĳ��ȴ��ڲ����ĸ����������ֵ�������ԣ�
   *         ���values�ĳ���С�ڲ����ĸ��������������в�����ʹ�����һ��ֵ�����滻��
   * @since  0.2
   */
  public static String getReplaceString(String prefix, String source,
                                        String[] values) {
    String result = source;
    if (source == null || values == null || values.length < 1) {
      return source;
    }
    if (prefix == null) {
      prefix = "%";
    }

    for (int i = 0; i < values.length; i++) {
      String argument = prefix + Integer.toString(i + 1);
      int index = result.indexOf(argument);
      if (index != -1) {
        String temp = result.substring(0, index);
        if (i < values.length) {
          temp += values[i];
        }
        else {
          temp += values[values.length - 1];
        }
        temp += result.substring(index + 2);
        result = temp;
      }
    }
    return result;
  }

  /**
   * ���ַ����еı������ԡ�%��Ϊǰ��������֣�ʹ��values�����е����ݽ����滻��
   * �滻�Ĺ����ǲ�����Ƕ�׵ģ�������滻�������а����������ʽʱ�����滻��
   * @param source ��������ԭ�ַ���
   * @param values �滻�õ��ַ�������
   * @return �滻����ַ���
   * @since  0.1
   */
  public static String getReplaceString(String source, String[] values) {
    return getReplaceString("%", source, values);
  }

  /**
   * �ַ����������Ƿ����ָ�����ַ�����
   * @param strings �ַ�������
   * @param string �ַ���
   * @param caseSensitive �Ƿ��Сд����
   * @return ����ʱ����true�����򷵻�false
   * @since  0.4
   */
  public static boolean contains(String[] strings, String string,
                                 boolean caseSensitive) {
    for (int i = 0; i < strings.length; i++) {
      if (caseSensitive == true) {
        if (strings[i].equals(string)) {
          return true;
        }
      }
      else {
        if (strings[i].equalsIgnoreCase(string)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * �ַ����������Ƿ����ָ�����ַ�������Сд���С�
   * @param strings �ַ�������
   * @param string �ַ���
   * @return ����ʱ����true�����򷵻�false
   * @since  0.4
   */
  public static boolean contains(String[] strings, String string) {
    return contains(strings, string, true);
  }

  /**
   * �����ִ�Сд�ж��ַ����������Ƿ����ָ�����ַ�����
   * @param strings �ַ�������
   * @param string �ַ���
   * @return ����ʱ����true�����򷵻�false
   * @since  0.4
   */
  public static boolean containsIgnoreCase(String[] strings, String string) {
    return contains(strings, string, false);
  }

  /**
   * ���ַ�������ʹ��ָ���ķָ����ϲ���һ���ַ�����
   * @param array �ַ�������
   * @param delim �ָ�����Ϊnull��ʱ��ʹ��""��Ϊ�ָ�������û�зָ�����
   * @return �ϲ�����ַ���
   * @since  0.4
   */
  public static String combineStringArray(String[] array, String delim) {
    int length = array.length - 1;
    if (delim == null) {
      delim = "";
    }
    StringBuffer result = new StringBuffer(length * 8);
    for (int i = 0; i < length; i++) {
      result.append(array[i]);
      result.append(delim);
    }
    result.append(array[length]);
    return result.toString();
  }
}