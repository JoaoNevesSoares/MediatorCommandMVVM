package org.PlayingMVVM;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        EmploymentRequestView view = new EmploymentRequestView();
        Scene scene = new Scene(view);
        stage.setScene(scene);
        stage.setTitle("Minha Aplicação");
        stage.setWidth(480);
        stage.setHeight(320);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}