package lhj.learn.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4JTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(Log4JTest.class);
		Throwable e =  new Exception("This is a text exception : 192.168.200.248 ", new Exception("this is a text Exception : 192.168.200.53 "));
		log.error("test", e);
		return ;
	}

}
