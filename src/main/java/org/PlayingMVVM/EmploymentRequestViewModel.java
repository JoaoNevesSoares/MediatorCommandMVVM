package org.PlayingMVVM;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class EmploymentRequestViewModel {

    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty position = new SimpleStringProperty("");
    private final DoubleProperty annualSalary = new SimpleDoubleProperty();

    private final EmploymentRequestModel model = new EmploymentRequestModel();

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public double getAnnualSalary() {
        return annualSalary.get();
    }

    public DoubleProperty annualSalaryProperty() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary.set(annualSalary);
    }

    public void save() {
        model.save();
    }

    public void reset() {
        this.name.set("");
        this.position.set("");
        this.annualSalary.set(0.0d);
    }
    public Circle insertCircle(){
        //make a sleep to wait for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Circle(60, 40, 30, Color.GREEN);

    }


    
}
