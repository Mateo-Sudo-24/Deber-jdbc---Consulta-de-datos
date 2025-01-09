import javax.swing.*;

public class Formulario extends JFrame {
    private JLabel bienvenidaLabel;

    public Formulario(String usuario) {
        // Configuración básica de la ventana
        setTitle("Formulario Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Etiqueta de bienvenida
        bienvenidaLabel = new JLabel("Bienvenido, " + usuario);
        bienvenidaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(bienvenidaLabel);
    }
}
