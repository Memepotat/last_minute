module loginsystem.logreg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens loginsystem.logreg to javafx.fxml;
    exports loginsystem.logreg;
}