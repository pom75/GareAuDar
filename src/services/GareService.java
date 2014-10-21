package services;

import bd.GareBD;

public class GareService {

	
	public static boolean addFavor(String token,String uic){
		if(token.isEmpty() || token.equals("") || uic.isEmpty() || uic.equals("")){
			return false;
		}else{
			return GareBD.addFavGare(token, uic);
		}	
	}
}
