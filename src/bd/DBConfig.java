package bd;

// TODO: later, extranliser dans fichier config
public class DBConfig {

	// Option connexion
	static String MYSQL_HOST = "localhost:3306";
	static String MYSQL_USERNAME = "root";
	static String MYSQL_PASSWORD = "maimai"; // et ta grand m��re!
	static boolean MYSQL_POOLING = false;
	static String MYSQL_DB = "GareAuDar";

	// SQL tables
	public static final String TABLE_STATIONS = "Stations";
	public static final String TABLE_USER = "USER_FACEBOOK";
	public static final String TABLE_FRIENDS = "Friends";
	public static final String TABLE_FAVORITES = "GarFavor";
	public static final String TABLE_STATION_SEARCH = "SearchStations";
	public static final String TABLE_TRAIN = "Train";

	// MongoDB
	public static final String MONGO_IP = "localhost";
	public static final int MONGO_PORT = 27130;
	public static final String MONGO_DB = "gare";
	public static final String COLLECTION_ACTUALITE = "actualite";
	public static final String COLLECTION_TWEET = "actualite";
	
}