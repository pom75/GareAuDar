package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

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

			UserBD.addUser(rep.getString("id"), key, rep.getString("email"), rep.getString("first_name"), rep.getString("gender"), rep.getString("last_name"), rep.getString("link"), rep.getString("locale"), rep.getString("name"), rep.getString("timezone"));
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
			//retourné une erreur ici
		}
		return getUserFacebook(key);
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
	
	private static String readAll(BufferedReader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

}
