package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Antonio
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/VentanaPrincipal.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ProyectoCooperativa");
        stage.show();
        

    }

    public static void main(String[] args) {
        launch(args);
    }
}
