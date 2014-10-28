/*
 * Created on 2005-9-22
 * 
 */
package lhj.java.exception;

/**
 * Binary Exception
 * 
 * @author hjliang
 * 
 */
public class BinaryException extends RuntimeException {
	/** */
	private static final long serialVersionUID = 8821649824307200490L;

	public BinaryException() {
		super();
	}

	public BinaryException(String msg) {
		super(msg);
	}

	public BinaryException(Throwable t) {
		super(t);
	}

}
