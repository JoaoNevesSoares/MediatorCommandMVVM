package org.PlayingMVVM;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Node;
import javafx.util.converter.NumberStringConverter;

public class EmploymentRequestView extends VBox {
    
    GridPane gp = new GridPane();    

    private Button btnAdd = new Button("Add");
    private Button btnCancel = new Button("Cancel");
    private Button btnReset = new Button("Reset");

    private final EmploymentRequestViewModel viewModel = new EmploymentRequestViewModel();


    //private void save(ActionEvent evt) { viewModel.save(); }
    private void createCircle(ActionEvent evt, Pane list){
        Node newCircle = viewModel.insertCircle();
        list.getChildren().add(newCircle);
    }
    private void cancel(ActionEvent evt){ Platform.exit();}
    //private void reset(ActionEvent evt){ viewModel.reset();}
    private void reset(ActionEvent evt, Pane list){ list.getChildren().clear();}
    private void bindViewModel() {

        //tfName.textProperty().bindBidirectional(viewModel.nameProperty());


        //tfPosition.textProperty().bindBidirectional(viewModel.positionProperty());

        // Bindings.bindBidirectional(
        //         tfAnnualSalary.textProperty(),
        //         viewModel.annualSalaryProperty(),
        //         new NumberStringConverter()
        // );
    }
     private void createView() {
        VBox gpwrap = new VBox();

        HBox circleList = new HBox(5);
        Circle circle = new Circle(60, 40, 30, Color.GREEN);
        circleList.getChildren().add(circle);

        gpwrap.setAlignment( Pos.CENTER );

        gp.setPadding( new Insets(40) );
        gp.setVgap( 4 );
        gp.add(new Label("Name"), 0, 0);              
        gp.add(circleList, 0, 1);

        final ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth( 50 );

        gp.getColumnConstraints().addAll( col, col );

        gpwrap.getChildren().add( gp );

        VBox.setVgrow( gpwrap, Priority.ALWAYS );

        //btnSave.setOnAction( this::save );
        btnAdd.setOnAction(event -> createCircle(event, circleList));
        btnCancel.setOnAction( this::cancel );
        btnReset.setOnAction( event -> reset(event, circleList));

        btnAdd.setDefaultButton(true);
        //btnSave.setDefaultButton(true);

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPadding( new Insets(20.0d) );
        ButtonBar.setButtonData(btnAdd,ButtonBar.ButtonData.OTHER);
        ButtonBar.setButtonData(btnCancel, ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonBar.setButtonData(btnReset, ButtonBar.ButtonData.OTHER);
        buttonBar.getButtons().addAll(btnCancel, btnReset,btnAdd );
        this.getChildren().addAll(
                gpwrap,
                new Separator(),
                buttonBar);
    }

    public EmploymentRequestView() {
        createView();
        bindViewModel();
    }


}
