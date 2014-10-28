/*
 * $Id: LhjInvocationHandler.java,v 1.1 2007/08/25 18:19:10 ostore Exp $
 *
 * Copyright (c) 2005-2007 Sun Japan Corporation. All Rights Reserved.
 */
package lhj.java.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>
 * [���@��] �N���X����
 * </p>
 * <p>
 * [�@�@�\] �N���X�̋@�\���L�q����B
 * </p>
 * 
 * @author hjliang
 * @version $Revision: 1.1 $ $Date: 2007/08/25 18:19:10 $
 */
public class LhjInvocationHandler implements InvocationHandler {

	/**
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 *      java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Class<?> test = Class.forName(getClassName(method));
		Method m = test.getMethod(method.getName(), method.getParameterTypes());
		return m.invoke(test.newInstance(), args);
	}

	private String getClassName(Method method) throws Exception {
		String className = null;
		String iName = method.getDeclaringClass().getName();
		int index = iName.lastIndexOf('.');
		if (index != -1) {
			className = iName.substring(0, index + 1);
			iName = iName.substring(index + 1);
		}
		if (method.getDeclaringClass().isInterface()) {
			if (iName.charAt(0) != 'I') {
				throw new Exception("Wrong Interface Name");
			}
			className += iName.substring(1);
		} else {
			className += iName;
		}
		return className;
	}

}
