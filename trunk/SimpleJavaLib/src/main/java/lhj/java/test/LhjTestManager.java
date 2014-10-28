/*
 * Created on 2005/09/24 *
 */
package lhj.java.test;

import lhj.java.exception.LhjException;

/**
 * @author sealion Created on 2005/09/24
 * 
 */
public class LhjTestManager {
	private static final LhjTestManager instance = new LhjTestManager();

	public static LhjTestManager getInstance() {
		return instance;
	}

	/**
	 * @param testorName
	 * @return
	 * @throws LhjException
	 */
	private ILhjTestor getTestor(String testorName) throws LhjException {
		try {
			Class<?> testorClazz = Class.forName(testorName);
			Object testor = testorClazz.newInstance();
			if (!(testor instanceof ILhjTestor)) {
				throw new LhjException(testorName + " is not a ILhjTestor!");
			}
			return (ILhjTestor) testor;
		} catch (ClassNotFoundException ex) {
			throw new LhjException(ex);
		} catch (InstantiationException ex) {
			throw new LhjException(ex);
		} catch (IllegalAccessException ex) {
			throw new LhjException(ex);
		}
	}

	public Object test(String testorName, Object[] args) throws LhjException {
		ILhjTestor testor = getTestor(testorName);
		return testor.test(args);
	}
}
