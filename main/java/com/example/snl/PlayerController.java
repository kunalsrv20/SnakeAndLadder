package com.example.snl;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class PlayerController {

    @FXML
    private Scene scene;
    @FXML
    private GridPane gridPane;
    @FXML
    private ImageView player1;
    @FXML
    private ImageView player2;

    @FXML
    private Stage stage;
    @FXML
    private Parent root;

    @FXML
    TextField nameTextP11;
    @FXML
    TextField nameTextP12;
    @FXML
    TextField nameTextP21;
    @FXML
    TextField nameTextP22;

    private static String dp1;
    private static String dp2;

    public static String getPlayerName1() {
        return dp1;
    }
    public static String getPlayerName2() {
        return dp2;
    }

    @FXML
    void switchToStart(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void playerNameSetter() throws IOException {
        String userName11 = nameTextP11.getText();
        String userName12 = nameTextP12.getText();
        String userName21 = nameTextP21.getText();
        String userName22 = nameTextP22.getText();

        dp1 = "";
        dp2 = "";
        if (!userName11.equals("") || !userName21.equals("")) {
            if (!userName11.equals("")) {
                dp1 = userName11;
            }
            else {
                dp1 = userName21;
            }
        }
        if (!userName12.equals("") || !userName22.equals("")) {
            if (!userName12.equals("")) {
                dp2 = userName12;
            }
            else {
                dp2 = userName22;
            }
        }

    }

    @FXML
    public void switchToBoard(MouseEvent mouseEvent) throws IOException {
        playerNameSetter();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BoardScene.fxml")));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToBoardLoader(MouseEvent mouseEvent) throws IOException {
        playerNameSetter();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BoardPageLoader.fxml")));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private RadioButton rgroup1, rgroup2;
    @FXML
    private Label groupTeller;

    public void getPlayerGroup(ActionEvent event) {

        if (rgroup1.isSelected()) {
            nameTextP11.setDisable(false);
            nameTextP12.setDisable(false);
            nameTextP21.setDisable(true);
            nameTextP22.setDisable(true);

            groupTeller.setText("Player Set : 1");
        }
        else {
            nameTextP21.setDisable(false);
            nameTextP22.setDisable(false);
            nameTextP11.setDisable(true);
            nameTextP12.setDisable(true);

            groupTeller.setText("Player Set : 2");
        }

    }

}
