package com.example.snl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartScene.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Database dbs = new Database();
        dbs.init();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
