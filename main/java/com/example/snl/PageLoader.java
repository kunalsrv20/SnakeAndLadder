package com.example.snl;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class PageLoader implements Initializable {

    @FXML
    private Scene scene;

    @FXML
    private ImageView loadingPage;
    @FXML
    private ProgressBar LoadingBar;
    @FXML
    private Label loadingLabel;

    double progress = 0;

    public void getProgress() {
        progress += 0.01;
        LoadingBar.setProgress(progress);
        int val = (int)(progress * 100);
        loadingLabel.setText(val +"%");
    }

    Timeline roll() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                event -> getProgress()),
                new KeyFrame(Duration.seconds(0.01)));
        timeline.setCycleCount(100);
        return timeline;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle agg1) {
        Timeline timeStop = roll();
        timeStop.setOnFinished(event -> {
            try {
                switchingToPlayer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        timeStop.play();

    }

//    @FXML
//    public void switchToPlayer(MouseEvent mouseEvent) throws IOException {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PlayerSelectionScene.fxml")));
//        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    @FXML
    public void switchingToPlayer() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PlayerSelectionScene.fxml")));
        Stage stage = (Stage) (LoadingBar.getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
