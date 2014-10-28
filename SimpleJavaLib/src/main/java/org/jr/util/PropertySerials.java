package org.jr.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��12��
 * @author Cherami
 */

import java.util.regex.*;

/**
 * �������������еĳ���
 * ��ν������������ָ��һ���ķָ����ָ���һ�����Լ����ַ�����
 * �����ͱ����е���һ�����һ�������������Ƶ����ң������������Ϊ����ʹ�ù�����ʽ��
 * ��˹���Ҫǿ��һЩ��
 * @see StringUtil#split(String source, String delim) split
 * @since  0.1
 */

public class PropertySerials {
  private char splitChar = '\u0000';
  protected String sourceString;
  protected String result[];
  protected String splitPattern;
  protected Pattern pattern;
  /**
   * ���췽���������ַ�������һ������������ʵ����
   * �ָ���ʹ��ȱʡ��'\u0000'��
   * @param source �������е�ԭʼ�ַ���
   * @since  0.1
   */
  public PropertySerials(String source) {
    splitPattern = new String("[" + splitChar + "]{1}");
    pattern = Pattern.compile(splitPattern);
    sourceString = source;
  }

  /**
   * ���췽���������ַ����ͷָ�������һ������������ʵ����
   * @param source �������е�ԭʼ�ַ���
   * @param splitChar �ָ���
   * @since  0.1
   */
  public PropertySerials(String source, char splitChar) {
    this.splitChar = splitChar;
    splitPattern = new String("[" + splitChar + "]{1}");
    pattern = Pattern.compile(splitPattern);
    sourceString = source;
  }

  /**
   * �����������ַ����ֽ�õ��������顣
   * @return �ֽ����������顣���ԭ���������ַ���Ϊnull�򷵻�null��
   * @since  0.1
   */
  public String[] getSerials() {
    if (sourceString == null) {
      return null;
    }
    result = pattern.split(sourceString);
    return result;
  }

  /**
   * ���÷ָ�����
   * @param split �ָ���
   * @since  0.1
   */
  public void setSplitChar(char split) {
    splitChar = split;
    splitPattern = new String("[" + splitChar + "]{1}");
    pattern = Pattern.compile(splitPattern);
  }

  /**
   * �õ�ʹ�õķָ�����
   * @return ʹ�õķָ���
   * @deprecated  ���������޳�ʹ�ã���Ϊ��һ��������Ƽ�ʹ�ù�����ʽģʽ�����ǵ������ַ��ָ�����
   *              ������ֱ��ʹ�ù�����ʽģʽ������ʵ�����ߵ���setSplitPattern�����󱾷����ķ���ֵû�����塣
   * @since  0.1
   */
  public char getSplitChar() {
    return splitChar;
  }

  /**
   * ���÷ָ�ģʽ��
   * @param splitPattern �ָ�ģʽ�������ģʽ������ο�java.util.regex.Pattern���˵����
   * @since  0.1
   */
  public void setSplitPattern(String splitPattern) {
    if ( (splitPattern != null) && (!splitPattern.equals(this.splitPattern))) {
      this.splitPattern = splitPattern;
      pattern = Pattern.compile(splitPattern);
    }
  }

  /**
   * �õ�ʹ�õķָ�ģʽ��
   * @return ʹ�õķָ�ģʽ
   * @since  0.1
   */
  public String getSplitPattern() {
    return splitPattern;
  }

  /**
   * ������ʵ�����ַ�����ʾ��
   * @return ��ʵ�����ַ�����ʾ
   * @since  0.1
   */
  public String toString() {
    StringBuffer temp = new StringBuffer();
    for (int i = 0; i < result.length - 1; i++) {
      temp.append(result[i]);
      temp.append(splitChar);
    }
    temp.append(result[result.length - 1]);
    return temp.toString();
  }

}