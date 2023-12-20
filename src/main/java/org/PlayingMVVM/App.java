package org.PlayingMVVM;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class App extends Application {
    @Override
    public void start(Stage stage){

        try {
            Scene scene = new Scene(new ViewMain().root, 600, 480);
            stage.setScene(scene);
            stage.setTitle("PampaSim");
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
    public static void main(String[] args) {
        Mediator.getInstance().register(new Kernel(),Mediator.Component.KERNEL);
        Mediator.getInstance().register(new VirtualMachine(),Mediator.Component.VM);
        launch();
    }
}