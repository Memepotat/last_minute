module com.beginsecure.lastminute {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.beginsecure.lastminute to javafx.fxml;
    exports com.beginsecure.lastminute;
}