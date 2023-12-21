package org.PlayingMVVM;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class CreateProcessDialog extends Dialog<ButtonType> {

    private final IntegerProperty burstProperty = new SimpleIntegerProperty();
    public Label burstLabel;
    @FXML
    private Spinner<Integer> pidSpinner;
    @FXML
    private Spinner<Integer> burstSpinner;

    public CreateProcessDialog() {
        super();
        this.setTitle("Create Process Dialog");
        buildDialog();
    }


    private void buildDialog() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateProcessDialog.fxml"));
        loader.setController(this); // Set the current instance as the controller
        try {
            VBox content = loader.load();
            getDialogPane().setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle IOException here
        }
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                burstProperty.set(burstSpinner.getValue());
                return ButtonType.OK;
            }
            return null;
        });
    }
    public IntegerProperty burstProperty() {
        return burstProperty;
    }
}
