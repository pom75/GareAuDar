package bd;

import java.sql.Connection;
import java.sql.Statement;

public class FriendBD {


	public static boolean addFriend(int user1,int user2){
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "insert into Friends(id_user_1,id_user_2) VALUES ('"+user1+"','"+user2+"');";
			stm.executeUpdate(query);
			stm.close();
			co.close();
		} catch (Exception e) {
			System.err.print("Exception :");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean removeFriend(int user1, int user2) {
		Connection co;
		Statement stm;
		String query;
		try {
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();
			query = "delete from Friends where id_user_1 ='"+user1+"' and id_user_2 = '"+user2+"';";
			stm.executeUpdate(query);
			stm.close();
			co.close();
		} catch (Exception e) {
			System.err.print("Exception :");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
