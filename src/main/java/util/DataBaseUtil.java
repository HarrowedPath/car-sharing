package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DataBaseUtil {
    private DataBaseUtil() {
    }

    private static String DB_URL = "jdbc:h2:D:./test";
    private static String USER = "sa";
    private static String PASSWORD = "sa";


    public static void setDbUrl(String[] args) {
//        if (args.length > 1 && args[0].equals("-databaseFileName")) {
//            DB_URL = (DB_URL + args[1]);
//        } else {
//            DB_URL = (DB_URL + "default.db");
//        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

}
