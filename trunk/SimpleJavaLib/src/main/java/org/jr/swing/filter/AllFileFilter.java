package org.jr.swing.filter;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��12��
 * @author Cherami
 */

import java.io.*;

/**
 * ȫ���ļ���������
 * @since  0.1
 */

public class AllFileFilter
    extends javax.swing.filechooser.FileFilter {
  /**
   * �ж�ָ�����ļ��Ƿ���Ա����ܡ�
   * @param file ��Ҫ�жϵ��ļ�
   * @return ���κ����������true��
   * @since  0.1
   */
  public boolean accept(File file) {
    return true;
  }

  /**
   * ���ع������������ַ�����
   * @return �������������ַ�����All Files��
   * @since  0.1
   */
  public String getDescription() {
    return "All Files";
  }
}