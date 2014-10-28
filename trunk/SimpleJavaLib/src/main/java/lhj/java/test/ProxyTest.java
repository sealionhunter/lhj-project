/*
 * $Id: ProxyTest.java,v 1.1 2007/08/25 18:19:10 ostore Exp $
 *
 * Copyright (c) 2005-2007 Sun Japan Corporation. All Rights Reserved.
 */
package lhj.java.test;

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
public class ProxyTest implements IProxyTest {

	/**
	 * @see lhj.java.test.IProxyTest#proxyTest()
	 */
	public void proxyTest() {
		System.out.println("This is a Proxy test!");
	}

	/**
	 * @see lhj.java.test.IProxyTest#proxyTest(java.lang.String)
	 */
	public void proxyTest(String value) {
		System.out.println("This is a Proxy test with " + value + "!");
	}

	/**
	 * @see lhj.java.test.IProxyTest#proxyTestString()
	 */
	public String proxyTestString() {
		return "Test Proxy!";
	}

}
