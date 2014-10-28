package bd;

import java.sql.Connection;
import java.sql.Statement;

public class StationBD {

	
	public static boolean addFavGare(String user,String uic){
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "insert into SearchStations (id_user_1,UIC) VALUES ('"+user+"','"+uic+"');";
			stm.executeUpdate(query);
			stm.close();
			co.close();
		} catch (Exception e) {
			System.err.print("Exception :");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
