package org.jr.net;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月4日
 * @author Cherami
 */

import java.net.*;

/**
 * IP地址相关的工具类，封装常用的IP工具方法。
 * @since  0.12
 */

public class IPUtil {
  /**
   * 非法IP地址常量。
   * @since  0.12
   */
  public static final String INVALID_IP = "0.0.0.0";
  /**
   * 未知主机名常量。
   * @since  0.12
   */
  public static final String UNKNOWN_HOST = "";
  /**
   * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
   */
  private IPUtil() {
  }

  /**
   * 根据主机名得到IP地址字符串。
   * @param hostName 要查找地址的主机名
   * @return 对应主机的IP地址，主机名未知或者非法时返回INVALID_IP。
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
   * 根据IP地址得到主机名。
   * @param ip 要查找主界面的IP地址
   * @return 对应IP的主机名，IP地址未知时返回UNKNOWN_HOST，IP地址未知也可能是网络问题造成的。
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