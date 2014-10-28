package org.jr.swing.filter;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��12��
 * @author Cherami
 */

import java.io.*;

/**
 * Ŀ¼��������
 * @since  0.1
 */

public class DirectoryFilter
    extends javax.swing.filechooser.FileFilter {
  /**
   * �ж�ָ�����ļ��Ƿ���Ա����ܡ�
   * @param file ��Ҫ�жϵ��ļ�
   * @return �ļ���һ��Ŀ¼ʱ����true�����򷵻�false��
   * @since  0.1
   */
  public boolean accept(File file) {
    return file.isDirectory();
  }

  /**
   * ���ع������������ַ�����
   * @return �������������ַ�����Directories����
   * @since  0.1
   */
  public String getDescription() {
    return "Directories";
  }
}