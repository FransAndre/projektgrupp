import java.sql.*;
import javax.swing.*;

public class dbConnection {
	Connection conn = null;

	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:C:\\Users\\pip\\Dropbox\\Dev\\Workspace\\projektgrupp\\eva\\lib\\eva.sqlite");
			System.out.println("CONNECTED");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
