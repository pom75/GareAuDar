package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FriendBD {
	public static boolean addFriend(String id_fb1, String id_fb2) {
        Connection co;
        Statement stm;
        String query;
        ResultSet rep = null;
        String id_user1 = "";
        String id_user2 = "";

        try {
            //Connexion a la base
            co = DBTools.getMySQLConnection();
            stm = co.createStatement();

            rep = stm.executeQuery("SELECT * FROM " + DBStatic.TABLE_USER + "  WHERE id_fb='" + id_fb1 + "'");

            //Si il existe on retourn l'id et le login sinon false
            if (rep.next() != false) {
                id_user1 = rep.getString("id_user");
            }
            
            rep = stm.executeQuery("SELECT * FROM " + DBStatic.TABLE_USER + "  WHERE id_fb='" + id_fb2 + "'");

            //Si il existe on retourn l'id et le login sinon false
            if (rep.next() != false) {
                id_user2 = rep.getString("id_user");
            }

            //Insertion dans la bd
            query = "INSERT INTO " + DBStatic.TABLE_FRIENDS + " (id,id_user_1,id_user_2) VALUES" + " (NULL ,'" + id_user1 + "','" + id_user2 + "');";
            stm.executeUpdate(query);


            //On coupe la connexion
            stm.close();
            co.close();

            //Si une exeption est leve l'insersion na pas pu se faire , on revois false
        } catch (Exception e) {
            System.err.print("Exception :");
            e.printStackTrace();
            return false;
        }
        //Si tous ses bien passer on retrun true
        return true;

	}

}

