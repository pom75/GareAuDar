package bd.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;

import bd.DBConfig;
import bd.DBTools;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class UserBD {


	
	public static boolean myKey(String id , String key){
		Connection con = null;
		Statement stm = null;
		ResultSet rep;

		try {
			con = DBTools.getMySQLConnection();
			stm = con.createStatement();

			rep = stm.executeQuery("Select * from " + DBConfig.TABLE_USER + " where id_user = '"+id+"' and token = '"+key+"';");
			boolean a = rep.next() != false;
			
			
			stm.close();
			con.close();
			return a;

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public static boolean upKey(String id , String key){
		Connection con = null;
		Statement stm = null;

		try {
			con = DBTools.getMySQLConnection();
			stm = con.createStatement();


			String query = "UPDATE "+ DBConfig.TABLE_USER +" SET token = ?  WHERE id_fb = '"+id+"' ;";

			PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
			preparedStmt.setString(1, key);

			// execute the java preparedstatement
			preparedStmt.executeUpdate();

			stm.close();
			con.close();
			return true;

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public static boolean userExist(String id_fb) {
		Connection con = null;
		Statement stm = null;
		ResultSet rep;

		try {
			//Connexion a la bd 
			con = DBTools.getMySQLConnection();
			stm = con.createStatement();

			//On selection le login entrer , dansla table User
			rep = stm.executeQuery("SELECT * FROM " + DBConfig.TABLE_USER + " WHERE id_fb='" + id_fb + "'");

			//On teste si le resultat est null
			boolean a = rep.next() != false;
			
			
			stm.close();
			con.close();
			return a;

		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	public static boolean addUser(String id_fb, String token, String email, String first_name, String gender, String last_name, String link, String locale, String name, String timezone) {
		Connection co;
		Statement stm;
		String query;

		try {
			//Connexion a la base
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();

			//Recuperation de l'heur actuel
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			//Insertion dans la bd
			query = "INSERT INTO " + DBConfig.TABLE_USER + " (id_user,token,id_fb,email,first_name,gender,last_name,link,locale,name,timezone) VALUES" + " (NULL ,'" + token + "','" + id_fb + "','" + email + "','" + first_name + "','" + gender + "' , '" + last_name + "' , '" + link + "' , '" + locale + "' ,'" + name + "' , '" + timezone + "');";
			stm.executeUpdate(query);


			//On coupe la connexion
			stm.close();
			co.close();

			//Si une exeption est leve l'insersion na pas pu se faire , on revois false
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.err.print("utilisateur déja cré :");
			return false;
		} catch (Exception e) {
			System.err.print("Exception :");
			return false;
		}
		//Si tous ses bien passer on retrun true
		return true;
	}

	public static JSONObject getUser(String key) {
		Connection co;
		Statement stm;
		String query;
		JSONObject json = new JSONObject();
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "Select * from "+ DBConfig.TABLE_USER+" where token = '"+key+"';";
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()){
				json.put("id", rs.getInt("id_user"));
				json.put("id_fb",rs.getString("id_fb"));
				json.put("first_name",rs.getString("first_name"));
				json.put("last_name",rs.getString("last_name"));
			}

		}catch (Exception e) {
			System.err.print("AAAAAAAAAAAAAAException :");
			e.printStackTrace();
		}	
		return json;
	}

	public static JSONObject getUserID(String id) {
		Connection co;
		Statement stm;
		String query;
		JSONObject json = new JSONObject();
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "Select * from "+ DBConfig.TABLE_USER+"  where id_user = '"+id+"';";
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()){
				json.put("id", rs.getInt("id_user"));
				json.put("id_fb",rs.getString("id_fb"));
				json.put("first_name",rs.getString("first_name"));
				json.put("last_name",rs.getString("last_name"));
			}

		}catch (Exception e) {
			System.err.print("Exception :");
			e.printStackTrace();
		}	
		return json;
	}
}