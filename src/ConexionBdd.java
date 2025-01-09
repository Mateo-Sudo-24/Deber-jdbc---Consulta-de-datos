import java.sql.*;

public class ConexionBdd {
    public static Connection getConnection() {
        try {
            // Asegúrate de que el driver esté cargado
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Configura la conexión a tu base de datos
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/estudiantes2024b", "root", "123456");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
