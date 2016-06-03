/*
 * Created on 2005-9-16
 */
package lhj.java.exception;

/**
 * @author Sealion Hunter
 */
public class LhjException extends Exception {
	/** */
	private static final long serialVersionUID = 2477858319445025096L;

	public LhjException() {
		super();
	}

	public LhjException(String msg) {
		super(msg);
	}

	public LhjException(Throwable t) {
		super(t);
	}
}
