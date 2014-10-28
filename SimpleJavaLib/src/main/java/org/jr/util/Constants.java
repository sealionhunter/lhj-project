package org.jr.util;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��28��
 * @author Cherami
 */

/**
 * �������ռ�Java����г��õĳ�����
 * ��������ĳ�����У���Ȼ������Math���е���Щ������
 * Ϊ�������ɴ����ʵ�������췽��������Ϊprivate���͵ġ�
 * @since  0.12
 */

public class Constants {
  /**
   * ���з���
   * <b>��������Ƶ�ʱ������final�����ǻᾭ��Ԥ�����Ż��ģ���˶���Ϊfinal������û�п�ƽ̨�������ˡ������0.5beta����ȡ����final������</b>
   * @since  0.12
   */
  public static String LINE_SEPARATOR = System.getProperty(
      "line.separator");
  /**
   * �ļ��ָ�����
   * <b>��������Ƶ�ʱ������final�����ǻᾭ��Ԥ�����Ż��ģ���˶���Ϊfinal������û�п�ƽ̨�������ˡ������0.5beta����ȡ����final������</b>
   * @since  0.12
   */
  public static String FILE_SEPARATOR = System.getProperty(
      "file.separator");
  /**
   * ·���ָ�����
   * <b>��������Ƶ�ʱ������final�����ǻᾭ��Ԥ�����Ż��ģ���˶���Ϊfinal������û�п�ƽ̨�������ˡ������0.5beta����ȡ����final������</b>
   * @since  0.12
   */
  public static String PATH_SEPARATOR = System.getProperty(
      "path.separator");
  /**
   * ˽�й��췽������ֹ���ʵ��������Ϊ�����಻��Ҫʵ������
   */
  private Constants() {
  }

}