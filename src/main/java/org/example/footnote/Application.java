package org.example.footnote;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Application extends javafx.application.Application {
    public static Player activePlayer = new Player();
    public static Team activeTeam = new Team();
    public static Game activeGame = new Game();
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.getIcons().add((new Image("file:Images/Jersey.png")));
        stage.setTitle("FootNote");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}