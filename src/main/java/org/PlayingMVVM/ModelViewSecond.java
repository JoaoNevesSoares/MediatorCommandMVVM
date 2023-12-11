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

public class ModelViewSecond {


    @FXML
    Button BtnCreateProc;

    @FXML
    Button BtnStartSim;

    @FXML
    Button BtnFinishSim;

    @FXML
    HBox listaPreta;

    @FXML
    HBox listaVermelha;


    private Boolean pause = false;

    PauseTransition delay = new PauseTransition(Duration.seconds(0.5));

    private final Mediator mediator = new Mediator();


    public ModelViewSecond(){
        mediator.subscribe(Mediator.EVENT_UPDATE,this,this::update);
        mediator.subscribe(Mediator.ON_CREATE,this,this::createProcessCircle);
        mediator.subscribe(Mediator.ON_SCHEDULE,this,this::schedule);
    }

    @FXML
    private void createProcess() {
        Model.createProcess();
        System.out.println("Clicou Preto");
    }
    private void createProcessCircle(String event){
        Color col = Color.color(Math.random(),Math.random(),Math.random());
        Circle c = createCircle(col);
        listaPreta.getChildren().add(c);
    }

    private void update(String event) {

        Circle c = createCircle(Color.RED);
        listaPreta.getChildren().add(c);

    }
    private void schedule(String event){
        if(listaVermelha.getChildren().isEmpty()){
            listaVermelha.getChildren().add((listaPreta.getChildren().remove(0)));
        }
        else{
            listaPreta.getChildren().add(listaVermelha.getChildren().remove(0));
            listaVermelha.getChildren().add((listaPreta.getChildren().remove(0)));
        }
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
                                Model.run();
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
    private void startSim() {
        pause = false;
        createCircleCommand.restart();
    }
    @FXML
    private void finishSim() {
        pause = true;
    }

    private Circle createCircle(Color col) {

        return new Circle(40,col);
    }
}
