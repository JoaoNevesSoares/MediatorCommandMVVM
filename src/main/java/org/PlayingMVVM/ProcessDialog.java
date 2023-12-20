package org.PlayingMVVM;

import javafx.beans.property.IntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import javax.security.auth.callback.Callback;
import java.security.KeyStore;

public class ProcessDialog extends Dialog<DialogReturnPOJO>{
    private Spinner<Integer> pidSpinner;
    private Spinner<Integer> burstSpinner;

    public ProcessDialog(){
        super();
        this.setTitle("Create Process");
        buildUI();
    }
    private void buildUI() {
        Pane pane = createGridPane();
        getDialogPane().setContent(pane);
        // adding buttons to dialog pane
        getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK){
                return new DialogReturnPOJO(pidSpinner.getValue(),burstSpinner.getValue());
            }
            return null;
        });
    }
    public Pane createGridPane(){
        VBox content = new VBox(10);
        Label pid  = new Label("Insert Process Pid");
        Label burst = new Label(  "Burst time");
        this.pidSpinner = new Spinner<Integer>(0,999,0);
        this.burstSpinner = new Spinner<Integer>(0,999,0);
        this.pidSpinner.setEditable(true);
        GridPane grid = new GridPane();
        grid.setHgap (10);
        grid.setVgap(5);
        grid.add(pid, 0, 0);
        grid.add(burst, 0,1);
        grid.add(pidSpinner, 1,0);
        grid.add(burstSpinner,1,1);
        GridPane.setHgrow(pid, Priority.ALWAYS);
        GridPane.setHgrow(burst,Priority.ALWAYS);
        content.getChildren().add(grid);
        return content;
    }
}

