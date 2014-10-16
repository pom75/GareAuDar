/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

public class DBStatic {

    //Otpion connexion
    static String mysql_host = "localhost:3306";
    static String mysql_username = "root";
    static String mysql_password = "maimai";
    static boolean mysql_pooling = false ;
    static String mysql_db = "GareAuDar";
    

    //SQL tables
    public static final String TABLE_STATIONS = "Stations";
    public static final String TABLE_USER = "USER_FACEBOOK";
    
    
    //MongoDB
    public static final String COLLECTION_ACTUALITE = "actualite";
    public static final String IP_MONGO = "localhost";
    public static final int PORT_MONGO = 27130;
    public static final String BD_MONGO = "gare";
}