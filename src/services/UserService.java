package services;

import org.json.JSONObject;

import bd.UserBD;

public class UserService {

	
	
	public static JSONObject getProfil(String key){
		if(key.isEmpty() || key.equals("")){
			return null;
		}else{
			return UserBD.getUser(key);
		}
	}
	
	public static JSONObject getProfilID(String id){
		if(id.isEmpty() || id.equals("")){
			return null;
		}else{
			return UserBD.getUserID(id);
		}
	}
}
