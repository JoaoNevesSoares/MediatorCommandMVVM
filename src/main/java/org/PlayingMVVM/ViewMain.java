package org.PlayingMVVM;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ViewMain extends AnchorPane {

    public AnchorPane root = new AnchorPane();
    @FXML
    private Button BntAddBlack;

    @FXML
    Button BntAddRed;

    @FXML
    Button BntMoveUp;

    @FXML
    Button BntMoveDown;



    @FXML
    public void initialize(){
        BntAddBlack.onActionProperty().set(e -> {
            System.out.println("Clicou Preto");
        });
        BntAddRed.onActionProperty().set( e -> {
            System.out.println("Ciclou Vermelho");
        });
        BntMoveUp.onActionProperty().set( e -> {
            System.out.println("Ciclou pra cima");
        });
        BntMoveDown.onActionProperty().set( e -> {
            System.out.println("Clicou pra baixo");
        });

    }
    public ViewMain() throws IOException{
        createView();
        //initialize();
    }
    public void createView() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/minhainterface.fxml"));
        if (loader.getLocation() == null) {
            throw new IOException("FXML file not found");
        }
        loader.setController(this);
        try {
            this.root.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}