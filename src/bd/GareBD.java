package bd;

import java.sql.Connection;
import java.sql.Statement;

public class GareBD {

	
	public static boolean addFavGare(String token,String uic){
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "insert into GarFavor(token,UIC) VALUES ('"+token+"','"+uic+"');";
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
