package bd.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONObject;

import bd.DBTools;

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
	
	
	/*SELECT UIC, COUNT( * ) AS count
	FROM SearchStations
	WHERE id_user_1 =27
	GROUP BY UIC
	ORDER BY count DESC 
	LIMIT 0 , 30
	*/
	public static JSONObject getMostSearch(String user){
		Connection co;
		Statement stm;
		String query;
		JSONObject json = new JSONObject();
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "select UIC,COUNT(*) AS count FROM SearchStations where id_user_1="+user+" GROUP BY UIC ORDER BY count DESC;";
			ResultSet res = stm.executeQuery(query);
			if(res.next()){
				json.put("station", res.getString("UIC"));
				json.put("num",res.getInt(2));
			}else{
				json.put("station","NA");
				json.put("num",0);
			}
			stm.close();
			co.close();
		}catch (Exception e) {
			System.err.print("Exception :");
			e.printStackTrace();		
		}
		return json;
	}	
}
