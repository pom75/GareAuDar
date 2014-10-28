package services;

import org.json.JSONObject;

import bd.mongo.TweetBD;

public class ToolService {

	
	
	public  static JSONObject getNTweet(String n){
		if(n == null || n.isEmpty() || n.equals("") ){
			return null;
		}else{
			return TweetBD.getNTweet(Integer.valueOf(n));
		}	
	}
}
