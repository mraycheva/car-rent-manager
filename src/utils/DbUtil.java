package utils;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    static Connection conn;

    public DbUtil() {
    }

    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            String path = "jdbc:h2:tcp://localhost/" +
                    Paths.get(".").toAbsolutePath().normalize().toString()
                    +"\\database\\guidb";
            System.out.println(path);

            conn = DriverManager
                    .getConnection(path,
                            "sa",
                            "fmi");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}