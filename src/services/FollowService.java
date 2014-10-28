package services;

import org.json.JSONArray;
import org.json.JSONObject;

import bd.sql.FollowBD;
import bd.sql.UserBD;

public class FollowService {

	
	public static boolean addFollow(String user1,String user2, String key){
		if( !UserBD.myKey(user1, key)){
			return false;
		}else{
			return FollowBD.addFollow(user1,user2);
		}
	}

	public static boolean removeFollow(String key, String user1, String user2) {
		if(key == null || user1 == null || user2 == null || !UserBD.myKey(user1, key) || user1.isEmpty() || user1.equals("") || user2.isEmpty() || user2.equals("")){
			return false;
		}else{
			return FollowBD.removeFollow(Integer.valueOf(user1),Integer.valueOf(user2));
		}
	}

	public static JSONObject getFollow(String user1, String user2) {
		if(user1 == null || user1.isEmpty() || user1.equals("")){
			if(user2 == null || user2.isEmpty() || user2.equals("")){
				return null;
			}else{
				return FollowBD.listFollowInverse(Integer.valueOf(user2));
			}
		}else{
			return FollowBD.listFollow(Integer.valueOf(user1));
		}
	}
}
