package services;

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
}
