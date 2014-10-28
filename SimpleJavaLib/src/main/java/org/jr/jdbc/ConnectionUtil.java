package org.jr.jdbc;

/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��2��19��
 * @author Cherami
 */

/**
 * ��ȡ�������ݿ����ӵ��ַ����Ĺ����ࡣ
 * @since  0.4
 */

public class ConnectionUtil {
  /**
   * ˽�й��췽������ֹ���ʵ��������Ϊ�����಻��Ҫʵ������
   */
  private ConnectionUtil() {
  }

  /**
   * ���ݲ���ֵ����һ��Mysql�����ݿ�����������ַ�����
   * @param host ��������Ҳ������IP��ַ��
   * @param database ���ݿ���
   * @param userName �û���
   * @param password ����
   * @param options ����ѡ�����ѡ��ʱ��ֵ��"option1=value1"����ʽ�������ʱ��ʹ��"&"���ӣ�����"option1=value1&option2=value2"��
   * @return Mysql�����ݿ�����������ַ���
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
   * ���ݲ���ֵ����һ��Sysbase�����ݿ�����������ַ�����
   * @param tds tds��
   * @param host ������
   * @param port �˿ں�
   * @return Sysbase�����ݿ�����������ַ���
   * @since  0.4
   */
  public static String getSysbaseConnection(String tds, String host,
                                            String port) {
    return "jdbc:sybase:" + tds + ":" + host + ":" + port;
  }

  /**
   * ���ݲ���ֵ����һ��Oracle��thin��ʽ�����ݿ�����������ַ�����
   * @param serverName ������
   * @param port �˿ں�
   * @param userName �û���
   * @param password ����
   * @return Oracle��thin��ʽ�����ݿ�����������ַ���
   * @since  0.4
   */
  public static String getOracleThinConnection(String serverName, String port,
                                               String userName, String password) {
    return "jdbc:oracle:thin:@" + serverName + ":" + port + ":ORCL?user=" +
        userName + ";password=tiger";
  }

  /**
   * ���ݲ���ֵ����һ��Oracle��oci8��ʽ�����ݿ�����������ַ�����
   * @param localeServerName Net8�ı��ط�����
   * @return Oracle��oci8��ʽ�����ݿ�����������ַ���
   * @since  0.4
   */
  public static String getOracleOCI8Connection(String localeServerName) {
    return "jdbc:oracle:oci8:@" + localeServerName;
  }
  
  /**
   * ���ݲ���ֵ����һ��SQL Server�����ݿ�����������ַ���
   * @param host ��������Ҳ������IP��ַ��
   * @param instance ���ݿ�ʵ��
   * @param port �˿ں�
   * @param dbName ���ݿ���
   * @param userName �û���
   * @param password ����
   * @param options ����ѡ�����ѡ��ʱ��ֵ��"option1=value1"����ʽ�������ʱ��ʹ��"&"���ӣ�����"option1=value1&option2=value2"��
   * @return SQL Server�����ݿ�����������ַ���
   */
  public static String getSqlServerConnection(String host, String port, String dbName) {
	  return "jdbc:sqlserver://" + host + ":" + port + ";DatabaseName=" + dbName;
  }
}