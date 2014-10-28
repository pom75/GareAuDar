package services;

import org.json.JSONObject;

import bd.sql.StationBD;
import bd.sql.UserBD;

// total?
public class StationService {

	public static boolean addSearched(String token,String user,String uic){
		if(!UserBD.myKey(user, token)){
			return false;
		}else{
			return StationBD.addSearchGare(user, uic);
		}	
	}

	
	public static JSONObject getMostSearched(String user){
		if(user == null || user.isEmpty()){
			return new JSONObject();
		}else{
			return StationBD.getMostSearch(user);
		}
	}
}
