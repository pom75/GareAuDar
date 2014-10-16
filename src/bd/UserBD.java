package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserBD {

	public static boolean userExist(String id_fb) {
		Connection con = null;
		Statement stm = null;
		ResultSet rep;

		try {
			//Connexion a la bd 
			con = DBTools.getMySQLConnection();
			stm = con.createStatement();

			//On selection le login entrer , dansla table User
			rep = stm.executeQuery("SELECT * FROM " + DBStatic.TABLE_USER + " WHERE id_fb='" + id_fb + "'");

			//On teste si le resultat est null
			boolean a = rep.next() != false;
			stm.close();
			con.close();
			return a;

		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	public static boolean addUser(String id_fb, String token, String email, String first_name, String gender, String last_name, String link, String locale, String name, String timezone) {
        Connection co;
        Statement stm;
        String query;

        try {
            //Connexion a la base
            co = DBTools.getMySQLConnection();
            stm = co.createStatement();

            //Recuperation de l'heur actuel
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            //Insertion dans la bd
            query = "INSERT INTO " + DBStatic.TABLE_USER + " (id_user,token,id_fb,email,first_name,gender,last_name,link,locale,name,timezone) VALUES" + " (NULL ,'" + token + "','" + id_fb + "','" + email + "','" + first_name + "','" + gender + "' , '" + last_name + "' , '" + link + "' , '" + locale + "' ,'" + name + "' , '" + timezone + "');";
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
