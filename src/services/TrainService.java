package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bd.sql.FollowBD;
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
	
	public static JSONObject getTrainPasse(String key, String user_id) {
		JSONObject rep = new JSONObject();
		if(!UserBD.myKey(user_id, key)){
			//AHAHAHAH
		}else{
			JSONArray alltrain = TrainBD.getTrainUser(user_id);
			
			//Train passe uniquement
			alltrain = getTrainPasse(alltrain);
			
			try {
				rep.put("train", alltrain);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return rep;
	}

	public static JSONObject getTrainFuture(String key, String user_id) {
		JSONObject rep = new JSONObject();
		if(!UserBD.myKey(user_id, key)){
			//AHAHAHAH
		}else{
			
			//Recupere tout les trains 
			JSONArray alltrain = TrainBD.getTrainUser(user_id);
			
			//Train futur uniquement
			alltrain = getTrainFutur(alltrain , 1);
			
			//nb friend qui prennent ce train  == numT ==term  +-2h date
			
			//Date mise a jour de chaques train 
			rep = majAllTrain(alltrain);
			
			
			
		}	
		
		return rep;
	}
	
	
	public static JSONObject getTrainFallowFutur(String key, String user_id) {
		JSONObject rep = new JSONObject();
		JSONArray repT = new JSONArray();
		if(!UserBD.myKey(user_id, key)){
			//AHAHAHAH
		}else{
			JSONArray friend = new JSONArray();
			try {
				
				friend = FollowBD.listFollow(Integer.parseInt(user_id)).getJSONArray("list");
				
				for (int i = 0; i < friend.length(); i++){
					((JSONObject)  friend.get(i)).getString("id_user");
					
					JSONArray buff = new JSONArray();
					
					buff = TrainBD.getTrainUser(user_id);
					buff = getTrainFutur(buff, 0);
					
					for (int j = 0; j < buff.length(); j++){
						repT.put(buff.get(j));
					}
					
					
					
				}
			
			rep.put("train", repT);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		return rep;
		
	}
	
	
	private static JSONArray getTrainPasse(JSONArray tab) {
		JSONArray rep = new JSONArray();
		for (int i = 0; i < tab.length(); i++)
		{
			try {

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				Date aujourdhui = new Date();
				Date date1 = sdf.parse(((JSONObject)  tab.get(i)).getString("date"));
				Date date2 = sdf.parse(sdf.format(aujourdhui));
				
		    	
				
		    	if(date1.compareTo(date2)<0 ){
		    		rep.put(tab.get(i));
		    	}
			
			
			
			} catch (JSONException e) {
				System.out.println("BUG JSON LLAAA \n");
			} catch (ParseException e) {
				System.out.println("BUG JSON LLAAA DATE \n");
			}
		}
		
		return rep;
	}
	
	

	public static JSONArray getTrainFutur(JSONArray tab , int interval){
		JSONArray rep = new JSONArray();
		for (int i = 0; i < tab.length(); i++)
		{
			try {

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				Date aujourdhui = new Date();
				Date date1 = sdf.parse(((JSONObject)  tab.get(i)).getString("date"));
				Date date2 = sdf.parse(sdf.format(aujourdhui));
				
		    	
				//Si le train est pas encore passé ou si le train est a quai , ou si le train est en retard (toujours présent requette)
		    	if(date1.compareTo(date2)>0 || date1.compareTo(date2)==0 || isInInterval(date1.toString(), date2.toString(), interval) ){
		    		rep.put(tab.get(i));
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
	
	//retourn true sur la date d1 et d2 son a interval de temps 
	public static boolean isInInterval(String d1, String d2, int interval){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Date date1;
		try {
			date1 = sdf.parse(d1);
			Date date2 = sdf.parse(d2);
			if(date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth()  && date1.getDay() 
	    			== date2.getDay() && date1.getHours() - date2.getHours() < interval && date1.getHours() - date2.getHours() > - interval){
				return true;
	    	}
		} catch (ParseException e) {
			System.out.println("FAIL HERR");
			return false;
		}
		
		
		
    	
		return false;
	}


}


