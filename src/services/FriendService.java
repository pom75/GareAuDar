package services;

import bd.FriendBD;

public class FriendService {

	
	public static boolean addFriend(String user1,String user2){
		if(user1.isEmpty() || user1.equals("") || user2.isEmpty() || user2.equals("")){
			return false;
		}else{
			return FriendBD.addFriend(Integer.valueOf(user1),Integer.valueOf(user2));
		}
	}
}
