package tools.apis;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;
import org.json.XML;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class SNCFApi {
	private static String log = "tnhtn175";
	private static String pwd = "cgP479kW";
	
	// Reçois un numéro de gare et retourne tous les prochai ntrain de passage a cette gare en JSON
	public static JSONObject getTrainAtGareJSON(String num){
		try {
            String line = " ";
            String str = " ";
			String address = "http://api.transilien.com/gare/"+ num +"/depart";
			URL url = new URL(address);
			String credentials = log + ":" + pwd;
			String encoding = Base64.encode(credentials.getBytes("UTF-8"));
			
			
			URLConnection uc = url.openConnection();
			uc.setRequestProperty("Authorization", String.format("Basic %s", encoding));
		 
			InputStream content = (InputStream) uc.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    content));
            while ((line = in.readLine()) != null) {
                str += line;
            }
            String xml  = str.toString();  
            JSONObject jsonObj = XML.toJSONObject(xml);   
 
            return jsonObj;
		} catch (java.io.IOException e) {
			// TODO return JSON ERROR HERE 
			System.out.println(" ERRO Nom de gare inexistant");
		}catch (Exception e) {
			// TODO return JSON ERROR HERE 
			System.out.println(" ERRO parsing XML TO JSON");
		}
		return null;
	}
	
	
	
	
	
	public static void main(String args[]){
		System.out.println(getTrainAtGareJSON("87393009"));
	}

}
