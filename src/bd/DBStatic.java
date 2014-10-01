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
    static String mysql_db = "projet";
    //tables
    //SQL
    public static final String TABLE_SESSION = "SESSION";
    
    //MongoDB
    public static final String COLLECTION_POST = "post";
    public static final String IP_MONGO = "li328.lip6.fr";
    public static final int PORT_MONGO = 27130;
    public static final String BD_MONGO = "fe_fe";
}