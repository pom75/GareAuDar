package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONObject;

public class GareBD {

	
	public static boolean addFavGare(String user,String uic){
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "insert into GarFavor (id_u,UIC) VALUES ('"+user+"','"+uic+"');";
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

	public static boolean removeFavGare(String user, String uic) {
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "delete from GarFavor where id_u = '"+user+"' and UIC = '"+uic+"';";
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

	public static JSONObject listArray(String user1) {
		JSONArray jArray = new JSONArray();
		JSONObject rep = new JSONObject();
		Connection co;
		Statement stm;
		String query;
		try{
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "select * from GarFavor where id_u = '"+user1+"';";
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("uic",rs.getString("UIC"));
				jArray.put(json);
			}
			rep.put("list", jArray);
		}catch (Exception e) {
			System.err.print("Exception :");
			e.printStackTrace();
		}
		return rep;
	}
}
