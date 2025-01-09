import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class BienvenidaVentana extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;

    public BienvenidaVentana() {
        setTitle("Bienvenido");
        setSize(500, 400);
        setLocationRelativeTo(null); // Centrar la ventana

        // Llamar a cargar los datos para llenar la tabla
        cargarDatos();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Método para cargar los datos de la base de datos y mostrar en la tabla
    public void cargarDatos() {
        // Crear el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Cedula");
        model.addColumn("Nombre");
        model.addColumn("B1");
        model.addColumn("B2");

        try {
            // Realizar la consulta SQL
            Connection con = ConexionBdd.getConnection();  // Asegúrate de tener tu método de conexión
            String query = "SELECT cedula, nombre, b1, b2 FROM estudiantes";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Agregar los datos a la tabla
            while (rs.next()) {
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                int b1 = rs.getInt("b1");
                int b2 = rs.getInt("b2");
                model.addRow(new Object[]{cedula, nombre, b1, b2});
            }

            // Cerrar la conexión
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Crear la tabla y el JScrollPane
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);  // Ajustar el tamaño de la tabla al tamaño del viewport

        // Agregar el JScrollPane al JFrame
        add(scrollPane, BorderLayout.CENTER);
    }
}
