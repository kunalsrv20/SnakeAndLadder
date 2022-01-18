package com.example.snl;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    @FXML
    private Scene scene;
    @FXML
    private GridPane gridPane;
    @FXML
    private ImageView player1;
    @FXML
    private ImageView player2;
    @FXML
    private ImageView duplicatePlayer1;
    @FXML
    private ImageView duplicatePlayer2;

    @FXML
    private Stage stage;
    @FXML
    private Parent root;

    @FXML
    void switchToExitPage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ExitConfirmation.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    Player p1 = new Player(this);
    Player p2 = new Player(this);


    @FXML
    void switchToStart(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StartScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    Random random = new Random();

    @FXML
    private ImageView diceImage;

    private int turn = 1;
    @FXML

    static int r = 0;
    Timeline roll() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.05),
                event -> setimage((random.nextInt(6)+1))),
                new KeyFrame(Duration.seconds(0.1)));
        timeline.setCycleCount(5);
        return timeline;
    }

    private void setimage(int r) {
        String path = "src/main/resources/ProjectImages/"+ r +".jpeg";
        File file = new File(path);
        diceImage.setImage(new Image(file.toURI().toString()));

    }

    @FXML
    public void rollTheDie(MouseEvent event) throws InterruptedException {
        diceImage.setDisable(true);

//        TranslateTransition translation = new TranslateTransition();
        RotateTransition rotation = new RotateTransition();
        ScaleTransition scale = new ScaleTransition();


//      For Rotation the Dice
        rotation.setNode(diceImage);
        rotation.setDuration(Duration.seconds(0.6));
        rotation.setCycleCount(1);
        rotation.setByAngle(360);
        rotation.setAxis(Rotate.X_AXIS);
        rotation.play();

//      For Scaling the Dice
        scale.setNode(diceImage);
        scale.setDuration(Duration.millis(150));
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setCycleCount(1);
        scale.setByX(0.6);
        scale.setByY(0.6);
        scale.setOnFinished(event1 -> {
            ScaleTransition scales = new ScaleTransition();
            scales.setNode(diceImage);
            scales.setDuration(Duration.millis(150));
            scales.setInterpolator(Interpolator.LINEAR);
            scales.setCycleCount(1);
            scales.setByX(-0.6);
            scales.setByY(-0.6);

            scales.play();
        });
        scale.play();

        r = (random.nextInt(6)+1);

        Timeline timeline = roll();
        timeline.play();
        timeline.setOnFinished( e ->
        {
            setimage(r);
        });

        diceImage.setDisable(false);
        moveThePlayers();
        if(p1.getCurrPoints() == 100){

        }else{

        }
        valTurn();
    }

    void moveThePlayers() throws InterruptedException {
        TranslateTransition translation = new TranslateTransition();
        RotateTransition rotation = new RotateTransition();
        ScaleTransition scale = new ScaleTransition();

        turn ^= 1;

        if(turn == 0){
            if(p1.isOpen())
                p1.movePlayer(r);
            else {
                if(r == 1)
                    p1.openPlayer(r);
            }

//            gridPane.getChildren().remove(player1);
//            gridPane.add(player1, p1.getCurrColumn(), p1.getCurrRow());
        }else{
            if(p2.isOpen())
                p2.movePlayer(r);
            else
                p2.openPlayer(r);
//            gridPane.getChildren().remove(player2);
//            gridPane.add(player2, p2.getCurrColumn(), p2.getCurrRow());
        }

        if (p1.getCurrPoints() == 100) {
            winnerPName = PlayerController.getPlayerName1();
            WinnerPageDisplay();
        }
        else if (p2.getCurrPoints() == 100) {
            winnerPName = PlayerController.getPlayerName2();
            WinnerPageDisplay();
        }

    }

    public void animateToken(Player p, ImageView player, ImageView duplicatePlayer) throws InterruptedException {

//        duplicatePlayer.setVisible(true);
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(duplicatePlayer);
        translateTransition.setDuration(Duration.millis(1000));
        double height = (p.getCurrRow() - 10) * 538d / 11;
        double width = p.getCurrColumn() * 348d / 10;
        translateTransition.setToX(width);
        translateTransition.setToY(height);
        translateTransition.play();
//        player.setVisible(true);
//        duplicatePlayer.setVisible(false);
    }

    public void perMoveAnimate() throws InterruptedException {
        if(turn == 0){
            gridPane.getChildren().remove(player1);
//            player1.setVisible(false);
            gridPane.add(player1, p1.getCurrColumn(), p1.getCurrRow());
            animateToken(p1, player1, duplicatePlayer1);
        } else{
            gridPane.getChildren().remove(player2);
//            player2.setVisible(false);
            gridPane.add(player2, p2.getCurrColumn(), p2.getCurrRow());
            animateToken(p2, player2, duplicatePlayer2);
        }
    }

    @FXML
    Label player1Name;
    @FXML
    Label player2Name;

    public void displayPlayerName() {
        if (PlayerController.getPlayerName1().equals("")) {
            player1Name.setText("Player 1");
        }
        else {
            player1Name.setText(PlayerController.getPlayerName1());
        }

        if (PlayerController.getPlayerName2().equals("")) {
            player2Name.setText("Player 2");
        }
        else {
            player2Name.setText(PlayerController.getPlayerName2());
        }
    }

    @FXML
    private ImageView arrowLeft;
    @FXML
    private ImageView arrowRight;

    @FXML
    private ImageView pp1;
    @FXML
    private ImageView pp2;

    TranslateTransition translation1 = new TranslateTransition();
    TranslateTransition translation2 = new TranslateTransition();
    ScaleTransition scale1 = new ScaleTransition();
    ScaleTransition scale2 = new ScaleTransition();

    public void valTurn () {

        if (turn == 1) {
            scale2.pause();
            translation2.pause();

            scale1.play();
            translation1.play();
        }
        else {
            translation1.pause();
            scale1.pause();

            scale2.play();
            translation2.play();
        }
    }

    @FXML
    private AnchorPane exitBck;
    @FXML
    private AnchorPane logoutPopUp;
    @FXML
    private ImageView exitYesBtn;
    @FXML
    private ImageView exitNoBtn;

    @FXML
    public void logoutPopUp() {
        exitBck.setVisible(true);
        exitBck.setDisable(true);
        logoutPopUp.setVisible(true);
        FadeTransition fd_transition = new FadeTransition();
        fd_transition.setNode(logoutPopUp);
        fd_transition.setInterpolator(Interpolator.LINEAR);
        fd_transition.setDuration(Duration.millis(600));
        fd_transition.setFromValue(0);
        fd_transition.setToValue(1);
        fd_transition.play();
    }

    @FXML
    public void resumeGame() {
        FadeTransition fd_transition = new FadeTransition();
        fd_transition.setNode(logoutPopUp);
        fd_transition.setInterpolator(Interpolator.LINEAR);
        fd_transition.setDuration(Duration.millis(800));
        fd_transition.setFromValue(1);
        fd_transition.setToValue(0);
        fd_transition.play();
        logoutPopUp.setVisible(false);
        exitBck.setVisible(false);
        exitBck.setDisable(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayPlayerName();

        scale1.setNode(pp1);
        scale1.setDuration(Duration.millis(600));
        scale1.setInterpolator(Interpolator.LINEAR);
        scale1.setCycleCount(Animation.INDEFINITE);
        scale1.setByX(0.2);
        scale1.setByY(0.2);
        scale1.setAutoReverse(true);


        scale2.setNode(pp2);
        scale2.setDuration(Duration.millis(600));
        scale2.setInterpolator(Interpolator.LINEAR);
        scale2.setCycleCount(Animation.INDEFINITE);
        scale2.setByX(0.2);
        scale2.setByY(0.2);
        scale2.setAutoReverse(true);

        translation1.setNode(arrowLeft);
        translation1.setDuration(Duration.millis(600));
        translation1.setCycleCount(Animation.INDEFINITE);
        translation1.setByX(15);
        translation1.setAutoReverse(true);

        translation2.setNode(arrowRight);
        translation2.setDuration(Duration.millis(600));
        translation2.setCycleCount(Animation.INDEFINITE);
        translation2.setByX(-15);
        translation2.setAutoReverse(true);

        valTurn();
    }

    @FXML
    private AnchorPane endBck;
    @FXML
    private AnchorPane WinnerBck;
    @FXML
    private Label WinnerName;
    @FXML
    private ImageView WinnerExit;


    @FXML
    public void WinnerPageDisplay() {
        if (p1.getCurrPoints() == 100 || p2.getCurrPoints() == 100) {
            endBck.setVisible(true);
            endBck.setDisable(true);
            WinnerBck.setVisible(true);
            System.out.println(winnerPName);
            WinnerName.setText(winnerPName);
            FadeTransition fd_transition = new FadeTransition();
            fd_transition.setNode(WinnerBck);
            fd_transition.setInterpolator(Interpolator.LINEAR);
            fd_transition.setDuration(Duration.millis(600));
            fd_transition.setFromValue(0);
            fd_transition.setToValue(1);
            fd_transition.play();
        }

    }

    @FXML
    public void terminateGame(MouseEvent event) {
        stage = (Stage)endBck.getScene().getWindow();
        stage.close();
    }


    private static String winnerPName;

    public static String winnerPlayerName() {
        return winnerPName;
    }

}