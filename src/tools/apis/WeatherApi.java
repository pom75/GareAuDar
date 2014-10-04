package tools.apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONObject;


public class WeatherApi {
		
	private static String readAll(BufferedReader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	
	public static JSONObject getWeatherNow(double x , double y){
		try {
			String address = "http://api.openweathermap.org/data/2.5/weather?lat="+x+"&lon="+y+"";
			System.out.println(address);
		 
			URL url = new URL(address);		 
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String str = readAll(in);
			in.close();
			JSONObject json = new JSONObject(str);		
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static JSONObject getWeatherForeCast(double x , double y , int cnt){
		try {
		String address = "http://api.openweathermap.org/data/2.5/forecast/daily?lat="+x+"&lon="+y+"&cnt="+cnt+"&mode=json";
		System.out.println(address);
		 
		URL url = new URL(address);		 
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));
		String str = readAll(in);
		in.close();
		JSONObject json = new JSONObject(str);		
		return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String args[]){
		System.out.println(getWeatherNow(48.43,2.25));
		System.out.println(getWeatherForeCast(48.43,2.25,10));
	}
	
}
