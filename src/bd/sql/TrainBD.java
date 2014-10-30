package bd.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import bd.DBConfig;
import bd.DBTools;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class TrainBD {

	public static boolean addTrain( String user_id, String numT, String date, String numG, String term) {
		Connection co;
		Statement stm;
		String query;

		try {
			//Connexion a la base
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();


			//Insertion dans la bd
			query = "INSERT INTO  " + DBConfig.TABLE_TRAIN + " (user_id,numT,date,numG,term) VALUES" + " ( '" + user_id+ "' , '" + numT + "', '" + date + "' , '" + numG + "' , '" + term + "' );";
			stm.executeUpdate(query);
		//TODO: check if user id is string??
			
			//On coupe la connexion
			stm.close();
			co.close();

			//Si une exeption est leve l'insersion na pas pu se faire , on revois false
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.err.print("utilisateur déja crée :");
			return false;
		} catch (Exception e) {
			System.err.print("Exception :");
			return false;
		}
		//Si tous ses bien passer on retrun true
		return true;
		
	}
	
	public static boolean removeTrain(String user_id, String numT, String date) {
		Connection co;
		Statement stm;
		String query;

		try {
			//Connexion a la base
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();

			//Suppression dans la bd
			//TODO check
			query = "DELETE FROM  " + DBConfig.TABLE_TRAIN + " WHERE user_id ='"+user_id+"' AND numT = '"+numT +"' AND date = '"+date+"' ;";
			
			System.out.println(query);
			stm.executeUpdate(query);
		
			//On coupe la connexion
			stm.close();
			co.close();
	} catch (Exception e) {
			System.err.print("Exception :"); e.printStackTrace();
			return false;
		}
		//Si tous ses bien passer on retrun true
		return true;
	}

	
	

	public static JSONArray getTrainUser(String user_id) {
		JSONArray jArray = new JSONArray();
		Connection co;
		Statement stm;
		String query;
		try{
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "select * from " + DBConfig.TABLE_TRAIN + " where user_id = '"+user_id+"';";
			ResultSet rs = stm.executeQuery(query);
			
			
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("user_id", rs.getString("user_id"));
				json.put("numT", rs.getString("numT"));
				json.put("date",rs.getString("date"));
				json.put("numG",rs.getString("numG"));
				json.put("term",rs.getString("term"));
				jArray.put(json);
					
				}	
			
			stm.close();
			co.close();
			
		}catch (Exception e) {
			System.err.print("Exception :");
			e.printStackTrace();
		}
		
		return jArray;
		
	}

	
	/*
	 * SELECT COUNT( * ) 
FROM  `Train` 
WHERE user_id =1
	 */
	
	public static JSONObject getNbTrainTaken(String id){
		Connection co;
		Statement stm;
		String query;
		JSONObject json = new JSONObject();
		try{
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "select COUNT(*) from Train where user_id = '"+id+"'";
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()){
				json.put("nb", rs.getInt(1));
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
