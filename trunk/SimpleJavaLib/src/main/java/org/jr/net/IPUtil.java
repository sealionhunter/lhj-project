package org.jr.net;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��4��
 * @author Cherami
 */

import java.net.*;

/**
 * IP��ַ��صĹ����࣬��װ���õ�IP���߷�����
 * @since  0.12
 */

public class IPUtil {
  /**
   * �Ƿ�IP��ַ������
   * @since  0.12
   */
  public static final String INVALID_IP = "0.0.0.0";
  /**
   * δ֪������������
   * @since  0.12
   */
  public static final String UNKNOWN_HOST = "";
  /**
   * ˽�й��췽������ֹ���ʵ��������Ϊ�����಻��Ҫʵ������
   */
  private IPUtil() {
  }

  /**
   * �����������õ�IP��ַ�ַ�����
   * @param hostName Ҫ���ҵ�ַ��������
   * @return ��Ӧ������IP��ַ��������δ֪���߷Ƿ�ʱ����INVALID_IP��
   * @since  0.12
   */
  public static String getByName(String hostName) {
    try {
      InetAddress inet = InetAddress.getByName(hostName);
      return inet.getHostAddress();
    }
    catch (UnknownHostException e) {
      return INVALID_IP;
    }
  }

  /**
   * ����IP��ַ�õ���������
   * @param ip Ҫ�����������IP��ַ
   * @return ��ӦIP����������IP��ַδ֪ʱ����UNKNOWN_HOST��IP��ַδ֪Ҳ����������������ɵġ�
   * @since  0.12
   */
  public static String getHostName(String ip) {
    try {
      InetAddress inet = InetAddress.getByName(ip);
      return inet.getHostName();
    }
    catch (UnknownHostException e) {
      return UNKNOWN_HOST;
    }
  }
}