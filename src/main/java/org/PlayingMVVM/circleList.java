package org.PlayingMVVM;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class circleList extends GridPane {

    public circleList() {
        createNewWindow();
    }

    public Circle[] createCircle(int count, Color col) {
        Circle[] circles = new Circle[count];
        for (int i = 0; i < count; i++) {
            circles[i] = new Circle(40, col);
        }
        return circles;
    }

    public void createNewWindow() {
        HBox lista_1 = new HBox(50);
        HBox lista_2 = new HBox(50);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10));
        lista_1.setPadding(new Insets(10));
        lista_2.setPadding(new Insets(10));
        lista_1.getChildren().addAll(createCircle(3, Color.RED));
        lista_2.getChildren().addAll(createCircle(3, Color.BLUE));
        add(lista_1, 0, 0);
        add(lista_2, 0, 1);
    }
}
