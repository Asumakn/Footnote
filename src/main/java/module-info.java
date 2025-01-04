module org.example.footnote {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires java.desktop;
    requires jdk.httpserver;
    requires json.simple;
    requires org.jsoup;

    opens org.example.footnote to javafx.fxml;
    exports org.example.footnote;
}