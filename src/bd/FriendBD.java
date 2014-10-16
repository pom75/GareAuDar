package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.simple.JSONObject;

public class FriendBD {


	public static boolean addFriend(int user1,int user2){
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "insert into Friends(id_user_1,id_user_2) VALUES ('"+user1+"','"+user2+"');";
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

	public static boolean removeFriend(int user1, int user2) {
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "delete from Friends where id_user_1 ='"+user1+"' and id_user_2 = '"+user2+"';";
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
	
	@SuppressWarnings("unchecked")
	public static JSONArray listFriend(int user1){
		JSONArray jArray = new JSONArray();
		Connection co;
		Statement stm;
		String query;
		ArrayList<Integer> ids = new ArrayList<Integer>();
		try{
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "select * from Friends where id_user_1 = '"+user1+"'or id_user_2 = '"+user1+"';";
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()){
				if(rs.getInt("id_user_1") == user1 ){
					ids.add(rs.getInt("id_user_2"));
				}else{
					ids.add(rs.getInt("id_user_1"));
				}
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
			}
		}catch (Exception e) {
			System.err.print("Exception :");
			e.printStackTrace();
		}
		return jArray;
	}
}
