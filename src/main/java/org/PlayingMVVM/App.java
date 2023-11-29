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

        ViewMain viewMain;
        try {
            viewMain = new ViewMain();
            Scene scene = new Scene(viewMain.root, 600, 480);
            stage.setScene(scene);
            stage.setTitle("Minha Aplicação");
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
    public static void main(String[] args) {
            launch();
    }
}