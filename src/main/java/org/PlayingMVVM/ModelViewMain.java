package org.PlayingMVVM;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ModelViewMain {


    @FXML
    Button BntAddBlack;

    @FXML
    Button BntAddRed;

    @FXML
    Button BntMoveUp;

    @FXML
    Button BntMoveDown;

    @FXML
    HBox listaPreta;

    @FXML
    HBox listaVermelha;
    public ModelViewMain(){


    }

    @FXML
    private void handleAddBlack(){

        Circle c = createCircle(40,Color.RED);
        listaPreta.getChildren().add(c);
        System.out.println("Clicou Preto");
    }
    @FXML
    private void handleAddRed(){

        Circle c = createCircle(40,Color.BLACK);
        listaVermelha.getChildren().add(c);
        System.out.println("Clicou Vermelho");
    }
    @FXML
    private void handleMoveUp(){
        System.out.println("Clicou para Cima");
        if(!listaVermelha.getChildren().isEmpty()){
            Circle c = (Circle) listaVermelha.getChildren().remove(0);
            c.setFill(Color.RED);
            listaPreta.getChildren().add(c);
        }
        else{
            System.out.println("Ops Não há item para mover para cima!");
        }

        BntMoveDown.setDisable(false);

        if (listaVermelha.getChildren().isEmpty())
            BntMoveUp.setDisable(true);
    }
    @FXML
    private void handleMoveDown(){
        System.out.println("Clicou para baixo");
        Circle c = (Circle) listaPreta.getChildren().remove(0);
        c.setFill(Color.BLACK);
        listaVermelha.getChildren().add(c);

        BntMoveUp.setDisable(false);

        if (listaPreta.getChildren().isEmpty())
            BntMoveDown.setDisable(true);
    }

    private Circle createCircle(int radius, Color col){

        return new Circle(radius,col);
    }
}
