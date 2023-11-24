package org.PlayingMVVM;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ViewMain extends AnchorPane {

    public final AnchorPane root = new AnchorPane();
    @FXML
    private Button BntAddBlack;

    @FXML
    public void initialize(){
        BntAddBlack.onActionProperty().set(e -> {
            System.out.println("Clicou");
        });
    }
    public ViewMain() throws IOException{
        createView();
        //initialize();
    }
    public void createView(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/PlayingMVVM/minhainterface.fxml"));
        loader.setController(this);
        try {
            this.root.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}