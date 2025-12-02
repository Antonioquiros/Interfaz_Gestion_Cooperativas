package controlador;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import Clases.Cliente;
import Clases.ClienteConsulta;
import Clases.Cooperativa;
import Clases.Consultas;
import Clases.CoopConsulta;
import Clases.OperacionCliente;
import apirest_ejemplo.apirest.MiAPIServicioActualizar;
import apirest_ejemplo.apirest.MiAPIServicioBorrar;
import apirest_ejemplo.apirest.MiAPIServicioInsertar;
import apirest_ejemplo.apirest.MiAPIServicioLeer;
import static com.fasterxml.jackson.databind.util.ClassUtil.name;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
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
import java.nio.file.Path;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author Molina
 */
public class ControladorLibros implements Initializable {

    Connection conexion;

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

    private Call<OperacionCliente> callLect;
    private Call<OperacionCliente> callIns;
    private Call<OperacionCliente> callBorr;
    private Call<OperacionCliente> callAct;
    private Label label;
    private Stage mistage;


    @FXML
    void manual(KeyEvent event) {
        if(event.getCode() == KeyCode.F1){
            llamarAWeb();
        }
    }
    //Se crean todos los metodos de los OperacionCliente
    //Se crea el metodo para añadir OperacionCliente
    @FXML
    private void aniadirCliente() {
        if(datosUsuarioValidos()) {
            OperacionCliente c = new OperacionCliente(nombreClientetxt.getText(),apellidosClientetxt.getText(),DNIclientetxt.getText(),Integer.parseInt(telefonoClientetxt.getText()));
            callIns = servicioIns.insertarDato(c);
            encolaInsercion(); //No sabemos cuanto va a tardar en insertarlo
        }
    }

    //Se crea el metodo de actualizar OperacionCliente
    @FXML
    private void actualizarCliente() {
        // Comprobación de campos vacíos
        if (datosUsuarioValidos()) {
             // Salir del método si algún campo está vacío
        
            OperacionCliente c = new OperacionCliente(Integer.parseInt(idClientetxt.getText()),nombreClientetxt.getText(),apellidosClientetxt.getText(),DNIclientetxt.getText(),Integer.parseInt(telefonoClientetxt.getText()));
            callAct = servicioAct.actualizarDato(c);
            encolaActualizar();
        }


    }

    //Se crea el metodo de eliminar OperacionCliente
    @FXML
    private void eliminarCliente() {
        // Verificar si los campos están vacíos
        if (idClientetxt.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR,"Antonio Quiros Garcia","Porfavor introduzca el id a borrar", null);
            return; // Salir del método si algún campo está vacío
        }else{
            OperacionCliente c = new OperacionCliente(Integer.parseInt(idClientetxt.getText()),nombreClientetxt.getText(),apellidosClientetxt.getText(),DNIclientetxt.getText(),Integer.parseInt(telefonoClientetxt.getText()));
            callBorr = servicioBorr.borrarDato(c);
            System.out.println("Prueba"+servicioBorr.toString());
            System.out.println("Borrando Cliente " + c);
            encolaBorrado();
        }
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

    //Se crea el metodo para cargar los datos de los OperacionCliente en el formulario
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
        Connection conn;
        /*try {
            conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/appcooperativa", "root", "");
           // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AppCooperativa", "admin", "4Me8sWddu41H");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error de conexión");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return null;
        }*/
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
    public void mostrarDatosCliente(){
        tablaCliente.setItems(dameListaCliente());
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tablaCliente.setOnMouseClicked(event -> cargarDatosCliente());
        //Definimos la URL base del API REST que utilizamos
        String baseUrl = "http://localhost/API_ANTONIO/API_ANTONIO/crud/";

        Gson gson = new GsonBuilder().setLenient().create();
        //Instancia a retrofit agregando la baseURL y el convertidor GSON
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //Se crea el servicio
        MiAPIServicioLeer servicioLeer = retrofit.create(MiAPIServicioLeer.class);
        //Se crea el servicio
        MiAPIServicioInsertar servicioIns = retrofit.create(MiAPIServicioInsertar.class);
        //Se crea el servicio
        MiAPIServicioBorrar servicioBorr = retrofit.create(MiAPIServicioBorrar.class);
        //Se crea el servicio
        MiAPIServicioActualizar servicioAct = retrofit.create(MiAPIServicioActualizar.class);
        callLect = servicioLeer.getClientes();
        encolaLectura();

        //Los campos han de coincidir con los campos del objeto Libros

        IdCliente.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente"));
        NombreCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nombre"));
        apellidos.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Apellido"));
        DNI.setCellValueFactory(new PropertyValueFactory<Cliente, String>("DNI"));
        telefono.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("Telefono"));



    }


    private void lanzarInforme(String rutaInf, Map hm) throws FileNotFoundException{
        try {
            InputStream inputS= getClass().getResourceAsStream(rutaInf);
            JasperReport report = (JasperReport) JRLoader.loadObject(inputS);

            JasperPrint jasperPrint=JasperFillManager.fillReport(report, hm,this.conexion);

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

    // REPAIR TABLE cooperativa; Se ejecuta en phpmyadmin por si falla al añadir cooperativas
    //Definimos la URL base del API REST que utilizamos
    String baseUrl = "http://localhost/API_ANTONIO/API_ANTONIO/crud/";

    Gson gson = new GsonBuilder().setLenient().create();
    //Instancia a retrofit agregando la baseURL y el convertidor GSON
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    //Se crea el servicio
    MiAPIServicioLeer servicioLeer = retrofit.create(MiAPIServicioLeer.class);
    //Se crea el servicio
    MiAPIServicioInsertar servicioIns = retrofit.create(MiAPIServicioInsertar.class);
    //Se crea el servicio
    MiAPIServicioBorrar servicioBorr = retrofit.create(MiAPIServicioBorrar.class);
    //Se crea el servicio
    MiAPIServicioActualizar servicioAct = retrofit.create(MiAPIServicioActualizar.class);


    public void encolaLectura() {

        callLect.enqueue(new Callback<OperacionCliente>() {

            /**
             * Para errores del tipo: Network Error :: timeout
             */
            @Override
            public void onFailure(Call<OperacionCliente> call, Throwable t) {
                System.out.println("Network Error :: " + t.getLocalizedMessage());
                mostrarAlerta(Alert.AlertType.ERROR,"Error Lectura",t.getMessage(),null );
            }

            /**
             * La respuesta del servidor
             */
            @Override
            //es un método de la clase Platform de JavaFX que permite ejecutar una tarea en el hilo de interfaz
            //de usuario (UI thread) de forma asíncrona.
            public void onResponse(Call<OperacionCliente> call, Response<OperacionCliente> response) {
                Platform.runLater(() -> { //
                    if (response.isSuccessful()) {
                        ObservableList<Cliente> listaC = FXCollections.observableArrayList();
                        for(Cliente client : response.body().getClientes()){
                            listaC.add(client);
                        }

                        tablaCliente.setItems(listaC);
                    } else {
                        label.setText("Código de error: " + response.code());

                    }
                });
            }
        });
    }
    public void encolaInsercion() {
        //la llamada es asíncrona:
        //Retrofit descarga y analiza los datos del API en un subproceso en
        //paralelo y entrega los resultados a traves de los metodos
        //onFailure o onResponseprivate void clearFilterForm() {
        idClientetxt.clear();
        nombreClientetxt.clear();
        apellidosClientetxt.clear();
        DNIclientetxt.clear();
        telefonoClientetxt.clear();
    }
        //Si se usa enqueue sigue con el procesamiento en las líneas posteriores
        callIns.enqueue(new Callback<OperacionCliente>() {

            /**
             * Para errores del tipo: Network Error :: timeout
             */
            @Override
            public void onFailure(Call<OperacionCliente> call, Throwable t) {
                System.out.println("Network Error :: " + t.getLocalizedMessage());
                mostrarAlerta(Alert.AlertType.ERROR,
                        "Network Error:: ",
                        t.getMessage(),
                        null);
            }

            /**
             * La respuesta del servidor
             */
            @Override
            //es un método de la clase Platform de JavaFX que permite ejecutar una tarea en el hilo de interfaz de usuario (UI thread) de forma asíncrona.
            public void onResponse(Call<OperacionCliente> call, Response<OperacionCliente> response) {
                Platform.runLater(() -> { //
                    System.out.println("Respuesta INSERTAR: " + response.message());

                    //Comprobamos que la respuesta sea correcta
                    if (response.isSuccessful() && response.body().getResult().isOk()) {
                        //actualizaTexto(response.code());

                        tablaCliente.getItems().add(response.body().getCliente());
                        tablaCliente.refresh();

                        clearFilterForm();

                        mostrarAlerta(Alert.AlertType.INFORMATION,
                                "Antonio Quiros Garcia",
                                "El cliente se ha añadido correctamente",
                                null);
                    } else{
                        System.out.println("Fallo "+response.message());
                        mostrarAlerta(Alert.AlertType.ERROR, "Antonio Quiros Garcia", (!response.isSuccessful()) ? response.message() : response.body().getResult().getMessage(),null);
                    }
                });
            }


        });
    }

    public void encolaActualizar() {
        //la llamada es asíncrona:
        //Retrofit descarga y analiza los datos del API en un subproceso en
        //paralelo y entrega los resultados a traves de los metodos
        //onFailure o onResponse
        //Si se usa enqueue sigue con el procesamiento en las líneas posteriores
        callAct.enqueue(new Callback<OperacionCliente>() {

            /**
             * Para errores del tipo: Network Error :: timeout
             */
            @Override
            public void onFailure(Call<OperacionCliente> call, Throwable t) {
                System.out.println("Network Error :: " + t.getLocalizedMessage());
                mostrarAlerta(Alert.AlertType.ERROR, "Network Error", t.getMessage(),null);
            }

            /**
             * La respuesta del servidor
             */
            @Override
            //es un método de la clase Platform de JavaFX que permite ejecutar una tarea en el hilo de interfaz de usuario (UI thread) de forma asíncrona.
            public void onResponse(Call<OperacionCliente> call, Response<OperacionCliente> response) {
                Platform.runLater(() -> { //
                    if (response.isSuccessful() && response.body().getResult().isOk()) {
                        clearFilterForm();

                        tablaCliente.getItems().remove(response.body().getCliente());
                        tablaCliente.getItems().add(response.body().getCliente());

                        tablaCliente.refresh();

                        mostrarAlerta(AlertType.INFORMATION, "Antonio Quiros Garcia", "Cliente actualizado",null);
                    } else {
                        System.out.println(response.message());
                        mostrarAlerta(Alert.AlertType.ERROR, "Antonio Quiros Garcia", (!response.isSuccessful()) ? response.message() : response.body().getResult().getMessage(),null);
                    }
                });
            }
        });
    }

    public void encolaBorrado() {
        //la llamada es asíncrona:
        //Retrofit descarga y analiza los datos del API en un subproceso en
        //paralelo y entrega los resultados a traves de los metodos
        //onFailure o onResponse
        //Si se usa enqueue sigue con el procesamiento en las líneas posteriores
        callBorr.enqueue(new Callback<OperacionCliente>() {

            /**
             * Para errores del tipo: Network Error :: timeout
             */
            @Override
            public void onFailure(Call<OperacionCliente> call, Throwable t) {
                System.out.println("Network Error :: " + t.getLocalizedMessage());
                mostrarAlerta(Alert.AlertType.ERROR, "Antonio Quiros Garcia", t.getMessage() ,null);
            }

            /**
             * La respuesta del servidor
             */
            @Override
            //es un método de la clase Platform de JavaFX que permite ejecutar una tarea en el hilo de interfaz de usuario (UI thread) de forma asíncrona.
            public void onResponse(Call<OperacionCliente> call, Response<OperacionCliente> response) {
                Platform.runLater(() -> { //
                    if (response.isSuccessful() && response.body().getResult().isOk()) {
                        clearFilterForm();

                        tablaCliente.getItems().remove(response.body().getCliente());
                        tablaCliente.refresh();

                        mostrarAlerta(AlertType.INFORMATION, "Antonio Quiros Garcia", response.body().getResult().getMessage(),null);
                    } else {
                        System.out.println(response.message());
                        mostrarAlerta(Alert.AlertType.ERROR, "Antonio Quiros Garcia", (!response.isSuccessful()) ? response.message() : response.body().getResult().getMessage(),null);
                    }
                });
            }
        });
    }

    private void clearFilterForm() {
        idClientetxt.clear();
        nombreClientetxt.clear();
        apellidosClientetxt.clear();
        DNIclientetxt.clear();
        telefonoClientetxt.clear();
    }

    private void actualizaTexto(int codigo) {

        System.out.println("Respuesta Servidor:\n Código: " + codigo);
        mistage.show();//Actualiza
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String contenido, String headerText) {
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
}
    
    
   
    

        
                