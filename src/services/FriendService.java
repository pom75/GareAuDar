package services;

import org.json.JSONArray;

import bd.FriendBD;

public class FriendService {

	
	public static boolean addFriend(String user1,String user2){
		if(user1.isEmpty() || user1.equals("") || user2.isEmpty() || user2.equals("")){
			return false;
		}else{
			return FriendBD.addFriend(Integer.valueOf(user1),Integer.valueOf(user2));
		}
	}

	public static boolean removeFriend(String user1, String user2) {
		if(user1.isEmpty() || user1.equals("") || user2.isEmpty() || user2.equals("")){
			return false;
		}else{
			return FriendBD.removeFriend(Integer.valueOf(user1),Integer.valueOf(user2));
		}
	}

	public static JSONArray getFriends(String user1) {
		if(user1.isEmpty() || user1.equals("")){
			return null;
		}else{
			return FriendBD.listFriend(Integer.valueOf(user1));
		}
	}
}