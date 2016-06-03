package org.jr;
/**
 * <p>Copyright: Copyright (c) 2002-2003</p>
 * <p>Company: JavaResearch(http://www.javaresearch.org)</p>
 * <p>最后更新日期:2003年3月26日
 * @author Cherami
 */

/**
 * 非法操作异常。
 * 一般在需要很多参数但是有些需要的参数的值没有设置时就调用需要那些参数的方法时抛出此异常。 
 * 这是一个运行时异常，不用进行捕获。
 * 
 * @since 0.5
 */

public class IllegalOperationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2051771842731814909L;

	/**
	 * 构造一个IllegalOperationException。
	 * 
	 * @since 0.5
	 */
	public IllegalOperationException() {
		super("You can not invoke the method in current status!");
	}

	/**
	 * 以指定的信息构造一个IllegalOperationException。
	 * 
	 * @param message
	 *            错误信息。
	 * @since 0.5
	 */
	public IllegalOperationException(String message) {
		super(message);
	}
}