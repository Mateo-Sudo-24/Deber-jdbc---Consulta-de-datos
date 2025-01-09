(Todo el codigo se encuentra en la rama master)

1.Creacion de clases 
 1.1.Clase Login
 
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


