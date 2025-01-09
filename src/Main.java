import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crear y mostrar la ventana de login
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().getLoginPanel()); // Usar el método getLoginPanel para acceder al JPanel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setVisible(true);
    }
}
