package bd.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import bd.DBTools;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class TrainBD {

	public static int addTrain(String train) {
		Connection co;
		Statement stm;
		String query;
		int num = 0;

		try {
			//Connexion a la base
			co = DBTools.getMySQLConnection();
			stm = co.createStatement();

			//Recuperation de l'heur actuel
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			//Insertion dans la bd
			query = "INSERT INTO  Train (JSON) VALUES" + " ( '" + train + "');";
			
			stm.executeUpdate(query);
			
			query = "Select * from Train where JSON = '"+train+"';";
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()){
			//	json.put("id", rs.getInt("id"));;
			}


			//On coupe la connexion
			stm.close();
			co.close();

			//Si une exeption est leve l'insersion na pas pu se faire , on revois false
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.err.print("utilisateur déja cré :");
			return -1;
		} catch (Exception e) {
			System.err.print("Exception :");
			return -1;
		}
		//Si tous ses bien passer on retrun true
		return num;
		
	}

}
