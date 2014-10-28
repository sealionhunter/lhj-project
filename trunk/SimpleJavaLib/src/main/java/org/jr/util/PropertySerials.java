package org.jr.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月12日
 * @author Cherami
 */

import java.util.regex.*;

/**
 * 此类是属性序列的抽象。
 * 所谓的属性序列是指以一定的分隔符分隔的一个属性集合字符串。
 * 这个类和本包中的另一个类的一个方法具有类似的左右，但是这个类因为可以使用规则表达式，
 * 因此功能要强大一些。
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
   * 构造方法，根据字符串生成一个属性序列类实例。
   * 分隔符使用缺省的'\u0000'。
   * @param source 属性序列的原始字符串
   * @since  0.1
   */
  public PropertySerials(String source) {
    splitPattern = new String("[" + splitChar + "]{1}");
    pattern = Pattern.compile(splitPattern);
    sourceString = source;
  }

  /**
   * 构造方法，根据字符串和分隔符生成一个属性序列类实例。
   * @param source 属性序列的原始字符串
   * @param splitChar 分隔符
   * @since  0.1
   */
  public PropertySerials(String source, char splitChar) {
    this.splitChar = splitChar;
    splitPattern = new String("[" + splitChar + "]{1}");
    pattern = Pattern.compile(splitPattern);
    sourceString = source;
  }

  /**
   * 将属性序列字符串分解得到属性数组。
   * @return 分解后的属性数组。如果原属性序列字符串为null则返回null。
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
   * 设置分隔符。
   * @param split 分隔符
   * @since  0.1
   */
  public void setSplitChar(char split) {
    splitChar = split;
    splitPattern = new String("[" + splitChar + "]{1}");
    pattern = Pattern.compile(splitPattern);
  }

  /**
   * 得到使用的分隔符。
   * @return 使用的分隔符
   * @deprecated  本方法不赞成使用，因为在一般情况下推荐使用规则表达式模式而不是单独的字符分隔符。
   *              并且在直接使用规则表达式模式生成类实例或者调用setSplitPattern方法后本方法的返回值没有意义。
   * @since  0.1
   */
  public char getSplitChar() {
    return splitChar;
  }

  /**
   * 设置分隔模式。
   * @param splitPattern 分隔模式，具体的模式规则请参考java.util.regex.Pattern类的说明。
   * @since  0.1
   */
  public void setSplitPattern(String splitPattern) {
    if ( (splitPattern != null) && (!splitPattern.equals(this.splitPattern))) {
      this.splitPattern = splitPattern;
      pattern = Pattern.compile(splitPattern);
    }
  }

  /**
   * 得到使用的分隔模式。
   * @return 使用的分隔模式
   * @since  0.1
   */
  public String getSplitPattern() {
    return splitPattern;
  }

  /**
   * 返回类实例的字符串表示。
   * @return 类实例的字符串表示
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