package lhj.java.jkrx.tracsaction;

import java.sql.Connection;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * 
 * <p>
 * Company: JKRX
 * </p>
 * 
 * @author HJLIANG
 * @version 1.0
 */
public class JdbcConnectionFactory {
	JdbcConnectionFactory() {
	}

	public static Connection getConnection() throws Exception {
		return getDataSourceConnection();
	}

	static Connection getDataSourceConnection() throws Exception {
		return null;
	}

	static Connection getRawConnection() throws Exception {
		return null;
	}
}
