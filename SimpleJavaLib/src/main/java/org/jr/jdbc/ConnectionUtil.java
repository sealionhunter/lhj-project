package org.jr.jdbc;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年2月19日
 * @author Cherami
 */

/**
 * 获取各种数据库连接的字符串的工具类。
 * @since  0.4
 */

public class ConnectionUtil {
  /**
   * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
   */
  private ConnectionUtil() {
  }

  /**
   * 根据参数值构造一个Mysql的数据库连接所需的字符串。
   * @param host 主机名，也可以是IP地址。
   * @param database 数据库名
   * @param userName 用户名
   * @param password 密码
   * @param options 其他选项，单个选项时的值是"option1=value1"的形式，多个的时候使用"&"连接，例如"option1=value1&option2=value2"。
   * @return Mysql的数据库连接所需的字符串
   * @since  0.4
   */
  public static String getMysqlConnection(String host, String database,
                                          String userName, String password,
                                          String options) {
    if (options != null && options.length() > 0) {
      return "jdbc:mysql://" + host + "/" + database + "?user=" + userName +
          "&password=" + password + "&+" + options;
    }
    else {
      return "jdbc:mysql://" + host + "/" + database + "?user=" + userName +
          "&password=" + password;
    }
  }

  /**
   * 根据参数值构造一个Sysbase的数据库连接所需的字符串。
   * @param tds tds名
   * @param host 主机名
   * @param port 端口号
   * @return Sysbase的数据库连接所需的字符串
   * @since  0.4
   */
  public static String getSysbaseConnection(String tds, String host,
                                            String port) {
    return "jdbc:sybase:" + tds + ":" + host + ":" + port;
  }

  /**
   * 根据参数值构造一个Oracle的thin方式的数据库连接所需的字符串。
   * @param serverName 服务名
   * @param port 端口号
   * @param userName 用户名
   * @param password 密码
   * @return Oracle的thin方式的数据库连接所需的字符串
   * @since  0.4
   */
  public static String getOracleThinConnection(String serverName, String port,
                                               String userName, String password) {
    return "jdbc:oracle:thin:@" + serverName + ":" + port + ":ORCL?user=" +
        userName + ";password=tiger";
  }

  /**
   * 根据参数值构造一个Oracle的oci8方式的数据库连接所需的字符串。
   * @param localeServerName Net8的本地服务名
   * @return Oracle的oci8方式的数据库连接所需的字符串
   * @since  0.4
   */
  public static String getOracleOCI8Connection(String localeServerName) {
    return "jdbc:oracle:oci8:@" + localeServerName;
  }
  
  /**
   * 根据参数值构造一个SQL Server的数据库连接所需的字符串
   * @param host 主机名，也可以是IP地址。
   * @param instance 数据库实例
   * @param port 端口号
   * @param dbName 数据库名
   * @param userName 用户名
   * @param password 密码
   * @param options 其他选项，单个选项时的值是"option1=value1"的形式，多个的时候使用"&"连接，例如"option1=value1&option2=value2"。
   * @return SQL Server的数据库连接所需的字符串
   */
  public static String getSqlServerConnection(String host, String port, String dbName) {
	  return "jdbc:sqlserver://" + host + ":" + port + ";DatabaseName=" + dbName;
  }
}