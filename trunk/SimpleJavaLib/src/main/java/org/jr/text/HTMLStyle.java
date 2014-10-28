package org.jr.text;
/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月26日
 * @author Cherami
 */

/**
 * HTML风格的实现类。
 * @since  0.5
 */

public class HTMLStyle extends DefaultStyle{
  /**
   * 构造一个空的HTMLStyle。
   * 其风格为""。
   */
  public HTMLStyle() {
    super();
  }
  /**
   * 以指定的开始结束风格构造一个HTMLStyle。
   * @param begin 风格的开始部分
   * @param end 风格的结束部分
   * @since  0.5
   */
  public HTMLStyle(String begin,String end) {
    super(begin,end);
    if (begin!=null) {
      this.begin = begin;
    }
    else {
      this.begin="";
    }
    if (end!=null) {
      this.end = end;
    }
    else {
      this.end="";
    }
  }

}