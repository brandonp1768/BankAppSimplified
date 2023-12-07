module com.example.bankappsimplified {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.bankappsimplified to javafx.fxml;
    exports com.example.bankappsimplified;
}