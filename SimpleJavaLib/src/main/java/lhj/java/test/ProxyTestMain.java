/*
 * $Id: ProxyTestMain.java,v 1.1 2007/08/25 18:19:10 ostore Exp $
 *
 */
package lhj.java.test;

import java.lang.reflect.Proxy;

/**
 * 
 * @author Sealion Hunter
 * @version $Revision: 1.1 $ $Date: 2007/08/25 18:19:10 $
 */
public class ProxyTestMain {

	public static void main(String[] args) {
		IProxyTest test = (IProxyTest) Proxy.newProxyInstance(IProxyTest.class.getClassLoader(),
				new Class[] { IProxyTest.class }, new LhjInvocationHandler());
		test.proxyTest();
		test.proxyTest("Success!");
		System.out.print(test.proxyTestString());
	}
}
