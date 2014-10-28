package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import static bd.DBConfig.*;

public class DBTools {

	private DataSource dataSource;

	public DBTools(String jndiname) throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dataSource = (DataSource) new InitialContext()
					.lookup("java:comp/env/" + jndiname);
		} catch (NamingException e) {
			// Handle error that it's not configured in JNDI.
			throw new SQLException(jndiname + " is missing in JNDI! : "
					+ e.getMessage());
		}
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static Connection getMySQLConnection() throws SQLException,
			ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		
		if (MYSQL_POOLING == false) {
			return (DriverManager.getConnection("jdbc:mysql://" + MYSQL_HOST
					+ "/" + MYSQL_DB, MYSQL_USERNAME, MYSQL_PASSWORD));
		} else {
			DBTools database = new DBTools("jdbc/db");
			return (database.getConnection());
		}
	}
}
