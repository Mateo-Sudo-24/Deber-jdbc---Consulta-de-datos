import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JTextField logintxt;
    private JPasswordField passwordtxt;
    private JButton OKButton;
    private JLabel userLabel, passLabel;
    private JPanel login;

    // Usuario y contraseña predeterminados
    private static final String USUARIO = "profesor";
    private static final String CONTRASENA = "contrasena123";

    public Login() {
        login = new JPanel();
        login.setLayout(null);

        // Etiqueta de Usuario
        userLabel = new JLabel("Usuario = Profesor:");
        userLabel.setBounds(50, 20, 200, 30);
        login.add(userLabel);

        // Campo de texto para el nombre
        logintxt = new JTextField();
        logintxt.setBounds(50, 50, 200, 30);
        login.add(logintxt);

        // Etiqueta de Contraseña
        passLabel = new JLabel("Contraseña:");
        passLabel.setBounds(50, 100, 200, 30);
        login.add(passLabel);

        // Campo de texto para la contraseña
        passwordtxt = new JPasswordField();
        passwordtxt.setBounds(50, 130, 200, 30);
        login.add(passwordtxt);

        // Botón OK
        OKButton = new JButton("OK");
        OKButton.setBounds(50, 180, 200, 30);
        login.add(OKButton);

        // Acción del botón
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = logintxt.getText();
                String contrasena = new String(passwordtxt.getPassword());

                if (usuario.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                    return;
                }

                // Validar con los valores predefinidos
                if (usuario.equals(USUARIO) && contrasena.equals(CONTRASENA)) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                    new BienvenidaVentana().setVisible(true); // Mostrar ventana de bienvenida
                    ((JFrame) SwingUtilities.getWindowAncestor(login)).dispose(); // Cerrar el login
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                }
            }
        });

        login.setPreferredSize(new java.awt.Dimension(300, 300));
    }

    public JPanel getLoginPanel() {
        return login;
    }
}
