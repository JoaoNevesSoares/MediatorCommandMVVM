package org.PlayingMVVM;

import javafx.animation.PauseTransition;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

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


    private Boolean pause = false;

    PauseTransition delay = new PauseTransition(Duration.seconds(0.01));

    public ModelViewMain(){
    }

    @FXML
    private void handleAddBlack(){
        Circle c = createCircle(Color.RED);
        listaPreta.getChildren().add(c);
        System.out.println("Clicou Preto");
    }

    private void update(String event){

        Circle c = createCircle(Color.RED);
        listaPreta.getChildren().add(c);

    }

    private final Service<Void> createCircleCommand = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while(!pause){
                        delay.setOnFinished(event -> {
                            if(!pause){
                                VirtualMachine.run();
                            }
                            else{
                                System.out.println("foi parado antes do tempo");
                            }
                        });
                        delay.play();
                    }
                    return null;
                }
            };
        }
    };

    @FXML
    private void startBlack(){
        pause = false;
        createCircleCommand.restart();
    }

    @FXML
    private void handleAddRed(){

        Circle c = createCircle(Color.BLACK);
        listaVermelha.getChildren().add(c);
        System.out.println("Clicou Vermelho");
        pause = true;
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

        if(!listaPreta.getChildren().isEmpty()){
        Circle c = (Circle) listaPreta.getChildren().remove(0);
        c.setFill(Color.BLACK);
        listaVermelha.getChildren().add(c);
        }
        else{
            System.out.println("Ops Não há item para mover para baixo!");
        }
        BntMoveUp.setDisable(false);
        if (listaPreta.getChildren().isEmpty())
            BntMoveDown.setDisable(true);
    }

    private Circle createCircle(Color col){

        return new Circle(40,col);
    }
}
