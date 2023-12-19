package org.PlayingMVVM;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Optional;

public class ModelView {


    @FXML
    Button BtnResetTimer;
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

    HBox CircleProcesses;

    private Relogio timer = new Relogio(0,0,0);

    private Boolean pause = false;

    PauseTransition delay = new PauseTransition(Duration.seconds(0.5));

    Timeline animation;

    public DialogReturnPOJO diagPOJO;

    public ModelView() {
        Mediator.getInstance().register(this,Mediator.Component.GUI);
        Mediator.getInstance().register(new Kernel(),Mediator.Component.KERNEL);

        Mediator.getInstance().subscribe(Mediator.EVENT_UPDATE,this,this::update);
        Mediator.getInstance().subscribe(Mediator.ON_CREATE,this,this::createProcessCircle);
        Mediator.getInstance().subscribe(Mediator.ON_SCHEDULE,this,this::schedule);
    }
    public void addCircleToCreatedProcessList(Circle circle){
        listaPreta.getChildren().add(circle);
    }

    @FXML
    private void createProcess() throws Exception {
        Mediator.getInstance().send(this, Mediator.Action.CREATE_PROC);
    }
    public DialogReturnPOJO createProcessDialog(){
        Dialog<DialogReturnPOJO> createProcessDialog = new ProcessDialog();
        Optional<DialogReturnPOJO> result = createProcessDialog.showAndWait();
        return result.orElse(null);
    }

    private void createProcessCircle(String event){
        Circle c = createCircle();
        listaPreta.getChildren().add(c);
    }

    public void SuspendCircle(String event){
        // find a way to retrieve a circle from Mediator

    }

    private void update(String event) {

        Circle c = createCircle();
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

                    animation = new Timeline(new KeyFrame(Duration.millis(500), e -> VirtualMachine.run()));
                    animation.setCycleCount(Animation.INDEFINITE); // loop forever
                    animation.play();
                    return null;
                };
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
        animation.pause();
    }
    @FXML
    private void resetTimer(){
        Mediator.getInstance().send(this, Mediator.Action.VISUALIZAR);
    }

    public Circle createCircle() {
        Color col = Color.color(Math.random(),Math.random(),Math.random());
        return new Circle(40,col);
    }
}
