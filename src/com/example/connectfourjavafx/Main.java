package com.example.connectfourjavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        GameBoard board = new GameBoard();
        Scene scene = new Scene(board.createBoard(), 800, 700);

        // Safe load of style.css (avoids NPE)
        URL css = getClass().getResource("style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        } else {
            System.err.println("⚠️ style.css not found — continuing without CSS.");
        }

        stage.setTitle("Connect Four 🎮");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
