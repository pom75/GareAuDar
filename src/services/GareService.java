package services;

import org.json.JSONObject;

import bd.sql.FavoriteBD;
import bd.sql.UserBD;

public class GareService {

	
	public static boolean addFavor(String token,String user,String uic){
		if(!UserBD.myKey(user, token)){
			return false;
		}else{
			return FavoriteBD.addFavoriteStation(user, uic);
		}	
	}

	public static boolean removeFavor(String token, String user, String uic) {
		if(!UserBD.myKey(user, token)){
			return false;
		}else{
			return FavoriteBD.removeFavoriteStation(user, uic);
		}	
	}

	public static JSONObject getStations(String user1) {
		if(user1 == null || user1.isEmpty() || user1.equals("")){
			return null;
		}else{
			return FavoriteBD.listFavoriteStations(user1);
		}	
	}
}
