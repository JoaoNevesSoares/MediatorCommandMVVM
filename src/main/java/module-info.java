module org.PlayingMVVM {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    opens org.PlayingMVVM to javafx.fxml;
    exports org.PlayingMVVM;
}