package bd.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import bd.DBTools;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class TrainBD {

	public static boolean addTrain( String user_id, String numT, String date, String numG, String term) {
		Connection co;
		Statement stm;
		String query;

		try {
			//Connexion a la base
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();


			//Insertion dans la bd
			query = "INSERT INTO  Train (user_id,numT,date,numG,term) VALUES" + " ( '" + user_id+ "' , '" + numT + "', '" + date + "' , '" + numG + "' , '" + term + "' );";
			stm.executeUpdate(query);
			
			
			//On coupe la connexion
			stm.close();
			co.close();

			//Si une exeption est leve l'insersion na pas pu se faire , on revois false
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.err.print("utilisateur d��ja cr�� :");
			return false;
		} catch (Exception e) {
			System.err.print("Exception :");
			return false;
		}
		//Si tous ses bien passer on retrun true
		return true;
		
	}

}
