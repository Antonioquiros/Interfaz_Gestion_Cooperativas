package controlador;


import Clases.Cliente;
import Clases.ClienteConsulta;
import Clases.Cooperativa;
import Clases.CoopConsulta;
import java.io.FileNotFoundException;
import java.io.InputStream;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Molina
 */
public class ControladorLibros implements Initializable {

    Connection conexion;

    @FXML
    private TabPane VentanaPrincipal;

    @FXML
    private Tab irCoop;

    @FXML
    private Tab irCliente;

    @FXML
    private Tab irConsulta;

    @FXML
    private AnchorPane VentanaCoop;

    @FXML
    private AnchorPane ventanaCliente;

    @FXML
    private AnchorPane ventanaConsulta;

    @FXML
    private ListView formCoop;
    
    @FXML
    private TextField idCooptxt;

    @FXML
    private TextField ciudadCooptxt;

    @FXML
    private TextField nombreCooptxt;

    @FXML
    private TextField horariotxt;
    
    @FXML
    private TextField KgTotaltxt;
    
    @FXML
    private TableView<Cooperativa> tablaCoop;
    
   @FXML 
    private TableColumn<Cooperativa, Integer> IdCooperativa;
    
    @FXML
    private TableColumn<Cooperativa, String> ciudad;
    
    @FXML
    private TableColumn<Cooperativa, String> NombreCoop;
    
    @FXML
    private TableColumn<Cooperativa, Double> Kgtotales;
    
    @FXML
    private TableColumn<Cooperativa, String> Horario;
    
    @FXML
    private Button aniadirCoop;
    
    @FXML
    private Button eliminarCoop;
    
    @FXML
    private Button actCoop;
    
    @FXML
    private ListView formCliente;
    
    @FXML
    private TextField idClientetxt;

    @FXML
    private TextField nombreClientetxt;

    @FXML
    private TextField apellidosClientetxt;

    @FXML
    private TextField DNIclientetxt;
    
    @FXML
    private TextField telefonoClientetxt;
    
    @FXML
    private TableView<Cliente> tablaCliente;
    
    @FXML 
    private TableColumn<Cliente, Integer> IdCliente;
    
    @FXML
    private TableColumn<Cliente, String> NombreCliente;
    
    @FXML
    private TableColumn<Cliente, String> apellidos;
    
    @FXML
    private TableColumn<Cliente, String> DNI;
    
    @FXML
    private TableColumn<Cliente, Integer> telefono;
    
    @FXML
    private Button aniadirCliente;
    
    @FXML
    private Button eliminarCliente;
    
    @FXML
    private Button actCliente;
    
    @FXML
    private ListView formConsultas;
    
    @FXML
    private Button ConsultarClienteBoton;
    
    @FXML 
    private Button ConsultarCoopBoton;
    
    @FXML
    private Button AgregarKgBoton;
    
    @FXML
    private TableView mostrarDatosCoop;
    
    @FXML
    private TableView mostrarDatosCient;
    
    @FXML
    private TableColumn idCooperativakg;
    
    @FXML
    private TableColumn Ciudadkg;
    
    @FXML
    private TableColumn NombreCoopkg;
    
    @FXML
    private TableColumn Horariokg;
    
    @FXML
    private TableColumn Kgtotaleskg;
    
    @FXML
    private TableColumn kgCoop;
    
    @FXML
    private TableColumn idClientekg;
    
    @FXML
    private TableColumn nombreClientkg;
    
    @FXML
    private TableColumn Apellidoskg;
    
    @FXML
    private TableColumn DNIkg;
    
    @FXML
    private TableColumn Telefonokg;
    
    @FXML
    private TableColumn kg;
    
    
    
    
    
    
   @FXML
    void manual(KeyEvent event) {
        if(event.getCode() == KeyCode.F1){
            llamarAWeb();
        }
    } 
    //Se crean todos los metodos de las Cooperativas
    
    //Se crea el metodo para añadir cooperativas
    
    
   @FXML
private void aniadirCoop() {
    // Comprobación de campos vacíos
    if (ciudadCooptxt.getText().isEmpty() || nombreCooptxt.getText().isEmpty() || horariotxt.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, completa todos los campos.");
        alert.show();
        return; // Salir del método si algún campo está vacío
    }

    String query = "INSERT INTO cooperativa (Ciudad,Nombre,Horario) VALUES (?, ?, ?)";
    try {
        PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
        preparedStatement.setString(1, ciudadCooptxt.getText());
        preparedStatement.setString(2, nombreCooptxt.getText());
        preparedStatement.setString(3, horariotxt.getText());
        preparedStatement.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("La cooperativa se ha añadido correctamente");
        alert.show();
    } catch (SQLException e) {
        e.printStackTrace();
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Antonio Quiros Garcia");
        alerta.setHeaderText(null);
        alerta.setContentText("Comprueba que los datos estén bien introducidos");
        alerta.show();
    }
    idCooptxt.clear();
    ciudadCooptxt.clear();
    nombreCooptxt.clear();
    horariotxt.clear();
    KgTotaltxt.clear();
    mostrarDatosCoop();
}

    //Se crea el metodo para actualizar cooperativas
    @FXML
private void actualizarCoop() {
    // Comprobación de campos vacíos
    if (ciudadCooptxt.getText().isEmpty() || nombreCooptxt.getText().isEmpty() || horariotxt.getText().isEmpty() || idCooptxt.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, completa todos los campos.");
        alert.show();
        return; // Salir del método si algún campo está vacío
    }

    String query = "UPDATE cooperativa SET Ciudad=?,Nombre=?,Horario=? WHERE idCooperativa=?";
    try {
        PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
        preparedStatement.setString(1, ciudadCooptxt.getText());
        preparedStatement.setString(2, nombreCooptxt.getText());
        preparedStatement.setString(3, horariotxt.getText());
        preparedStatement.setInt(4, Integer.parseInt(idCooptxt.getText()));
        
        preparedStatement.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("La cooperativa se ha actualizado correctamente");
        alert.show();
    } catch (SQLException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("Comprueba que los datos estén bien introducidos");
        alert.show();
    }
    idCooptxt.clear();
    ciudadCooptxt.clear();
    nombreCooptxt.clear();
    horariotxt.clear();
    KgTotaltxt.clear();
    mostrarDatosCoop();
}

    
@FXML
private void eliminarCoop() {
    // Verificar si los campos están vacíos
    if (ciudadCooptxt.getText().isEmpty() || nombreCooptxt.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, completa todos los campos.");
        alert.show();
        return; // Salir del método si algún campo está vacío
    }

    String queryVerificacion = "SELECT idCooperativa FROM cooperativa WHERE Ciudad=? AND Nombre=?";
    String queryEliminarConsultas = "DELETE FROM consultas WHERE idCooperativa=?";
    String queryEliminacion = "DELETE FROM cooperativa WHERE idCooperativa=?";
    
    try {
        // Verificar si existe una cooperativa con los datos proporcionados
        PreparedStatement verificacionStatement = this.conexion.prepareStatement(queryVerificacion);
        verificacionStatement.setString(1, ciudadCooptxt.getText());
        verificacionStatement.setString(2, nombreCooptxt.getText());
        ResultSet resultSet = verificacionStatement.executeQuery();
        
        if (resultSet.next()) { // Si hay una coincidencia en la base de datos
            int idCooperativa = resultSet.getInt("idCooperativa");
            
            // PRIMERO: Eliminar todos los registros de consultas que referencian esta cooperativa
            PreparedStatement eliminarConsultasStatement = this.conexion.prepareStatement(queryEliminarConsultas);
            eliminarConsultasStatement.setInt(1, idCooperativa);
            int registrosEliminados = eliminarConsultasStatement.executeUpdate();
            
            // LUEGO: Eliminar la cooperativa
            PreparedStatement eliminacionStatement = this.conexion.prepareStatement(queryEliminacion);
            eliminacionStatement.setInt(1, idCooperativa);
            eliminacionStatement.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Antonio Quiros Garcia");
            alert.setHeaderText(null);
            alert.setContentText("La cooperativa y sus " + registrosEliminados + " registros de consultas se han eliminado correctamente");
            alert.show();
            
            // Limpiar los campos de entrada
            idCooptxt.setText("");
            ciudadCooptxt.setText("");
            nombreCooptxt.setText("");
            horariotxt.setText("");
            KgTotaltxt.setText("");
        } else { // Si no hay coincidencias en la base de datos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Antonio Quiros Garcia");
            alert.setHeaderText(null);
            alert.setContentText("La información proporcionada no coincide con ninguna cooperativa en la base de datos.");
            alert.show();
        }
    } catch (SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("Error al eliminar la cooperativa: " + e.getMessage());
        alert.show();
        e.printStackTrace();
    }
    mostrarDatosCoop();
}

    //Se crean todos los metodos de los clientes
    //Se crea el metodo para añadir clientes
    @FXML
private void aniadirCliente() {
    // Comprobación de campos vacíos
    if (!datosUsuarioValidos()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, completa todos los campos.");
        alert.show();
        return; // Salir del método si algún campo está vacío
    }

    String query = "INSERT INTO cliente (nombre,apellido,dni,telefono) VALUES (?, ?, ?, ?)";
    try {
        PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
        preparedStatement.setString(1, nombreClientetxt.getText());
        preparedStatement.setString(2, apellidosClientetxt.getText());
        preparedStatement.setString(3, DNIclientetxt.getText());
        preparedStatement.setInt(4, Integer.parseInt(telefonoClientetxt.getText()));
        preparedStatement.executeUpdate();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("El cliente se ha añadido correctamente");
        alert.show();
        
    } catch (SQLException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("Comprueba que los datos estén bien introducidos");
        alert.show();
    }
     nombreClientetxt.clear();
    apellidosClientetxt.clear();
    DNIclientetxt.clear();
    telefonoClientetxt.clear();
    mostrarDatosCliente();
}

    //Se crea el metodo de actualizar clientes
    @FXML
private void actualizarCliente() {
    // Comprobación de campos vacíos
    if (!datosUsuarioValidos()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, completa todos los campos.");
        alert.show();
        return; // Salir del método si algún campo está vacío
    }

    String query = "UPDATE cliente SET nombre=?,apellido=?,dni=?,telefono=? WHERE idCliente=?";
    try {
        PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
        preparedStatement.setString(1, nombreClientetxt.getText());
        preparedStatement.setString(2, apellidosClientetxt.getText());
        preparedStatement.setString(3, DNIclientetxt.getText());
        preparedStatement.setInt(4, Integer.parseInt(telefonoClientetxt.getText()));
        preparedStatement.setInt(5, Integer.parseInt(idClientetxt.getText()));
        preparedStatement.executeUpdate();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("El cliente se ha actualizado correctamente");
        alert.show();
    } catch (SQLException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("Comprueba que los datos estén bien introducidos");
        alert.show();
    }
    idClientetxt.clear();
    nombreClientetxt.clear();
    apellidosClientetxt.clear();
    DNIclientetxt.clear();
    telefonoClientetxt.clear();
    mostrarDatosCliente();
}

    //Se crea el metodo de eliminar clientes
     @FXML
private void eliminarCliente() {
    // Verificar si los campos están vacíos
    if (!datosUsuarioValidos()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, completa todos los campos.");
        alert.show();
        return; // Salir del método si algún campo está vacío
    }

    String queryVerificacion = "SELECT * FROM cliente WHERE nombre=? AND apellido=? AND dni=? AND telefono=?";
    String queryEliminacion = "DELETE FROM cliente WHERE nombre=? AND apellido=? AND dni=? AND telefono=?";

    try {
        // Verificar si existe un cliente con los datos proporcionados
        PreparedStatement verificacionStatement = this.conexion.prepareStatement(queryVerificacion);
        verificacionStatement.setString(1, nombreClientetxt.getText());
        verificacionStatement.setString(2, apellidosClientetxt.getText());
        verificacionStatement.setString(3, DNIclientetxt.getText());
        verificacionStatement.setInt(4, Integer.parseInt(telefonoClientetxt.getText()));
        
        ResultSet resultSet = verificacionStatement.executeQuery();
        
        if (resultSet.next()) { // Si hay una coincidencia en la base de datos
            // Eliminar el cliente
            PreparedStatement eliminacionStatement = this.conexion.prepareStatement(queryEliminacion);
            eliminacionStatement.setString(1, nombreClientetxt.getText());
            eliminacionStatement.setString(2, apellidosClientetxt.getText());
            eliminacionStatement.setString(3, DNIclientetxt.getText());
            eliminacionStatement.setInt(4, Integer.parseInt(telefonoClientetxt.getText()));
            eliminacionStatement.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Antonio Quiros Garcia");
            alert.setHeaderText(null);
            alert.setContentText("El cliente se ha eliminado correctamente");
            alert.show();
            
            // Limpiar los campos de entrada
            idClientetxt.setText("");
            nombreClientetxt.setText("");
            apellidosClientetxt.setText("");
            DNIclientetxt.setText("");
            telefonoClientetxt.setText("");
        } else { // Si no hay coincidencias en la base de datos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Antonio Quiros Garcia");
            alert.setHeaderText(null);
            alert.setContentText("La información proporcionada no coincide con ningún cliente en la base de datos.");
            alert.show();
        }
    } catch (SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Antonio Quiros Garcia");
        alert.setHeaderText(null);
        alert.setContentText("Comprueba que los datos estén bien introducidos");
        alert.show();
        e.printStackTrace();
    }
    mostrarDatosCliente();
}

    //Se crea el metodo para añadir kg a una cooperativa
   @FXML
private void agregarKg() {

    Label labelKg = new Label("Kg:");
    TextField textFieldKg = new TextField();
    Label labelidClient = new Label("IdCliente:");
    TextField textFieldidClient = new TextField();
    Label labelidCoop = new Label("IdCooperativa:");
    TextField textFieldidCoop = new TextField();
    Button buttonEnviar = new Button("Enviar");

    // Agregamos los controles al contenedor
    VBox vbox = new VBox();
    vbox.getChildren().addAll(labelKg, textFieldKg, labelidClient, textFieldidClient, labelidCoop, textFieldidCoop, buttonEnviar);

    // Creamos la escena
    Scene scene = new Scene(vbox, 300, 165);

    // Establecemos la escena en el escenario
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Antonio Quiros Garcia");

    // Mostramos el escenario
    stage.show();

    // Eventos del botón Enviar
    buttonEnviar.setOnAction(event -> {
        // Verificar si el idCliente existe
        String consultaCliente = "SELECT COUNT(idCliente) FROM cliente WHERE idCliente = ?";
        int cantidadCliente = -1;
        try {
            PreparedStatement preparedStatementCliente = this.conexion.prepareStatement(consultaCliente);
            preparedStatementCliente.setInt(1, Integer.parseInt(textFieldidClient.getText()));
            ResultSet resultSetCliente = preparedStatementCliente.executeQuery();
            if (resultSetCliente.next()) {
                cantidadCliente = resultSetCliente.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Verificar si el idCooperativa existe
        String consultaCoop = "SELECT COUNT(idCooperativa) FROM cooperativa WHERE idCooperativa = ?";
        int cantidadCoop = -1;
        try {
            PreparedStatement preparedStatementCoop = this.conexion.prepareStatement(consultaCoop);
            preparedStatementCoop.setInt(1, Integer.parseInt(textFieldidCoop.getText()));
            ResultSet resultSetCoop = preparedStatementCoop.executeQuery();
            if (resultSetCoop.next()) {
                cantidadCoop = resultSetCoop.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (cantidadCliente > 0 && cantidadCoop > 0) {
            // El idCliente y el idCooperativa existen
            int idClient = Integer.parseInt(textFieldidClient.getText());
            int idCoope = Integer.parseInt(textFieldidCoop.getText());
            double cantidadKg = Double.parseDouble(textFieldKg.getText());

            try {
                String query = "UPDATE cooperativa SET kgTotales = kgTotales + ? WHERE idCooperativa = ?";
                PreparedStatement preparedStatement = this.conexion.prepareStatement(query);
                preparedStatement.setDouble(1, cantidadKg);
                preparedStatement.setInt(2, idCoope);
                preparedStatement.executeUpdate();

                String selectQuery = "SELECT * FROM consultas WHERE idCliente = ? AND idCooperativa = ?";
                PreparedStatement psSelect = this.conexion.prepareStatement(selectQuery);
                psSelect.setInt(1, idClient);
                psSelect.setInt(2, idCoope);
                ResultSet rs = psSelect.executeQuery();

                if (rs.next()) {
                    // Si ya existe una consulta, actualizamos el campo kg
                    String updateQuery = "UPDATE consultas SET kg = kg + ? WHERE idCliente = ? AND idCooperativa = ?";
                    PreparedStatement psUpdate = this.conexion.prepareStatement(updateQuery);
                    psUpdate.setDouble(1, cantidadKg);
                    psUpdate.setInt(2, idClient);
                    psUpdate.setInt(3, idCoope);
                    psUpdate.executeUpdate();
                } else {
                    // Si no existe, insertamos una nueva fila en la tabla consultas
                    String insertQuery = "INSERT INTO consultas (idCliente, idCooperativa, kg) VALUES (?, ?, ?)";
                    PreparedStatement psInsert = this.conexion.prepareStatement(insertQuery);
                    psInsert.setInt(1, idClient);
                    psInsert.setInt(2, idCoope);
                    psInsert.setDouble(3, cantidadKg);
                    psInsert.executeUpdate();
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Antonio Quiros Garcia");
                alert.setHeaderText(null);
                alert.setContentText("Los kg se han añadido correctamente");
                alert.show();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Antonio Quiros Garcia");
                alert.setHeaderText(null);
                alert.setContentText("Comprueba que los datos estén bien introducidos");
                alert.show();
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Antonio Quiros Garcia");
            alert.setHeaderText(null);
            if (cantidadCliente <= 0 && cantidadCoop <= 0) {
                alert.setContentText("El cliente y la cooperativa que has introducido no existen");
            } else if (cantidadCliente <= 0) {
                alert.setContentText("El cliente que has introducido no existe");
            } else {
                alert.setContentText("La cooperativa que has introducido no existe");
            }
            alert.show();
        }
        mostrarDatosCoop();
    });
}

@FXML
private void ConsultarCoop() {
    Label labelId = new Label("Id de la Cooperativa:");
    TextField textFieldId = new TextField();
    Button buttonEnviar = new Button("Enviar");

    // Agregamos los controles al contenedor
    VBox vbox = new VBox();
    vbox.getChildren().addAll(labelId, textFieldId, buttonEnviar);

    // Creamos la escena
    Scene scene = new Scene(vbox, 300, 90);

    // Establecemos la escena en el escenario
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Antonio Quiros Garcia");

    // Mostramos el escenario
    stage.show();

    // Eventos del botón Enviar
    buttonEnviar.setOnAction(event -> {
        // Obtenemos los valores de los controles
        int id = Integer.parseInt(textFieldId.getText());
        String consultaExistencia = "SELECT COUNT(idCooperativa) FROM cooperativa WHERE idCooperativa LIKE ?";
        int cantidad = -1;
        try {
            PreparedStatement preparedStatement = this.conexion.prepareStatement(consultaExistencia);
            preparedStatement.setString(1, textFieldId.getText());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cantidad = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (cantidad > 0) {
            // Verificar si kgTotales es igual a 0.0
            String consultaKgTotales = "SELECT kgtotales FROM cooperativa WHERE idCooperativa = ?";
            double kgTotales = 0.0;
            try {
                PreparedStatement psKgTotales = this.conexion.prepareStatement(consultaKgTotales);
                psKgTotales.setInt(1, id);
                ResultSet rsKgTotales = psKgTotales.executeQuery();

                if (rsKgTotales.next()) {
                    kgTotales = rsKgTotales.getDouble("kgtotales");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (kgTotales == 0.0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Antonio Quiros Garcia");
                alert.setHeaderText(null);
                alert.setContentText("Esta cooperativa no ha cargado aceituna aún");
                alert.show();
            } else {
                // Continuar con la consulta y mostrar datos en tablas
                String query = "SELECT COOP.idCooperativa, COOP.ciudad, COOP.nombre AS nombreCooperativa, COOP.horario, COOP.kgtotales, 0 AS kg FROM cooperativa COOP WHERE COOP.idCooperativa = ?;";
                String consult = "SELECT C.idCliente, C.nombre, C.apellido, C.dni, C.telefono, CO.kg FROM cliente C JOIN consultas CO ON C.idCliente = CO.idCliente WHERE CO.idCooperativa = ?;";
                ObservableList<CoopConsulta> lista = FXCollections.observableArrayList();
                ObservableList<ClienteConsulta> listaC = FXCollections.observableArrayList();
                try {
                    PreparedStatement ps = conexion.prepareStatement(query);
                    PreparedStatement pr = conexion.prepareStatement(consult);

                    ps.setInt(1, id);
                    pr.setInt(1, id);

                    // Se ejecuta la consulta
                    ResultSet rs = ps.executeQuery();
                    ResultSet rt = pr.executeQuery();

                    // Se recorren los resultados de la consulta
                    if (rs.next()) {
                        do {
                            CoopConsulta coop = new CoopConsulta(
                                    rs.getInt("idCooperativa"),
                                    rs.getString("ciudad"),
                                    rs.getString("nombrecooperativa"),
                                    rs.getString("horario"),
                                    rs.getDouble("Kgtotales"),
                                    rs.getDouble("kg")
                            );
                            lista.add(coop);
                        } while (rs.next());
                        mostrarDatosTablaCoop(lista);

                        if (rt.next()) {
                            do {
                                ClienteConsulta cliente = new ClienteConsulta(
                                        rt.getInt("idCliente"),
                                        rt.getString("nombre"),
                                        rt.getString("apellido"),
                                        rt.getString("dni"),
                                        rt.getInt("telefono"),
                                        rt.getDouble("kg")
                                );
                                listaC.add(cliente);

                            } while (rt.next());
                            mostrarDatosEnTabla(listaC);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Antonio Quiros Garcia");
            alert.setHeaderText(null);
            alert.setContentText("El id que has introducido no existe");
            alert.show();
        }
        stage.close();
    });
}

@FXML
private void ConsultarCliente(){
    
    Label labelid = new Label("id del cliente:");
    TextField textFieldid = new TextField();
    Button buttonEnviar = new Button("Enviar");
    // Agregamos los controles al contenedor
    VBox vbox = new VBox();
    vbox.getChildren().addAll( labelid, textFieldid, buttonEnviar);

    // Creamos la escena
    Scene scene = new Scene(vbox, 300, 90);

    // Establecemos la escena en el escenario
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Antonio Quiros Garcia");

    // Mostramos el escenario
    stage.show();

    // Eventos del botón Enviar
    buttonEnviar.setOnAction(event -> {
        // Obtenemos los valores de los controles
        int id = Integer.parseInt(textFieldid.getText());
        String consulta = "SELECT COUNT(idCliente) FROM cliente WHERE idCliente LIKE ?";
        int cantidad = -1;
        try {
            PreparedStatement preparedStatement = this.conexion.prepareStatement(consulta);
            preparedStatement.setString(1, textFieldid.getText());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cantidad = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (cantidad > 0) {
             String query = "SELECT C.idCliente, C.nombre, C.apellido, C.dni, C.telefono, COALESCE(SUM(CONS.kg), 0) AS kg FROM cliente C LEFT JOIN consultas CONS ON CONS.idCliente = C.idCliente WHERE C.idCliente = ? GROUP BY C.idCliente, C.nombre, C.apellido, C.dni, C.telefono;";
             String consultCoop = "SELECT COOP.idCooperativa, COOP.ciudad, COOP.nombre AS nombreCooperativa, COOP.horario, COOP.Kgtotales, CONS.kg FROM consultas CONS JOIN cooperativa COOP ON CONS.idCooperativa = COOP.idCooperativa WHERE CONS.idCliente = ?;";
             ObservableList<CoopConsulta> listaCoop = FXCollections.observableArrayList();
             ObservableList<ClienteConsulta> lista = FXCollections.observableArrayList();
             try{
                 PreparedStatement ps = conexion.prepareStatement(query);
                 PreparedStatement pc = conexion.prepareStatement(consultCoop);

    // Se setea el valor del id en la consulta
    ps.setInt(1, id);
    pc.setInt(1, id);

    // Se ejecuta la consulta
    ResultSet rs = ps.executeQuery();
    ResultSet cs = pc.executeQuery();
             if (rs.next()) {
    do {
        ClienteConsulta cliente = new ClienteConsulta(
            rs.getInt("idCliente"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("dni"),
            rs.getInt("telefono"),
            rs.getDouble("kg")
        );
        lista.add(cliente);
    } while (rs.next());

    mostrarDatosEnTabla(lista);
    
    if (cs.next()) {
                    do {
                        CoopConsulta cooperativa = new CoopConsulta(
                                cs.getInt("idCooperativa"),
                                cs.getString("ciudad"),
                                cs.getString("nombreCooperativa"),
                                cs.getString("horario"),
                                cs.getDouble("Kgtotales"),
                                cs.getDouble("kg")
                        );
                        listaCoop.add(cooperativa);
                    } while (cs.next());

                    mostrarDatosTablaCoop(listaCoop);}
} else {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Antonio Quiros Garcia");
    alert.setHeaderText(null);
    alert.setContentText("Esta persona no ha recogido aceituna todavia");
    alert.show();
}
             }catch(SQLException e){
                 e.printStackTrace();
            }
        
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Antonio Quiros Garcia");
            alert.setHeaderText(null);
            alert.setContentText("""
                    El ID que has introducido no existe""");
            alert.show();
        }
        stage.close();
    });
            
}

private void mostrarDatosEnTabla(ObservableList<ClienteConsulta> lista) {
    idClientekg.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
    nombreClientkg.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    Apellidoskg.setCellValueFactory(new PropertyValueFactory<>("apellido"));
    DNIkg.setCellValueFactory(new PropertyValueFactory<>("dni"));
    Telefonokg.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    kg.setCellValueFactory(new PropertyValueFactory<>("kg"));
    
    mostrarDatosCient.setItems(lista);
   
}

private void mostrarDatosTablaCoop(ObservableList<CoopConsulta> listaCoop){
    idCooperativakg.setCellValueFactory(new PropertyValueFactory<>("idCooperativa"));
    Ciudadkg.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
    NombreCoopkg.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    Horariokg.setCellValueFactory(new PropertyValueFactory<>("horario"));
    Kgtotaleskg.setCellValueFactory(new PropertyValueFactory<>("Kgtotales"));
    kgCoop.setCellValueFactory(new PropertyValueFactory<>("kg"));
    
    mostrarDatosCoop.setItems(listaCoop);
}


 //Se crea el metodo para cargar los datos de la cooperativa en el formulario
    private void cargarDatosCoop() {
        // Obtener el elemento seleccionado en la tabla.
        Cooperativa selectedItem = tablaCoop.getSelectionModel().getSelectedItem();

        // Verificar si se seleccionó algo.
        if (selectedItem != null) {
            // Cargar los datos en los campos del formulario.
            idCooptxt.setText(String.valueOf(selectedItem.getIdCooperativa()));
            ciudadCooptxt.setText(selectedItem.getCiudad());
            nombreCooptxt.setText(selectedItem.getNombre());
            horariotxt.setText(selectedItem.getHorario());
            KgTotaltxt.setText(String.valueOf(selectedItem.getKgtotales()));
            
        }
    }
    //Se crea el metodo para cargar los datos de los clientes en el formulario
    private void cargarDatosCliente() {
        // Obtener el elemento seleccionado en la tabla.
        Cliente selectedItem = tablaCliente.getSelectionModel().getSelectedItem();

        // Verificar si se seleccionó algo.
        if (selectedItem != null) {
            // Cargar los datos en los campos del formulario.
            idClientetxt.setText(String.valueOf(selectedItem.getIdCliente()));
            nombreClientetxt.setText(selectedItem.getNombre());
            apellidosClientetxt.setText(selectedItem.getApellido());
            DNIclientetxt.setText(selectedItem.getDNI());
            telefonoClientetxt.setText(String.valueOf(selectedItem.getTelefono()));
            
        }
    }
    
        


    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    public Connection getConnection() {
    if (conexion != null) return conexion;

    try {
        conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost:33060/AppCooperativas?useSSL=false&serverTimezone=UTC",
            "root", "root"
        );
        System.out.println("Conexión establecida correctamente");
        return conexion;
    } catch (SQLException e) {
        System.out.println("Error SQL: " + e.getMessage());
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Ha ocurrido un error de conexión");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        return null;
    }
}

    public ObservableList<Cooperativa> dameListaCoop() {
        ObservableList<Cooperativa> listaCoop = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM cooperativa";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Cooperativa cooperativa;
                while (rs.next()) { //Se usan los identificadores propios en la BBDD
                    cooperativa = new Cooperativa(rs.getInt("idCooperativa"),rs.getString("Ciudad"),rs.getString("Nombre"),rs.getString("Horario"),rs.getDouble("Kgtotales"));
                    listaCoop.add(cooperativa);
                }
            } catch (SQLException e) {
            }
            return listaCoop;
            
        }
        return null;
    }
    public ObservableList<Cliente> dameListaCliente() {
        ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();
        Connection connection = getConnection();
        if (connection != null) {
            String query = "SELECT * FROM cliente";
            Statement st;
            ResultSet rs;

            try {
                st = connection.createStatement();
                rs = st.executeQuery(query);
                Cliente cliente;
                while (rs.next()) { //Se usan los identificadores propios en la BBDD
                    cliente = new Cliente(rs.getInt("idCliente"),rs.getString("Nombre"),rs.getString("Apellido"),rs.getString("DNI"),rs.getInt("Telefono"));
                    listaCliente.add(cliente);
                }
            } catch (SQLException e) {
            }
            return listaCliente;
        }
        return null;
    }

    public void mostrarDatosCoop() {
        tablaCoop.setItems(dameListaCoop());
    }
    public void mostrarDatosCliente(){
        tablaCliente.setItems(dameListaCliente());
    }
    
    
                

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tablaCoop.setOnMouseClicked(event -> cargarDatosCoop());
        tablaCliente.setOnMouseClicked(event -> cargarDatosCliente());
        this.conexion = this.getConnection();
        ObservableList<Cliente> listaC = dameListaCliente();
        ObservableList<Cooperativa> lista = dameListaCoop();

        //Los campos han de coincidir con los campos del objeto Libros
        
        IdCliente.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente"));
        NombreCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nombre"));
        apellidos.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Apellido"));
        DNI.setCellValueFactory(new PropertyValueFactory<Cliente, String>("DNI"));
        telefono.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("Telefono"));
        
        IdCooperativa.setCellValueFactory(new PropertyValueFactory<Cooperativa, Integer>("idCooperativa"));
        ciudad.setCellValueFactory(new PropertyValueFactory<Cooperativa, String>("Ciudad"));
        NombreCoop.setCellValueFactory(new PropertyValueFactory<Cooperativa, String>("Nombre"));
        Horario.setCellValueFactory(new PropertyValueFactory<Cooperativa, String>("Horario"));
        Kgtotales.setCellValueFactory(new PropertyValueFactory<Cooperativa, Double>("Kgtotales"));

        tablaCoop.setItems(lista);
        tablaCliente.setItems(listaC);

    }
    
    
    private void lanzarInforme(String rutaInf, Map hm) throws FileNotFoundException{
        try {
           InputStream inputS = getClass().getResourceAsStream(rutaInf);
        JasperReport report = (JasperReport) JRLoader.loadObject(inputS);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, hm, this.conexion);

            //String outputFile="informePDF.pdf";
            //JasperExportManager.exportReportToPdfFile(jasperPrint,outputFile);

            JRViewer jrViewer=new JRViewer(jasperPrint);
            // jrViewer.setPreferredSize(new Dimension((int) this.inform.getWidth(),(int) this.inform.getHeight()));
            SwingNode swingNode = new SwingNode();
            SwingUtilities.invokeLater(() -> swingNode.setContent(jrViewer));
            
            StackPane root = new StackPane();
            root.getChildren().add(swingNode);
            Scene scene = new Scene(root, 800, 600);

            Stage stage = new Stage();
            stage.setTitle("JavaFX JasperViewer");
            stage.setScene(scene);
            stage.show();
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void informe1(){
        try{
            Map<String, Object> parameters = new HashMap<>();
            lanzarInforme("/TrabajoNuevo/Informe.jasper",parameters);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void informe2(){
        
    try{
        Map<String, Object> parameters = new HashMap<>();
            lanzarInforme("/TrabajoNuevo/Informe2.jasper",parameters);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void informe3(){
        Label labelMin = new Label("Kg Totales Mínimos:");
        TextField textFieldMin = new TextField();
        Label labelMax = new Label("Kg Totales Máximos:");
        TextField textFieldMax = new TextField();
        Button button = new Button("Generar Informe");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(labelMin,textFieldMin,labelMax,textFieldMax,button);
        Scene scene = new Scene(vbox, 300, 120);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Antonio Quiros Garcia");
    stage.show();
    button.setOnAction(event ->{
        try{
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("minimo", Double.parseDouble(textFieldMin.getText()));
            System.out.println(textFieldMin.getText());
            parameters.put("maximo", Double.parseDouble(textFieldMax.getText()));
            System.out.println(textFieldMax.getText());
            lanzarInforme("/TrabajoNuevo/Informe3.jasper", parameters);
        }catch(Exception e){
            e.printStackTrace();
        }
    });
    }
    
    private void llamarAWeb(){
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        URL url = getClass().getResource("/Manual/manual.html");
        webEngine.load(url.toExternalForm());
        
        StackPane root = new StackPane();
        root.getChildren().add(webView);
        
        Scene scene = new Scene(root, 300, 250);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        stage.setScene(scene);
        stage.setTitle("Manual");
        stage.show();
    }
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido, String headerText) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(headerText);
        alert.setContentText(contenido);
        alert.show();
    }

    private boolean datosUsuarioValidos() {
        String numTelefono = telefonoClientetxt.getText();
        String titulo = "Error campos cliente";

        if (nombreClientetxt.getText().isEmpty()){
            mostrarAlerta(Alert.AlertType.ERROR,titulo ,"Nombre esta vacio",null );
        } else if(apellidosClientetxt.getText().isEmpty()){
            mostrarAlerta(Alert.AlertType.ERROR,titulo,"Apellido esta vacio",null );
        } else if(DNIclientetxt.getText().isEmpty() || DNIclientetxt.getText().length() != 9){
            mostrarAlerta(Alert.AlertType.ERROR,titulo,"Dni esta vacio o le faltan caracteres",null );
        } else if (!esNumerico(numTelefono)){
            mostrarAlerta(Alert.AlertType.ERROR,titulo,"El telefono debe ser numérico",null );
        } else if (numTelefono == null || numTelefono.length() != 9){
            mostrarAlerta(Alert.AlertType.ERROR,titulo,"Telefono requiere 9 carácteres",null );
        } else{
            return true;
        }

        return false;
    }

    private boolean esNumerico(String text){
        try{
            Integer.parseInt(text);
            return true;
        }catch(Exception e){
            return false;
        }

    }
    
// REPAIR TABLE cooperativa; Se ejecuta en phpmyadmin por si falla al añadir cooperativas
}
    

        
                