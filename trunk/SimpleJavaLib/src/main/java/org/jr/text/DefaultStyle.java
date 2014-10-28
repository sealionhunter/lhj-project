package org.jr.text;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月26日
 * @author Cherami
 */

/**
 * 风格的缺省实现类。
 * @since  0.5
 */

public class DefaultStyle
    implements Style {
  protected String begin;
  protected String end;
  /**
   * 构造一个风格为空的DefaultStyle。
   * @since  0.5
   */
  public DefaultStyle() {
    begin="";
    end="";
  }

  /**
   * 以指定的开始结束风格构造一个DefaultStyle。
   * @param begin 风格的开始部分
   * @param end 风格的结束部分
   * @since  0.5
   */
  public DefaultStyle(String begin, String end) {
    if (begin != null) {
      this.begin = begin;
    }
    else {
      this.begin = "";
    }
    if (end != null) {
      this.end = end;
    }
    else {
      this.end = "";
    }
  }

  /**
   * 得到风格的开始部分。
   * @return 风格的开始部分
   * @since  0.5
   */
  public String getBegin() {
    return begin;
  }
  /**
   * 设置风格的开始部分。
   * @param begin 风格的开始部分
   * @since  0.5
   */
  public void setBegin(String begin) {
    if (begin != null) {
      this.begin = begin;
    }
    else {
      this.begin = "";
    }
  }

  /**
   * 得到风格的结尾部分。
   * @return 风格的结尾部分
   * @since  0.5
   */
  public String getEnd() {
    return end;
  }
  /**
   * 设置风格的结束部分。
   * @param end 风格的结束部分
   * @since  0.5
   */
  public void setEnd(String end) {
    if (end != null) {
      this.end = end;
    }
    else {
      this.end = "";
    }
  }

  /**
   * 得到风格的全部内容。
   * @return 风格的全部内容
   * @since  0.5
   */
  public String getStyle() {
    return begin + end;
  }

  /**
   * 类的字符串表示。
   * @return 字符串表示
   * @since  0.5
   */
  public String toString() {
    return begin + end;
  }
}
