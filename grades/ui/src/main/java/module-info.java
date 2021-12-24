module grades.ui {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    requires grades.core;

    opens grades.ui to javafx.graphics, javafx.fxml;
}