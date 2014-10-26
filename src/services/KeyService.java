package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import bd.FollowBD;
import bd.UserBD;

public class KeyService {

	private String key;
	
	public KeyService(String key){
		this.key = key;
	}

	public JSONObject service(){
		try {
			JSONObject rep = getUserFacebook(key);
			if(rep.has("error")){
				return rep;
			}

			//TODO tester si un utilisateur est déja enregitrer et mettre a jour son token
			
			if(UserBD.userExist(rep.getString("id"))){
				UserBD.upKey(rep.getString("id"), key);
				
			}else{
				UserBD.addUser(rep.getString("id"), key, rep.getString("email"), rep.getString("first_name"), rep.getString("gender"), rep.getString("last_name"), rep.getString("link"), rep.getString("locale"), rep.getString("name"), rep.getString("timezone"));

			}
			
			
			//Ajout la liste des ces amis dans la bd
			JSONArray listF = getFriendUserFacebook(key).getJSONArray("data");
			for(int i= 0; i< listF.length(); i++){
				FollowBD.addFollow(rep.getString("id"), listF.getJSONObject(i).getString("id"));
				FollowBD.addFollow(listF.getJSONObject(i).getString("id"), rep.getString("id"));

			}
			
			
			
		} catch (Exception e) {
			//System.out.println(e);
			return null;
			//retourné une erreur ici
		}
		return UserBD.getUser(key);
	}

	public static JSONObject getUserFacebook(String key){
		try {
			String address = "https://graph.facebook.com/v2.1/me?key=value&access_token="+key;
			System.out.println(address);
		 
			URL url = new URL(address);		 
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String str = readAll(in);
			in.close();
			JSONObject json = new JSONObject(str);		
			return json;
		} catch (Exception e) {
			return null;
			//retourné une erreur ici
		}
	}
	
	public static JSONObject getFriendUserFacebook(String key){
		try {
			String address = "https://graph.facebook.com/v2.1/me/friends?key=value&access_token="+key;
			System.out.println(address);
		 
			URL url = new URL(address);		 
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String str = readAll(in);
			in.close();
			JSONObject json = new JSONObject(str);		
			return json;
		} catch (Exception e) {
			return null;
			//retourné une erreur ici
		}
	}
	
	private static String readAll(BufferedReader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

}
