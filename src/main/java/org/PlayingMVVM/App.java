package org.PlayingMVVM;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public class App extends Application {
    @Override
    public void start(Stage stage){
        
        EmploymentRequestView view = new EmploymentRequestView();
        ViewMain viewMain;
        try {
            viewMain = new ViewMain();
            Scene scene = new Scene(viewMain.root, 640, 480);
            stage.setScene(scene);
            stage.setTitle("Minha Aplicação");
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
            launch();
    }
}