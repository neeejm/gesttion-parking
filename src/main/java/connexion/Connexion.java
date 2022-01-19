package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private final String url = "jdbc:postgresql://localhost/eparking";
    private final String user = "postgres";
    private final String password = "shelL.4711";
    private Connection connection = null;
    private static Connexion instane = null;

    private Connexion() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("Connexion errror");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // try {
        // Class.forName("com.mysql.cj.jdbc.Driver");
        // connection = DriverManager.getConnection(url + db, login, password);
        // } catch (ClassNotFoundException e) {
        // System.out.println("Driver introvable");
        // } catch (SQLException e) {
        // System.out.println("Connexion errror");
        // System.out.println(e.getMessage());
        // }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Connexion getInstane() {
        if (instane == null) {
            instane = new Connexion();
        }
        return instane;
    }
}
