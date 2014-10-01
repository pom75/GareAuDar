package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SessionBD {

    public static boolean keyExist(String key) {
        String dat = null;
        Connection co;
        ResultSet res = null;
        Date dateKey = null;
        Date dateNow = new Date();
        boolean b = false;
        try {
            co = DBTools.getMySQLConnection();
            Statement stm = co.createStatement();

            String query = "SELECT * FROM " + DBStatic.TABLE_SESSION + " WHERE cle='" + key + "';";
            res = stm.executeQuery(query);
            b = res.next();

            

            //Si la cle n'est pas perimer on la suprime et on en cree une nouvelle
            if (b) {
                dat = res.getString("date_expi");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dateKey = sdf.parse(dat);

                if (!dateNow.before(dateKey)) {
                    supKey(key);
                    b=false;
                }

            }
            co.close();
            stm.close();
        } catch (Exception e) {
            System.err.print("Execption: ");
            e.printStackTrace();
        }

            return b;
        }

    

    public static boolean userExist(String iduser) {
        Connection con = null;
        Statement stm = null;
        ResultSet rep;

        try {
            //Connexion a la bd 
            con = DBTools.getMySQLConnection();
            stm = con.createStatement();

            //On selection le login entrer , dansla table User
            rep = stm.executeQuery("SELECT * FROM " + DBStatic.TABLE_SESSION + " WHERE iduser='" + iduser + "'");

            //On teste si le resultat est null
            boolean a = rep.next() != false;
            stm.close();
            con.close();
            return a;

        } catch (Exception e) {
        }

        return false;
    }

    public static String getIdUser(String key) {
        Connection co;
        Statement stm;
        String query;
        ResultSet res;
        String idUser = null;

        try {
            co = DBTools.getMySQLConnection();
            stm = co.createStatement();

            query = "SELECT * FROM " + DBStatic.TABLE_SESSION + " WHERE cle='" + key + "';";
            res = stm.executeQuery(query);

            res.next();
            idUser = res.getString("iduser");

            co.close();
        } catch (Exception e) {
            System.err.print("Exception :");
            e.printStackTrace();
            return e + "";
        }

        return idUser;
    }
    
    public static String getNameUser(String id) {
        Connection co;
        Statement stm;
        String query;
        ResultSet res;
        String idUser = "";

        try {
            co = DBTools.getMySQLConnection();
            stm = co.createStatement();

            query = "SELECT * FROM " + DBStatic.TABLE_SESSION + " WHERE iduser='" + id + "';";
            res = stm.executeQuery(query);

            res.next();
            idUser = res.getString("prenom");
            idUser += " ";
            idUser += res.getString("nom");
                    
            co.close();
        } catch (Exception e) {
            System.err.print("Exception :");
            e.printStackTrace();
            return e + "";
        }

        return idUser;
    }

    public static boolean supKey(String key) {
        Connection con = null;
        Statement stm = null;
        try {
            con = DBTools.getMySQLConnection();
            stm = (Statement) con.createStatement();

            //On suprime les anciennes cle de l user
            stm.executeUpdate("DELETE FROM " + DBStatic.TABLE_SESSION + " WHERE cle ='" + key + "'");

            //deconnecion de la bd
            stm.close();
            con.close();

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static String creatInsertKey(String iduser) throws SQLException, ClassNotFoundException {

        //creation de la cle
        String key = "";
        while (key.length() < 32) {
            int x = (int) (Math.random() * 123);
            if (x >= 65 && x <= 90 || x >= 97 && x <= 122 || x >= 48 && x <= 57) {
                key += (char) x;
            }
        }

        Connection con = null;
        Statement stm = null;
        ResultSet rep = null;
        String query;

        //Creation de la date d'aujourdui
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DATE, 4);

        //Conexion a la bd
        con = DBTools.getMySQLConnection();
        stm = (Statement) con.createStatement();

        //On suprime les anciennes cle de l user
        stm.executeUpdate("DELETE FROM " + DBStatic.TABLE_SESSION + " WHERE iduser ='" + iduser + "'");

        //INsertion de la cle
        query = "INSERT INTO " + DBStatic.TABLE_SESSION + " (idc,cle,iduser,date_expi) VALUES" + " (NULL ,'" + key + "','" + iduser + "','" + sdf.format(calendar.getTime()) + "');";
        stm.executeUpdate(query);

        //deconnecion de la bd
        stm.close();
        con.close();

        return key;

    }

    public static String creatInsertKeyK(String cle) throws SQLException, ClassNotFoundException {

        //creation de la cle
        String key = "";
        while (key.length() < 32) {
            int x = (int) (Math.random() * 123);
            if (x >= 65 && x <= 90 || x >= 97 && x <= 122 || x >= 48 && x <= 57) {
                key += (char) x;
            }
        }
        String iduser = getIdUser(cle);
        Connection con = null;
        Statement stm = null;
        ResultSet rep = null;
        String query;

        //Creation de la date d'aujourdui
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DATE, 4);

        //Conexion a la bd
        con = DBTools.getMySQLConnection();
        stm = (Statement) con.createStatement();

        //On suprime les anciennes cle de l user
        stm.executeUpdate("DELETE FROM " + DBStatic.TABLE_SESSION + " WHERE cle ='" + cle + "'");

        //INsertion de la cle
        query = "INSERT INTO " + DBStatic.TABLE_SESSION + " (idc,cle,iduser,date_expi) VALUES" + " (NULL ,'" + key + "','" + iduser + "','" + sdf.format(calendar.getTime()) + "');";
        stm.executeUpdate(query);

        //deconnecion de la bd
        stm.close();
        con.close();

        return key;

    }
}