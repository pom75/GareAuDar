package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bd.sql.StationBD;
import bd.sql.TrainBD;
import bd.sql.UserBD;
import tools.apis.SNCFApi;

public class TrainService {

	public static boolean addTrain(String key, String user_id, String numT,
			String date, String numG, String term) {
		if(!UserBD.myKey(user_id, key)){
			return false;
		}else{
			return TrainBD.addTrain(user_id, numT,date,numG, term);
		}	
	}

	public static JSONObject getTrainFuture(String key, String user_id) {
		JSONObject rep = new JSONObject();
		if(!UserBD.myKey(user_id, key)){
			//AHAHAHAH
		}else{
			
			//Recupere tout les trains 
			JSONArray alltrain = TrainBD.getTrainFutur(user_id);
			
			//Train futur uniquement
			alltrain = getTrainFutur(alltrain);
			
			//nb friend qui prennent ce train  == numT ==term  +-2h date
			
			//Date mise a jour de chaques train 
			rep = majAllTrain(alltrain);
			
			
			
		}	
		
		return rep;
	}
	
	
	public static JSONArray getTrainFutur(JSONArray tab){
		JSONArray rep = new JSONArray();
		for (int i = 0; i < tab.length(); i++)
		{
			try {

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				Date aujourdhui = new Date();
				Date date1 = sdf.parse(((JSONObject)  tab.get(i)).getString("date"));
				Date date2 = sdf.parse(sdf.format(aujourdhui));
				
		    	
		    	if(date1.compareTo(date2)>0 || date1.compareTo(date2)==0){
		    		//utile ? test directement put(get i) )
		    		
		    		JSONObject json = new JSONObject();
					json.put("numT", ((JSONObject)  tab.get(i)).getString("numT"));
					json.put("date",((JSONObject)  tab.get(i)).getString("date"));
					json.put("numG",((JSONObject)  tab.get(i)).getString("numG"));
					json.put("term",((JSONObject)  tab.get(i)).getString("term"));
		    		rep.put(json);
		    	}
			
			
			
			} catch (JSONException e) {
				System.out.println("BUG JSON LLAAA \n");
			} catch (ParseException e) {
				System.out.println("BUG JSON LLAAA DATE \n");
			}
		}
		
		return rep;
		
	}

	
	public static JSONObject majAllTrain(JSONArray tab){
		JSONArray array = new JSONArray();
		JSONArray resT = new JSONArray();
		JSONObject res = new JSONObject();
		

		
		
		for (int i = 0; i < tab.length(); i++){
			try {
				//TODO : Ameliorer sur le train naparait plus
				JSONObject obj = SNCFApi.getTrainAtGareJSON(((JSONObject)  tab.get(i)).getString("numG"));
				array = obj.getJSONObject("passages").getJSONArray("train");
				for (int j = 0; j < array.length(); j++)
				{
					//Donc si ici aucun resultat mettre le trai na suprimer ou passé ou disparu ^^
					try {
						if( ((JSONObject)  array.get(j)).getString("num").contentEquals(((JSONObject)  tab.get(i)).getString("numT")) ){
							((JSONObject)  array.get(j)).put("numG", (((JSONObject)  tab.get(i)).getString("numG"))) ;
							resT.put(array.get(j));
						}
						

					} catch (JSONException e) {
						System.out.println("BUG JSON 2");
					}
				}

			
			
			} catch (JSONException e) {
				System.out.println("BUG JSON LLAAA \n");
			}
		}
		
		try {
			res.put("train", resT);
		} catch (JSONException e) {
			System.out.println("BUG JSON LLAAA mais impossible \n");
			e.printStackTrace();
		}
		
		
		System.out.println(res.toString());
		return res;
		
	}
	
	
	/*
	public static JSONObject addTrains(String num){
		JSONObject obj = SNCFApi.getTrainAtGareJSON(num);
		JSONArray array = null;
		
		try {
			array = obj.getJSONObject("passages").getJSONArray("train");
			
		} catch (JSONException e) {
			System.out.println("BUG JSON 1");
		}

		for (int i = 0; i < array.length(); i++)
		{
			try {
				((JSONObject)  array.get(i)).put("id", TrainBD.addTrain(array.get(i).toString())); 

			} catch (JSONException e) {
				System.out.println("BUG JSON 2");
			}
		}
		
		JSONObject res = new JSONObject();
		JSONObject fin = new JSONObject();
		try {
			res.put("train", array);
			res.put("gare",obj.getJSONObject("passages").getString("gare"));
			fin.put("passages", res);
		} catch (JSONException e) {
			System.out.println(" \n BUG JSON 3");
		}
		return fin;
	}
	*/
}


