package services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bd.TrainBD;
import tools.apis.SNCFApi;

public class TrainService {

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
}

