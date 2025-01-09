import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    private JTextField logintxt;
    private JPasswordField passwordtxt;
    private JButton OKButton;
    private JLabel userLabel, passLabel;
    private JPanel login;

    public Login() {
        login = new JPanel();
        login.setLayout(null); // Usar null layout para controlar manualmente la posición de los componentes

        // Etiqueta de Usuario
        userLabel = new JLabel("Usuario = Nombre:");
        userLabel.setBounds(50, 20, 200, 30); // Tamaño adecuado para la etiqueta
        login.add(userLabel);

        // Campo de texto para el nombre
        logintxt = new JTextField();
        logintxt.setBounds(50, 50, 200, 30); // Tamaño adecuado para el campo
        login.add(logintxt);

        // Etiqueta de Contraseña
        passLabel = new JLabel("Contraseña = Cédula:");
        passLabel.setBounds(50, 100, 200, 30); // Tamaño adecuado para la etiqueta
        login.add(passLabel);

        // Campo de texto para la contraseña
        passwordtxt = new JPasswordField();
        passwordtxt.setBounds(50, 130, 200, 30); // Tamaño adecuado para el campo
        login.add(passwordtxt);

        // Botón OK
        OKButton = new JButton("OK");
        OKButton.setBounds(50, 180, 200, 30); // Tamaño adecuado para el botón
        login.add(OKButton);

        // Acción del botón
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String parametro1 = logintxt.getText();
                String parametro2 = new String(passwordtxt.getPassword());
                if (parametro1.isEmpty() || parametro2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                    return;
                }

                try {
                    Connection con = ConexionBdd.getConnection();
                    System.out.println("Conexión a la base de datos exitosa.");

                    String query = "SELECT * FROM estudiantes WHERE nombre = ? AND cedula = ?";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, parametro1);
                    stmt.setString(2, parametro2);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                        new Formulario(parametro1).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                    }
                    con.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
                    ex.printStackTrace();
                }
            }
        });

        // Establecer tamaño mínimo para el panel
        login.setPreferredSize(new java.awt.Dimension(300, 300)); // Establecer un tamaño mínimo
    }

    // Método para obtener el JPanel
    public JPanel getLoginPanel() {
        return login;
    }
}
