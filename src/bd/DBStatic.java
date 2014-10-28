package bd;

// TODO: later, extranliser dans fichier config
public class DBStatic {

    //Option connexion
    static String mysql_host = "localhost:3306";
    static String mysql_username = "root";
    static String mysql_password = "maimai"; //et ta grand mère!
    static boolean mysql_pooling = false ;
    static String mysql_db = "GareAuDar";
    

    //SQL tables
    public static final String TABLE_STATIONS = "Stations";
    public static final String TABLE_USER = "USER_FACEBOOK";
    public static final String TABLE_FRIENDS = "Friends";
    
    
    //MongoDB
    public static final String MONGO_IP = "localhost";
    public static final int MONGO_PORT = 27130;
    public static final String MONGO_DB = "gare";
    public static final String COLLECTION_ACTUALITE = "actualite";
    public static final String COLLECTION_TWEET = "actualite";
}