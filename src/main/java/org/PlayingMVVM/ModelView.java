package org.PlayingMVVM;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
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
    HBox readyProcesses;
    @FXML
    HBox runningProcesses;
    @FXML
    HBox createdProcesses;
    Timeline animation;
    public ModelView() {
        Mediator.getInstance().register(this, Mediator.Component.GUI);
    }
    public void addCircleToCreatedProcessList(Circle circle){
        readyProcesses.getChildren().add(circle);
    }

    @FXML
    private void createProcess() throws Exception {
        Mediator.getInstance().send(this, Mediator.Action.CREATE_PROC);
    }
    public void schedule(){
        if(runningProcesses.getChildren().isEmpty()){
            runningProcesses.getChildren().add((readyProcesses.getChildren().remove(0)));
        }
        else{
            readyProcesses.getChildren().add(runningProcesses.getChildren().remove(0));
            runningProcesses.getChildren().add((readyProcesses.getChildren().remove(0)));
        }
    }
    public void removeProcessFromRunningList(Circle circleToRemove){
        // check if circleToRemove is in runningProcesses
        if(!runningProcesses.getChildren().contains(circleToRemove)){
            throw new IllegalStateException("Circle to remove must be in runningProcesses");
        }
        runningProcesses.getChildren().remove(circleToRemove);
    }
    public void removeProcessFromReadyList(Circle circleToRemove){
        // check if circleToRemove is in readyProcesses
        if(!readyProcesses.getChildren().contains(circleToRemove)){
            throw new IllegalStateException("Circle to remove must be in readyProcesses");
        }
        readyProcesses.getChildren().remove(circleToRemove);
    }
    public void addProcessToRunningList(Circle circle){
        runningProcesses.getChildren().add(circle);
    }
    public void addProcessToReadyList(Circle circle){
        readyProcesses.getChildren().add(circle);
    }

    private final Service<Void> createCircleCommand = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {

                    animation = new Timeline(new KeyFrame(Duration.millis(500), e -> Mediator.getInstance().send(this, Mediator.Action.RUN)));
                    animation.setCycleCount(Animation.INDEFINITE); // loop forever
                    animation.play();
                    return null;
                };
            };
        }
    };

    @FXML
    private void startSim() {
        createCircleCommand.restart();
    }
    @FXML
    private void finishSim() {
        animation.pause();
    }
    @FXML
    private void resetTimer(){
        Mediator.getInstance().send(this, Mediator.Action.VISUALIZE);
    }

    public Circle createCircle() {
        Color col = Color.color(Math.random(),Math.random(),Math.random());
        Circle c = new Circle(40,col);
        c.setOnMouseClicked((evt) -> {
            Mediator.getInstance().send(c, Mediator.Action.VIEW_PROCESS_INFO);
        });
        return c;
    }

    public void showProcessInfo(Process process, Color col) {
        // print colors
        String t = RGBtoHEX.rgbToHex((int)(col.getRed()*250), (int)(col.getGreen()*250), (int)(col.getBlue()*250));
        Dialog<ButtonType> processInfoDialog = new ProcessInfoDialog(process, t);
        processInfoDialog.showAndWait();
    }
}
