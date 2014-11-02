package tools;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

	// Error code and names

	public static JSONObject newJsonError(int errorCode, String message) {
		JSONObject js = new JSONObject();

		try {
			js.put("status", "error");
			js.put("error_code", errorCode);
			js.put("error_message", message);
		} catch (JSONException e) {
			// TODO should not occur
			e.printStackTrace();
		}

		return js;
	}

	public static JSONObject newJsonOk() {
		JSONObject js = new JSONObject();
		try {
			js.put("status", "ok");
		} catch (JSONException e) {
			// TODO should not occur
			e.printStackTrace();
		}
		return js;
	}

	//TODO: see for array??
	// Mettre dans champs result?
	// Json okay prend le json de resultat?
	
	
}
