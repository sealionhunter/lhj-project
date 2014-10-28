package org.jr.swing.filter;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��12��
 * @author Cherami
 */

import java.io.*;

/**
 * ѹ���ļ����͹�������
 * @since  0.1
 */

public class ZipFileFilter
    extends javax.swing.filechooser.FileFilter {
  /**
   * �ж�ָ�����ļ��Ƿ���Ա����ܡ�
   * @param file ��Ҫ�жϵ��ļ�
   * @return �ļ�����չ��Ϊzip��jar������һ��Ŀ¼ʱ����true�����򷵻�false��
   * @since  0.1
   */
  public boolean accept(File file) {
    if (file.getName().toLowerCase().endsWith(".zip") ||
        file.getName().toLowerCase().endsWith(".jar") || file.isDirectory()) {
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * ���ع������������ַ�����
   * @return �������������ַ�����*.zip,*.jar��
   * @since  0.1
   */
  public String getDescription() {
    return "*.zip,*.jar";
  }
}