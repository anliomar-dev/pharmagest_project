module com.phamagest.pharmagest {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;

    opens com.phamagest.pharmagest to javafx.fxml;
    exports com.phamagest.pharmagest;
    exports controllers;
    opens controllers to javafx.fxml;
    opens models to javafx.base;

}