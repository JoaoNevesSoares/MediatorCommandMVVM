package org.PlayingMVVM;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ViewMain extends AnchorPane {

    public AnchorPane root = new AnchorPane();

    public final ModelViewMain mv = new ModelViewMain();



    public ViewMain() throws IOException{
        createView();
    }
    public void createView() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/minhainterface.fxml"));

        if (loader.getLocation() == null)  {
            throw new IOException("FXML file not found");
        }

        try {
            this.root.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}