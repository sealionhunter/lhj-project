package lhj.java.jkrx.exception;

import lhj.java.jkrx.resource.JKRXMessage;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * 
 * <p>
 * Company: EBT
 * </p>
 * 
 * @author HJLIANG
 * @version 1.0
 */
public class JKRXException extends Exception {
	/** */
	private static final long serialVersionUID = -4345572148400416281L;
	private JKRXMessage msg;

	public JKRXException() {
	}

	public JKRXException(Throwable t) {
		super(t);
	}

	public JKRXException(JKRXMessage msg) {
		super();
	}

	/**
	 * Returns the detail message string of this throwable.
	 * 
	 * @return the detail message string of this <tt>Throwable</tt> instance
	 *         (which may be <tt>null</tt>).
	 * @todo Implement this java.lang.Throwable method
	 */
	public String getMessage() {
		if (msg != null) {
			return msg.getMessage();
		}
		return super.getMessage();
	}

}
