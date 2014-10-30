package services;

import org.json.JSONException;
import org.json.JSONObject;

import bd.sql.UserBD;

public class UserService {

	
	
	public static JSONObject getProfil(String key){
		if(key == null || key.isEmpty() || key.equals("")){
			return null;
		}else{
			return UserBD.getUser(key);
		}
	}
	
	public static JSONObject getProfilID(String id){
		if(id == null || id.isEmpty() || id.equals("")){
			return null;
		}else{
			return UserBD.getUserID(id);
		}
	}
	
	public static JSONObject test(String id){
		if(id == null || id.isEmpty() || id.equals("")){
			JSONObject json = new JSONObject();
			try {
				json.put("THIS", "IS SHIT");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return json;
		}else{
			return UserBD.bestComp(id);
		}
	}
}
