package bd.auth;

import bd.DBStatic;
import bd.DBTools;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class ExampleAuthBD {

    public static boolean userExist(String login) {
        Connection con = null;
        Statement stm = null;
        ResultSet rep;

        try {
            //Connexion a la bd 
            con = DBTools.getMySQLConnection();
            stm = con.createStatement();

            //On selection le login entrer , dansla table User
            rep = stm.executeQuery("SELECT * FROM " + DBStatic.TABLE_SESSION + " WHERE login='" + login + "'");

            //On teste si le resultat est null
            boolean a = rep.next() != false;
            stm.close();
            con.close();
            return a;

        } catch (Exception e) {
            
        }

        return false;
    }
    

    public static LinkedList<String> loginSql(String login, String pass) {
        Connection con = null;
        Statement stm = null;
        ResultSet rep = null;
        LinkedList<String> res = new LinkedList<String>();
        res.add("false");

        try {
            //Connexion a la base
            con = DBTools.getMySQLConnection();
            stm = (Statement) con.createStatement();
            //On cherche si le login et le mdp entre existe
            rep = stm.executeQuery("SELECT * FROM " + DBStatic.TABLE_SESSION + " u WHERE login='" + login + "' AND pwd='" + pass + "'");

            //Si il existe on retourn l'id et le login sinon false
            if (rep.next() != false) {
                res.remove();
                res.add(rep.getString("iduser"));
                res.add(rep.getString("login"));
            }
            //On ferme les connexion
            stm.close();
            con.close();


        } catch (Exception e) {
        }


        return res;
    }

    public static boolean addUser(String login, String pass, String name, String fname, String mail) {
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
            query = "INSERT INTO " + DBStatic.TABLE_SESSION + " (iduser,login,nom,prenom,mail,pwd,date_inscription,last_co) VALUES" + " (NULL ,'" + login + "','" + name + "','" + fname + "','" + mail + "','" + pass + "' , '" + sdf.format(calendar.getTime()) + "' , '" + sdf.format(calendar.getTime()) + "');";
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

    public static boolean addIp(String iduser, String ip) {
        Connection co;
        Statement stm;
        String query;

        try {
            //Connexion a la base
            co = DBTools.getMySQLConnection();
            stm = co.createStatement();

            //Recuperation de l'heur actuel
            Date aujourdhui = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

            //Mise a jour de la date de derniere connexion
            stm.executeUpdate("UPDATE USER SET last_co='" + formater.format(aujourdhui) + "'  WHERE iduser ='" + iduser + "';");

            //Insertion dans la bd
            query = "INSERT INTO " + DBStatic.TABLE_SESSION + " (idip,iduser,ip,date) VALUES" + " (NULL ,'" + iduser + "','" + ip + "','" + formater.format(aujourdhui) + "');";
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