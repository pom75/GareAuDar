package bd.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import bd.DBTools;
import static bd.DBConfig.TABLE_FAVORITES;

//TODO: merge maybe
public class FavoriteBD {
	
	public static boolean addFavoriteStation(String user,String uic){
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "INSERT INTO " + TABLE_FAVORITES + " (id_u,UIC) VALUES ('"+user+"','"+uic+"');";
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

	public static boolean removeFavoriteStation(String user, String uic) {
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "DELETE FROM " + TABLE_FAVORITES + " WHERE id_u = '"+user+"' AND UIC = '"+uic+"';";
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

	public static JSONObject listFavoriteStations(String user) {
		JSONArray jArray = new JSONArray();
		JSONObject rep = new JSONObject();
		Connection co;
		Statement stm;
		String query;
		try{
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "SELECT * FROM " + TABLE_FAVORITES + " WHERE id_u = '"+user+"';";
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
