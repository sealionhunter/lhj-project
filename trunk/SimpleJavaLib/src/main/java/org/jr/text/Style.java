package org.jr.text;
/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月26日
 * @author Cherami
 */

/**
 * 文本风格的接口。
 * @since  0.5
 */

public interface Style {
  /**
   * 得到风格的开始部分。
   * @return 风格的开始部分
   */
  public String getBegin();
  /**
   * 得到风格的结尾部分。
   * @return 风格的结尾部分
   */
  public String getEnd();
  /**
   * 得到风格的全部内容。
   * @return 风格的全部内容
   */
  public String getStyle();
}