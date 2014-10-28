package org.jr.swing.filter;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月25日
 * @author Cherami
 */

import java.io.*;

import org.jr.util.*;

/**
 * 扩展名文件过滤器。
 * @since  0.4
 */

public class FileTypeFilter
    extends javax.swing.filechooser.FileFilter {
  String[] suffixList;
  String description;
  boolean caseSensitive = false;
  /**
   * 使用指定的扩展名构造一个FileTypeFilter。
   * @param suffix 扩展名
   * @since  0.5
   */
  public FileTypeFilter(String suffix) {
    suffixList = new String[1];
    suffixList[0]=suffix;
  }
  /**
   * 使用指定的扩展名数组构造一个FileTypeFilter。
   * @param suffixList 扩展名数组
   * @since  0.4
   */
  public FileTypeFilter(String[] suffixList) {
    this.suffixList = suffixList;
  }

  /**
   * 使用指定的扩展名数组构造一个FileTypeFilter。
   * @param suffixList 扩展名数组
   * @param description 过滤器的描述文本
   * @since  0.4
   */
  public FileTypeFilter(String[] suffixList, String description) {
    this.suffixList = suffixList;
    this.description = description;
  }

  /**
   * 使用指定的扩展名数组和是否大小写敏感构造一个FileTypeFilter。
   * @param suffixList 扩展名数组
   * @param caseSensitive 是否大小写敏感
   * @since  0.4
   */
  public FileTypeFilter(String[] suffixList, boolean caseSensitive) {
    this.suffixList = suffixList;
    this.caseSensitive = caseSensitive;
  }

  /**
   * 使用指定的扩展名数组和是否大小写敏感构造一个FileTypeFilter。
   * @param suffixList 扩展名数组
   * @param caseSensitive 是否大小写敏感
   * @param description 过滤器的描述文本
   * @since  0.4
   */
  public FileTypeFilter(String[] suffixList, boolean caseSensitive,
                        String description) {
    this.suffixList = suffixList;
    this.caseSensitive = caseSensitive;
    this.description = description;
  }

  /**
   * 设置是否大小写敏感。
   * @param caseSensitive 是否大小写敏感
   * @since  0.4
   */
  public void setCaseSensitive(boolean caseSensitive) {
    this.caseSensitive = caseSensitive;
  }

  /**
   * 是否大小写敏感
   * @return 大小写敏感时返回true，否则返回false
   * @since  0.4
   */
  public boolean getCaseSensitive() {
    return caseSensitive;
  }

  /**
   * 判断指定的文件是否可以被接受。
   * @param file 需要判断的文件
   * @return 扩展名符合指定的要求时返回true，否则返回false。
   * @since  0.4
   */
  public boolean accept(File file) {
    String filename = file.getName();
    int dot = filename.lastIndexOf(".");
    if (dot == -1) {
      return false;
    }
    return StringUtil.contains(suffixList, filename.substring(dot + 1),
                               caseSensitive);
  }

  /**
   * 返回过滤器的描述字符串。
   * @return 所有扩展名的列表
   * @since  0.4
   */
  public String getDescription() {
    if (description != null) {
      return description;
    }
    return StringUtil.combineStringArray(suffixList, "/");
  }
}
