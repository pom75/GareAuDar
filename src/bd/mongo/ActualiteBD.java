package bd.mongo;

import java.net.UnknownHostException;
import java.util.Date;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

public class ActualiteBD {

	
	public static void postActualite(String user,String message){
	
		try {
			Date d = new Date();
			Mongo m = new Mongo("localhost",27017);
			DB db = m.getDB("gare");
			DBCollection collection = db.getCollection("actualite");
			BasicDBObject obj = new BasicDBObject();
			
			System.out.println(d);
			
			obj.put("user", user);
			obj.put("message",message);
			obj.put("date", d);
			System.out.println(obj);
			WriteResult res = collection.insert(obj);
			System.out.println(res.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
