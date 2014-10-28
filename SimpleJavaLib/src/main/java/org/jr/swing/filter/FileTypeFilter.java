package org.jr.swing.filter;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��25��
 * @author Cherami
 */

import java.io.*;

import org.jr.util.*;

/**
 * ��չ���ļ���������
 * @since  0.4
 */

public class FileTypeFilter
    extends javax.swing.filechooser.FileFilter {
  String[] suffixList;
  String description;
  boolean caseSensitive = false;
  /**
   * ʹ��ָ������չ������һ��FileTypeFilter��
   * @param suffix ��չ��
   * @since  0.5
   */
  public FileTypeFilter(String suffix) {
    suffixList = new String[1];
    suffixList[0]=suffix;
  }
  /**
   * ʹ��ָ������չ�����鹹��һ��FileTypeFilter��
   * @param suffixList ��չ������
   * @since  0.4
   */
  public FileTypeFilter(String[] suffixList) {
    this.suffixList = suffixList;
  }

  /**
   * ʹ��ָ������չ�����鹹��һ��FileTypeFilter��
   * @param suffixList ��չ������
   * @param description �������������ı�
   * @since  0.4
   */
  public FileTypeFilter(String[] suffixList, String description) {
    this.suffixList = suffixList;
    this.description = description;
  }

  /**
   * ʹ��ָ������չ��������Ƿ��Сд���й���һ��FileTypeFilter��
   * @param suffixList ��չ������
   * @param caseSensitive �Ƿ��Сд����
   * @since  0.4
   */
  public FileTypeFilter(String[] suffixList, boolean caseSensitive) {
    this.suffixList = suffixList;
    this.caseSensitive = caseSensitive;
  }

  /**
   * ʹ��ָ������չ��������Ƿ��Сд���й���һ��FileTypeFilter��
   * @param suffixList ��չ������
   * @param caseSensitive �Ƿ��Сд����
   * @param description �������������ı�
   * @since  0.4
   */
  public FileTypeFilter(String[] suffixList, boolean caseSensitive,
                        String description) {
    this.suffixList = suffixList;
    this.caseSensitive = caseSensitive;
    this.description = description;
  }

  /**
   * �����Ƿ��Сд���С�
   * @param caseSensitive �Ƿ��Сд����
   * @since  0.4
   */
  public void setCaseSensitive(boolean caseSensitive) {
    this.caseSensitive = caseSensitive;
  }

  /**
   * �Ƿ��Сд����
   * @return ��Сд����ʱ����true�����򷵻�false
   * @since  0.4
   */
  public boolean getCaseSensitive() {
    return caseSensitive;
  }

  /**
   * �ж�ָ�����ļ��Ƿ���Ա����ܡ�
   * @param file ��Ҫ�жϵ��ļ�
   * @return ��չ������ָ����Ҫ��ʱ����true�����򷵻�false��
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
   * ���ع������������ַ�����
   * @return ������չ�����б�
   * @since  0.4
   */
  public String getDescription() {
    if (description != null) {
      return description;
    }
    return StringUtil.combineStringArray(suffixList, "/");
  }
}
