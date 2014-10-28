package org.jr;
/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>����������:2003��3��26��
 * @author Cherami
 */

/**
 * �Ƿ������쳣��
 * һ������Ҫ�ܶ����������Щ��Ҫ�Ĳ�����ֵû������ʱ�͵�����Ҫ��Щ�����ķ���ʱ�׳����쳣��
 * ����һ������ʱ�쳣�����ý��в���
 * @since  0.5
 */

public class IllegalOperationException extends RuntimeException {
  /**
	 * 
	 */
	private static final long serialVersionUID = -2051771842731814909L;
/**
   * ����һ��IllegalOperationException��
   * @since  0.5
   */
  public IllegalOperationException() {
    super("You can not invoke the method in current status!");
  }
  /**
   * ��ָ������Ϣ����һ��IllegalOperationException��
   * @param message ������Ϣ��
   * @since  0.5
   */
  public IllegalOperationException(String message) {
    super(message);
  }
}