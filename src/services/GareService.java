package services;

import org.json.JSONArray;
import org.json.JSONObject;

import bd.GareBD;
import bd.UserBD;

public class GareService {

	
	public static boolean addFavor(String token,String user,String uic){
		if(!UserBD.myKey(user, token)){
			return false;
		}else{
			return GareBD.addFavGare(user, uic);
		}	
	}

	public static boolean removeFavor(String token, String user, String uic) {
		if(!UserBD.myKey(user, token)){
			return false;
		}else{
			return GareBD.removeFavGare(user, uic);
		}	
	}

	public static JSONObject getStations(String user1) {
		if(user1 == null || user1.isEmpty() || user1.equals("")){
			return null;
		}else{
			return GareBD.listArray(user1);
		}	
	}
}
