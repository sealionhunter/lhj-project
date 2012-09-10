import java.sql.Connection;
import java.sql.DriverManager;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:jtds:sqlserver://localhost:1433/myhotel", "sa",
				"overseas");

	}

}
