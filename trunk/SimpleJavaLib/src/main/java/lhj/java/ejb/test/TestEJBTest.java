//package lhj.java.ejb.test;
//
//import lhj.java.ejb.*;
//import lhj.java.util.SP;
//
//import javax.naming.*;
//import javax.rmi.PortableRemoteObject;
//import java.lang.String;
//import java.util.Properties;
//
//public class TestEJBTest {
//	private static final String ERROR_NULL_REMOTE = "Remote interface reference is null.  It must be created by calling one of the Home interface methods first.";
//	private static final int MAX_OUTPUT_LINE_LENGTH = 100;
//	private boolean logging = true;
//	private EJBTest eJBTest = null;
//	private EJBTestHome eJBTestHome = null;
//
//	// Construct the EJB test client
//	public TestEJBTest() {
//		initialize();
//	}
//
//	public void initialize() {
//		long startTime = 0;
//		if (logging) {
//			log("Initializing bean access.");
//			startTime = System.currentTimeMillis();
//		}
//
//		try {
//
//			// get naming context
//			Context context = getInitialContext();
//			// look up jndi name
//			Object ref = context.lookup("EJBTest");
//			// look up jndi name and cast to Home interface
//			eJBTestHome = (EJBTestHome) PortableRemoteObject.narrow(ref,
//					EJBTestHome.class);
//			if (logging) {
//				log("Succeeded initializing bean access through Home interface.");
//				long endTime = System.currentTimeMillis();
//				log("Execution time: " + (endTime - startTime) + " ms.");
//			}
//
//		} catch (Exception e) {
//			if (logging) {
//				log("Failed initializing bean access.");
//			}
//			e.printStackTrace();
//		}
//
//	}
//
//	public Context getInitialContext() throws Exception {
//		String url = "t3://hjliang-note:7001";
//		String user = "weblogic";
//		String password = "weblogic";
//		Properties properties;
//		try {
//			properties = new Properties();
//			properties.put(Context.INITIAL_CONTEXT_FACTORY,
//					"weblogic.jndi.WLInitialContextFactory");
//			properties.put(Context.PROVIDER_URL, url);
//			if (user != null) {
//				properties.put(Context.SECURITY_PRINCIPAL, user);
//				properties.put(Context.SECURITY_CREDENTIALS,
//						password == null ? "" : password);
//			}
//			return new javax.naming.InitialContext(properties);
//		} catch (Exception e) {
//			log("Unable to connect to WebLogic server at " + url);
//			log("Please make sure that the server is running.");
//			throw e;
//		}
//	}
//
//	// ----------------------------------------------------------------------------
//	// Methods that use Home interface methods to generate a Remote interface
//	// reference
//	// ----------------------------------------------------------------------------
//
//	public EJBTest create() {
//		long startTime = 0;
//		if (logging) {
//			log("Calling create()");
//			startTime = System.currentTimeMillis();
//		}
//
//		try {
//			eJBTest = eJBTestHome.create();
//			if (logging) {
//				log("Succeeded: create()");
//				long endTime = System.currentTimeMillis();
//				log("Execution time: " + (endTime - startTime) + " ms.");
//			}
//		} catch (Exception e) {
//			if (logging) {
//				log("Failed : create()");
//			}
//			e.printStackTrace();
//		}
//		if (logging) {
//			log("Return value from create(): " + eJBTest + ".");
//		}
//		return eJBTest;
//	}
//
//	// ----------------------------------------------------------------------------
//	// Methods that use Remote interface methods to access data through the bean
//	// ----------------------------------------------------------------------------
//
//	public String converte(String value) {
//		String returnValue = null;
//		if (eJBTest == null) {
//			SP.println("Error in converte(): " + ERROR_NULL_REMOTE);
//			return returnValue;
//		}
//		long startTime = 0;
//		if (logging) {
//			log("Calling converte(" + value + ")");
//			startTime = System.currentTimeMillis();
//		}
//
//		try {
//			returnValue = eJBTest.converte(value);
//			if (logging) {
//				log("Succeeded: converte(" + value + ")");
//				long endTime = System.currentTimeMillis();
//				log("Execution time: " + (endTime - startTime) + " ms.");
//			}
//		} catch (Exception e) {
//			if (logging) {
//				log("Failed : converte(" + value + ")");
//			}
//			e.printStackTrace();
//		}
//		if (logging) {
//			log("Return value from converte(" + value + "): " + returnValue
//					+ ".");
//		}
//		return returnValue;
//	}
//
//	public void executeRemoteCallsWithDefaultArguments() {
//		if (eJBTest == null) {
//			SP.println("Error in executeRemoteCallsWithDefaultArguments(): "
//					+ ERROR_NULL_REMOTE);
//			return;
//		}
//
//		try {
//			converte("");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	// ----------------------------------------------------------------------------
//	// Utility Methods
//	// ----------------------------------------------------------------------------
//
//	private void log(String message) {
//
//		if (message == null) {
//			SP.println("-- null");
//		}
//		if (message.length() > MAX_OUTPUT_LINE_LENGTH) {
//			SP.println("-- " + message.substring(0, MAX_OUTPUT_LINE_LENGTH)
//					+ " ...");
//		} else {
//			SP.println("-- " + message);
//		}
//	}
//
//	// Main method
//	public static void main(String[] args) {
//		TestEJBTest client = new TestEJBTest();
//		client.create();
//		String retValue = client.converte("This is a session ejb test");
//		SP.println(retValue);
//		// Use the client object to call one of the Home interface wrappers
//		// above, to create a Remote interface reference to the bean.
//		// If the return value is of the Remote interface type, you can use it
//		// to access the remote interface methods. You can also just use the
//		// client object to call the Remote interface wrappers.
//	}
//}
