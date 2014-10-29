package bd.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
			
			
			//On coupe la connexion
			stm.close();
			co.close();

			//Si une exeption est leve l'insersion na pas pu se faire , on revois false
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.err.print("utilisateur d��ja cr�� :");
			return false;
		} catch (Exception e) {
			System.err.print("Exception :");
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
			
			
		}catch (Exception e) {
			System.err.print("Exception :");
			e.printStackTrace();
		}
		
		return jArray;
		
	}

}
