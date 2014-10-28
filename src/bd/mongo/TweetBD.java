package bd.mongo;

import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class TweetBD {

	public static JSONObject getNTweet(int n) {
		JSONObject json = new JSONObject();
		try {
			MongoClient mg = new MongoClient("localhost",27017);
			DB db = mg.getDB("gare");
			DBCollection collection = db.getCollection("actualite");
			
			DBCursor cs = collection.find().sort(new BasicDBObject("_id",-1)).limit(n);
			
			while(cs.hasNext()){
				DBObject next = cs.next();
				json.accumulate("tweets", next);
			}
		 
			cs.close();
			mg.close();
			return json;
		 } catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		 return json;
	}

}
