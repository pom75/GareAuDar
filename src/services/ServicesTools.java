package services;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServicesTools {

	public static JSONObject ok() {
		return new JSONObject();
	}

/*
	//method ok pour le service Login
	public static JSONObject ok(String id,String login , String key) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", id);
			obj.put("login", login);
			obj.put("key", key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	
*/

	public static JSONObject erreur(String mess, int code) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("mess", mess);
			obj.put("code", code);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
