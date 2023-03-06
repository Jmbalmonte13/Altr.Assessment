package altr;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testing";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "ANSKk08aPEDbFjDO";
    private static final String tempTable = "temp_table";
    
    public static String getUrl() {
        return DB_URL;
    }
    
    public static String getUser() {
        return DB_USER;
    }
    
    public static String getPassword() {
        return DB_PASSWORD;
    }
    
    public static String tempTable() {
        return tempTable;
    }
}


