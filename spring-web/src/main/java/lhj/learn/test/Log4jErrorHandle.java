package lhj.learn.test;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class Log4jErrorHandle  implements ErrorHandler {

	@Override
	public void activateOptions() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLogger(Logger logger) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(String message, Exception e, int errorCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(String message, Exception e, int errorCode, LoggingEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAppender(Appender appender) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBackupAppender(Appender appender) {
		// TODO Auto-generated method stub

	}

}
