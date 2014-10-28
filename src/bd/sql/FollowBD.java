package bd.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import bd.DBStatic;
import bd.DBTools;

public class FollowBD {
	public static boolean addFollow(String id1, String id2) {
		Connection co;
		Statement stm;
		String query;

		try {
			//Connexion a la base
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();


			//Insertion dans la bd
			query = "INSERT INTO " + DBStatic.TABLE_FRIENDS + " (id,id_user_1,id_user_2) VALUES" + " (NULL ,'" + id1 + "','" + id2 + "');";
			//TODO: maybe, use prepare statement.
			stm.executeUpdate(query);


			//On coupe la connexion
			stm.close();
			co.close();

			//Si une exeption est leve l'insersion na pas pu se faire , on revois false
		} catch (Exception e) {
			//System.err.print("Exception :");
			//e.printStackTrace();
			return false;
		}
		//Si tous ses bien passer on retrun true
		return true;

	}


	public static boolean addFriend(int user1,int user2){
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "INSERT INTO " + DBStatic.TABLE_FRIENDS + " (id_user_1,id_user_2) VALUES ('"+user1+"','"+user2+"');";
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

	public static boolean removeFollow(int user1, int user2) {
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "DELETE FROM" + DBStatic.TABLE_FRIENDS + " where id_user_1 ='"+user1+"' and id_user_2 = '"+user2+"';";
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


	public static JSONObject listFollow(int user1){
		JSONArray jArray = new JSONArray();
		JSONObject rep = new JSONObject();
		Connection co;
		Statement stm;
		String query;
		ArrayList<Integer> ids = new ArrayList<Integer>();
		try{
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "select * from " + DBStatic.TABLE_FRIENDS + " where id_user_2 = '"+user1+"';";
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()){
					ids.add(rs.getInt("id_user_1"));
				}	
			for(int i = 0 ; i < ids.size() ; i++){
				query = "select * from USER_FACEBOOK where id_user = '"+ids.get(i)+"';";
				rs = stm.executeQuery(query);
				rs.next();
				JSONObject json = new JSONObject();
				json.put("id_user", rs.getString("id_user"));
				json.put("id_fb",rs.getString("id_fb"));
				json.put("name",rs.getString("name"));
				jArray.put(json);
				
				rep.put("list", jArray);
			}
		}catch (Exception e) {
			System.err.print("Exception :");
			e.printStackTrace();
		}
		
		return rep;
	}
	
	public static JSONObject listFollowInverse(int user1){
		JSONArray jArray = new JSONArray();
		Connection co;
		Statement stm;
		String query;
		ArrayList<Integer> ids = new ArrayList<Integer>();
		JSONObject rep = new JSONObject();;
		try{
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "select * from Friends where id_user_1 = '"+user1+"';";
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()){
					ids.add(rs.getInt("id_user_2"));
				}	
			for(int i = 0 ; i < ids.size() ; i++){
				query = "select * from USER_FACEBOOK where id_user = '"+ids.get(i)+"';";
				rs = stm.executeQuery(query);
				rs.next();
				JSONObject json = new JSONObject();
				json.put("id_user", rs.getString("id_user"));
				json.put("id_fb",rs.getString("id_fb"));
				json.put("name",rs.getString("name"));
				jArray.put(json);
				
				rep.put("list", jArray);
			}
		}catch (Exception e) {
			System.err.print("Exception :");
			e.printStackTrace();
		}
		return rep;
	}
}
