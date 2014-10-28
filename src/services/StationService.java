package services;

import bd.StationBD;
import bd.UserBD;

public class StationService {

	public static boolean addSearched(String token,String user,String uic){
		if(!UserBD.myKey(user, token)){
			return false;
		}else{
			return StationBD.addFavGare(user, uic);
		}	
	}
}
