(Todo el codigo se encuentra en la rama master)

1.Creacion de clases 
 1.1.Ventana Bienvenida
 
 ![image](https://github.com/user-attachments/assets/f028b0d6-79f2-42e1-8c11-2eab19ee6c30)
 ![image](https://github.com/user-attachments/assets/d498737d-c6f5-4192-82a3-3bf0f284c5ed)
 ![image](https://github.com/user-attachments/assets/fe3c63fa-d7f5-42b2-b167-0e93df4c9e95)
 ![image](https://github.com/user-attachments/assets/19aef8ad-430d-4a3b-a109-6a081e803151)
 
Librerías importadas:
javax.swing.*:

Esta es una librería de Java que proporciona clases para crear interfaces gráficas de usuario (GUI). Las clases más comunes de esta librería incluyen JFrame (ventana principal), JPanel (paneles de contenedor), JTable (tabla), JScrollPane (panel de desplazamiento), etc.
java.awt.*:

Librería que permite crear interfaces gráficas a nivel de componentes (como botones, cajas de texto, etc.) y gestionar el diseño de los componentes dentro de la ventana. BorderLayout es parte de esta librería, y es utilizado aquí para organizar los componentes dentro de la ventana.
java.sql.*:

Esta librería proporciona las clases necesarias para conectarse y trabajar con bases de datos en Java. Se utiliza para ejecutar consultas SQL, procesar resultados y manejar conexiones a bases de datos.
javax.swing.table.DefaultTableModel:

Es una clase especializada en el manejo de modelos de datos de tablas dentro de JTable. Permite agregar, eliminar y modificar filas y columnas de manera fácil.
Explicación del código:
Clase BienvenidaVentana:
Esta clase extiende de JFrame, lo que significa que es una ventana gráfica que puede contener otros componentes de la interfaz.

java
Copiar código
public class BienvenidaVentana extends JFrame {
Atributos:
JTable table: Es un componente de la interfaz que permite mostrar datos en una tabla.
JScrollPane scrollPane: Es el componente que permite agregar barras de desplazamiento a la tabla en caso de que los datos no puedan mostrarse completamente dentro del área visible.
Constructor BienvenidaVentana:
java
Copiar código
public BienvenidaVentana() {
    setTitle("Bienvenido");
    setSize(500, 400);
    setLocationRelativeTo(null); // Centrar la ventana
    cargarDatos();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
setTitle("Bienvenido"): Establece el título de la ventana.
setSize(500, 400): Define el tamaño de la ventana en píxeles (500 píxeles de ancho y 400 de alto).
setLocationRelativeTo(null): Centra la ventana en la pantalla.
cargarDatos(): Llama al método que carga los datos desde la base de datos y los muestra en la tabla.
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE): Indica que cuando se cierre la ventana, la aplicación también debe terminar.
Método cargarDatos():
Este es el método principal que se encarga de conectarse a la base de datos, recuperar los datos y mostrarlos en la tabla.

java
Copiar código
public void cargarDatos() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Cedula");
    model.addColumn("Nombre");
    model.addColumn("B1");
    model.addColumn("B2");
DefaultTableModel model = new DefaultTableModel();: Crea un modelo de datos vacío para la tabla.
model.addColumn(...): Agrega las columnas que se mostrarán en la tabla (en este caso, "Cedula", "Nombre", "B1" y "B2").
java
Copiar código
try {
    Connection con = ConexionBdd.getConnection();  // Obtener conexión a la base de datos
    String query = "SELECT cedula, nombre, b1, b2 FROM estudiantes";
    PreparedStatement stmt = con.prepareStatement(query);
    ResultSet rs = stmt.executeQuery();
Connection con = ConexionBdd.getConnection();: Obtiene la conexión a la base de datos a través del método getConnection() de la clase ConexionBdd (que no está en el código mostrado, pero asumo que es donde se gestiona la conexión a la base de datos).
String query = "SELECT ...": Se define una consulta SQL para seleccionar las columnas cedula, nombre, b1, y b2 de la tabla estudiantes.
PreparedStatement stmt = con.prepareStatement(query);: Se prepara la consulta SQL.
ResultSet rs = stmt.executeQuery();: Se ejecuta la consulta y se obtiene el conjunto de resultados (ResultSet).
java
Copiar código
while (rs.next()) {
    String cedula = rs.getString("cedula");
    String nombre = rs.getString("nombre");
    int b1 = rs.getInt("b1");
    int b2 = rs.getInt("b2");
    model.addRow(new Object[]{cedula, nombre, b1, b2});
}
while (rs.next()): Itera a través de las filas devueltas por la consulta SQL.
rs.getString(...): Obtiene los valores de las columnas (cedula, nombre, b1 y b2) para cada fila.
model.addRow(new Object[]{...}): Agrega los datos obtenidos a la tabla (en forma de una fila).
java
Copiar código
rs.close();
stmt.close();
con.close();
rs.close(), stmt.close(), con.close(): Cierra el ResultSet, el PreparedStatement y la conexión a la base de datos para liberar recursos.
java
Copiar código
table = new JTable(model);
scrollPane = new JScrollPane(table);
table.setFillsViewportHeight(true);
add(scrollPane, BorderLayout.CENTER);
table = new JTable(model);: Crea una nueva JTable con el modelo de datos que contiene las filas y columnas.
scrollPane = new JScrollPane(table);: Coloca la tabla dentro de un JScrollPane para agregar barras de desplazamiento si es necesario.
table.setFillsViewportHeight(true);: Hace que la tabla ocupe todo el espacio disponible en el JScrollPane.
add(scrollPane, BorderLayout.CENTER);: Agrega el JScrollPane al centro de la ventana.

 1.2 Login

 ![image](https://github.com/user-attachments/assets/f5f0c688-fc32-4ab9-a91e-9a52cac0e0e6)
 ![image](https://github.com/user-attachments/assets/7a973ae8-23eb-4116-8a05-5249a2e844da)
 ![image](https://github.com/user-attachments/assets/e6ccf37c-da6c-45ca-b186-78d3d5fffdf1)
 ![image](https://github.com/user-attachments/assets/5389f94e-4b74-46c9-a136-4e523b3d7e41)
 ![image](https://github.com/user-attachments/assets/b376f033-2934-47ff-98d0-a205c9d2b136)
Este código implementa una ventana de inicio de sesión (Login) con un formulario donde el usuario puede ingresar su nombre de usuario y contraseña. Si las credenciales son correctas, se muestra una ventana de bienvenida (BienvenidaVentana), y si son incorrectas, se muestra un mensaje de error.

Librerías importadas:
javax.swing.*:

JTextField: Es un campo de texto donde el usuario puede ingresar datos (en este caso, el nombre de usuario).
JPasswordField: Es un campo de texto especializado para ingresar contraseñas, donde los caracteres son ocultados por asteriscos.
JButton: Es un botón que, cuando se hace clic, ejecuta una acción.
JLabel: Es un componente que muestra texto, utilizado para mostrar etiquetas como "Usuario" y "Contraseña".
JPanel: Es un contenedor que organiza los componentes en la interfaz gráfica.
JOptionPane: Se utiliza para mostrar cuadros de diálogo (como mensajes de alerta o confirmación).
java.awt.event.*:

ActionEvent y ActionListener: Son usados para detectar y manejar eventos de acción, como el clic en un botón.
Atributos:
logintxt: Es un campo de texto (JTextField) donde el usuario ingresa el nombre de usuario.
passwordtxt: Es un campo de texto (JPasswordField) donde el usuario ingresa la contraseña.
OKButton: Es un botón que el usuario puede presionar para realizar el inicio de sesión.
userLabel, passLabel: Son etiquetas de texto (JLabel) que indican los campos de entrada de usuario y contraseña.
login: Es un panel (JPanel) que contiene todos los elementos gráficos (campos de texto, etiquetas, botones).
Constantes de usuario y contraseña:
java
Copiar código
private static final String USUARIO = "profesor";
private static final String CONTRASENA = "contrasena123";
Aquí se definen las credenciales predeterminadas para el inicio de sesión. En este caso, el usuario es "profesor" y la contraseña es "contrasena123".
Constructor Login:
java
Copiar código
public Login() {
    login = new JPanel();
    login.setLayout(null);
Se crea un panel (JPanel) donde se colocarán los componentes de la interfaz gráfica. El método setLayout(null) establece que no se usará ningún administrador de diseño automático y se colocarán los componentes manualmente usando coordenadas absolutas.
Etiquetas, campos de texto y botón:
java
Copiar código
// Etiqueta de Usuario
userLabel = new JLabel("Usuario = Profesor:");
userLabel.setBounds(50, 20, 200, 30);
login.add(userLabel);
userLabel: Muestra el texto "Usuario = Profesor:". Se establece en la posición (50, 20) con un tamaño de 200x30 píxeles en el panel.
java
Copiar código
// Campo de texto para el nombre
logintxt = new JTextField();
logintxt.setBounds(50, 50, 200, 30);
login.add(logintxt);
logintxt: Es el campo de texto donde el usuario puede escribir su nombre de usuario. Está ubicado en las coordenadas (50, 50) con un tamaño de 200x30 píxeles.
java
Copiar código
// Etiqueta de Contraseña
passLabel = new JLabel("Contraseña:");
passLabel.setBounds(50, 100, 200, 30);
login.add(passLabel);
passLabel: Muestra la etiqueta "Contraseña:". Está ubicada en (50, 100) con un tamaño de 200x30 píxeles.
java
Copiar código
// Campo de texto para la contraseña
passwordtxt = new JPasswordField();
passwordtxt.setBounds(50, 130, 200, 30);
login.add(passwordtxt);
passwordtxt: Es el campo de contraseña donde el usuario ingresa su clave. Está en las coordenadas (50, 130) con un tamaño de 200x30 píxeles.
java
Copiar código
// Botón OK
OKButton = new JButton("OK");
OKButton.setBounds(50, 180, 200, 30);
login.add(OKButton);
OKButton: Es un botón con el texto "OK" que el usuario presiona para intentar iniciar sesión. Está ubicado en las coordenadas (50, 180) con un tamaño de 200x30 píxeles.
Acción del botón OKButton:
java
Copiar código
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
addActionListener: Registra un escuchador de eventos para detectar cuando el usuario hace clic en el botón "OK".
Dentro del método actionPerformed:
Se obtienen los valores ingresados por el usuario en los campos de texto:
usuario con logintxt.getText()
contrasena con new String(passwordtxt.getPassword()) (esto es necesario para convertir el tipo char[] de JPasswordField a un String).
Se verifica si alguno de los campos está vacío, mostrando un mensaje de advertencia si es el caso.
Se compara el usuario y contraseña ingresados con los valores predefinidos:
Si son correctos, muestra un mensaje de "Inicio de sesión exitoso", luego abre la ventana de bienvenida y cierra la ventana de inicio de sesión.
Si son incorrectos, muestra un mensaje de "Usuario o contraseña incorrectos".
Método getLoginPanel():
java
Copiar código
public JPanel getLoginPanel() {
    return login;
}
Este método devuelve el panel de inicio de sesión (login), que contiene todos los componentes gráficos. Esto es útil si se quiere agregar este panel a un marco (JFrame) en otro lugar del código.


 1.3 Conexion BDD

 ![image](https://github.com/user-attachments/assets/2ef6dda5-1cb0-4ab2-a5c9-67b380729a67)

La clase ConexionBdd se encarga de establecer una conexión con una base de datos MySQL desde una aplicación Java. En este caso, la base de datos a la que se conecta es estudiantes2024b, utilizando el usuario root y la contraseña 123456.

Librerías importadas:
java.sql.*:
Connection: Es la interfaz que representa una conexión con una base de datos.
DriverManager: Es la clase que maneja las conexiones a las bases de datos. Se usa para obtener una conexión con un URL de la base de datos, el usuario y la contraseña.
SQLException: Es una excepción que se lanza cuando ocurre un error relacionado con la base de datos, como problemas con la conexión.
Class.forName: Es un método utilizado para cargar dinámicamente la clase del driver JDBC.
Método getConnection:
Este método establece la conexión a la base de datos y devuelve un objeto Connection, que se puede usar para interactuar con la base de datos.

java
Copiar código
public static Connection getConnection() {
    try {
        // Cargar el driver JDBC para MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Intentar obtener la conexión a la base de datos
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/estudiantes2024b", "root", "123456");

    } catch (Exception e) {
        // Si ocurre un error, imprimir la traza de la excepción
        e.printStackTrace();

        // Si hay un error, retornar null
        return null;
    }
}
Explicación del código:
Class.forName("com.mysql.cj.jdbc.Driver"):

Esta línea carga el driver JDBC de MySQL en el entorno de ejecución de Java. JDBC es un API que permite a las aplicaciones Java interactuar con bases de datos. En este caso, el driver es para MySQL, y el nombre de la clase es "com.mysql.cj.jdbc.Driver", que es el controlador para bases de datos MySQL.
La llamada a Class.forName asegura que la clase del driver esté registrada correctamente y lista para usarse.
DriverManager.getConnection("jdbc:mysql://localhost:3306/estudiantes2024b", "root", "123456"):

Aquí se utiliza DriverManager para establecer una conexión a la base de datos.
"jdbc:mysql://localhost:3306/estudiantes2024b" es la URL de conexión de la base de datos. Esta URL especifica el protocolo jdbc:mysql, el servidor localhost, el puerto 3306 (por defecto para MySQL) y el nombre de la base de datos (estudiantes2024b).
"root" es el nombre de usuario para la base de datos, y "123456" es la contraseña.
Si la conexión se establece correctamente, se retorna un objeto Connection, que permite interactuar con la base de datos (realizar consultas, inserciones, actualizaciones, etc.).
Manejo de excepciones:

Si ocurre algún error durante la carga del driver o la conexión a la base de datos (por ejemplo, si el servidor de base de datos no está en funcionamiento o las credenciales son incorrectas), se captura la excepción con catch (Exception e).
El método e.printStackTrace() imprime la traza del error en la consola, lo que puede ser útil para diagnosticar problemas.
Si ocurre un error, el método retorna null, lo que indica que la conexión no fue exitosa.

1.4 Formulario
![image](https://github.com/user-attachments/assets/b24762b9-117c-4772-8712-a64ca26b16e9)

La clase Formulario es una ventana de tipo JFrame que muestra un mensaje de bienvenida personalizado. El mensaje se adapta dinámicamente para mostrar el nombre del usuario que se pasa como parámetro al crear la ventana.

Librerías importadas:
javax.swing.*:
JFrame: Es una clase de la biblioteca Swing que representa una ventana principal de una aplicación gráfica.
JLabel: Es una clase de Swing que permite crear etiquetas de texto, como las que muestran mensajes en una ventana.
SwingConstants: Es una clase que contiene constantes de Swing. En este caso, se utiliza para alinear el texto de la etiqueta en el centro de la ventana.
Constructor Formulario(String usuario):
Este es el constructor de la clase Formulario. Recibe un parámetro usuario, que es un String que representa el nombre del usuario y se utilizará para personalizar el mensaje de bienvenida.

java
Copiar código
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
Explicación del código:
setTitle("Formulario Principal"):

Establece el título de la ventana (la barra superior donde se muestra el nombre de la ventana) como "Formulario Principal".
setSize(400, 300):

Establece el tamaño de la ventana. En este caso, la ventana será de 400 píxeles de ancho y 300 píxeles de alto.
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE):

Establece el comportamiento de la ventana cuando se cierra. JFrame.EXIT_ON_CLOSE hace que la aplicación termine cuando la ventana se cierra.
setLocationRelativeTo(null):

Centra la ventana en la pantalla. El null como parámetro significa que la ventana se centrará en relación con la pantalla completa.
bienvenidaLabel = new JLabel("Bienvenido, " + usuario):

Crea una nueva etiqueta de texto (JLabel) que mostrará el mensaje de bienvenida. La cadena "Bienvenido, " se concatena con el nombre del usuario recibido como parámetro en el constructor, personalizando el mensaje.
bienvenidaLabel.setHorizontalAlignment(SwingConstants.CENTER):

Ajusta la alineación del texto dentro de la etiqueta. SwingConstants.CENTER alinea el texto horizontalmente en el centro de la etiqueta.
add(bienvenidaLabel):

Agrega la etiqueta bienvenidaLabel a la ventana (JFrame). Esto hace que la etiqueta sea visible en la interfaz de usuario.

1.5 Main

![image](https://github.com/user-attachments/assets/62a8120c-968b-49ec-9e3a-9cbca0aec92d)

La clase Main es la clase principal que se utiliza para iniciar la aplicación. Dentro de su método main, crea una ventana para el login utilizando la clase Login que previamente definiste. Esta ventana permite al usuario ingresar su nombre de usuario y contraseña para acceder a la aplicación.

Librerías importadas:
javax.swing.*:
JFrame: Es una clase de Swing que representa una ventana principal de una aplicación gráfica.
Método main:
Este es el método principal de la clase, que se ejecuta cuando inicias la aplicación.

java
Copiar código
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
Explicación del código:
JFrame frame = new JFrame("Login"):

Crea una nueva ventana (JFrame) con el título "Login". Este será el contenedor principal de la interfaz de usuario.
frame.setContentPane(new Login().getLoginPanel()):

Se crea una nueva instancia de la clase Login y se llama al método getLoginPanel(). Este método retorna el panel de login (JPanel) que contiene los campos para el usuario y la contraseña, y el botón para iniciar sesión.
Luego, se establece este panel como el contenido de la ventana (frame.setContentPane(...)), lo que coloca todos los componentes de login en la ventana.
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE):

Establece la acción que debe realizarse cuando la ventana se cierra. JFrame.EXIT_ON_CLOSE asegura que la aplicación termine cuando el usuario cierre la ventana de login.
frame.pack():

Ajusta el tamaño de la ventana para que se ajuste automáticamente al tamaño preferido de sus componentes. Esto asegura que la ventana tenga el tamaño adecuado para mostrar todos los elementos (como los campos de texto y el botón).
frame.setLocationRelativeTo(null):

Centra la ventana en la pantalla. El null indica que la ventana se centra respecto a la pantalla completa (en lugar de otra ventana).
frame.setVisible(true):

Hace que la ventana sea visible en la pantalla. Al llamar a este método, se muestra la ventana con el formulario de login.

2.Funacionamiento

2.1 Ventana Login
![image](https://github.com/user-attachments/assets/daafc9fd-b850-4718-bd1c-5064754c511d)

2.2 Ventana emergente
![image](https://github.com/user-attachments/assets/d453b010-d684-4554-b3c6-b91bd0a1a7a8)

2.3 visuzalizacion de datos
![image](https://github.com/user-attachments/assets/431ae9bd-0233-4686-ad8b-f2ce02d4e574)
