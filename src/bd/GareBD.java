package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.simple.JSONObject;

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

	public static boolean removeFavGare(String token, String uic) {
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "delete from GarFavor where token = '"+token+"and UIC = '"+uic+"';";
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

	public static JSONArray listArray(String user1) {
		JSONArray jArray = new JSONArray();
		Connection co;
		Statement stm;
		String query;
		try{
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "select * from GarFavor where Token = '"+user1+"';";
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("uic",rs.getString("UIC"));
				jArray.put(json);
			}
		}catch (Exception e) {
			System.err.print("Exception :");
			e.printStackTrace();
		}
		return jArray;
	}
}
