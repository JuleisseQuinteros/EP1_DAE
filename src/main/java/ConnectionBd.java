import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBd {
    private static final String URL = "jdbc:mysql://localhost:3306/bd_alumnos";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Obtener la conexión
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
