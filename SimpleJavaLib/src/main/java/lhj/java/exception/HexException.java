/*
 * Created on 2005-9-8
 *
 */
package lhj.java.exception;

/**
 * Hex Exception
 * 
 * @author Sealion Hunter
 * 
 */
public class HexException extends RuntimeException {
	/** */
	private static final long serialVersionUID = 5260410874982227231L;

	public HexException() {
		super();
	}

	public HexException(String msg) {
		super(msg);
	}

	public HexException(Throwable t) {
		super(t);
	}
}
