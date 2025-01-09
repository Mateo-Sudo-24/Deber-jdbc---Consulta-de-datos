import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBdd {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/estudiantes2024b";
        String usuario = "root";
        String contraseña = "123456";
        return DriverManager.getConnection(url, usuario, contraseña);
    }
}
