package bd.mongo;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;

import static bd.DBStatic.*;

public class ActualiteBD {

	public static void postActualite(String user, String message) {

		try {
			Date d = new Date();
			Mongo m = new Mongo(MONGO_IP, MONGO_PORT);
			DB db = m.getDB(MONGO_DB);
			DBCollection collection = db.getCollection(COLLECTION_ACTUALITE);
			BasicDBObject obj = new BasicDBObject();

			System.out.println(d);

			obj.put("user", user);
			obj.put("message", message);
			obj.put("date", d);
			System.out.println(obj);
			WriteResult res = collection.insert(obj);
			System.out.println(res.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// TODO: generic handling of error!?
			e.printStackTrace();
		}
	}
}
