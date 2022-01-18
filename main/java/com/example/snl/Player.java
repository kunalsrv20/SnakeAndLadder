package com.example.snl;

import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private int currRow, currColumn;
    private boolean isOpen;
    private int currPoints;
    private final BoardController boardController;

    public Player(BoardController boardController){
        this.currRow = 10;
        this.currColumn = 0;
        this.isOpen = false;
        this.currPoints = 0;
        this.boardController = boardController;
    }

    public void movePlayer(int dieVal) throws InterruptedException {

        if (currPoints + dieVal == 100) {
            currPoints = 100;
        }
        else if(currPoints + dieVal > 100){
            return;
        }
        else{
            currPoints += dieVal;
        }

        if(currRow % 2 == 1){
            while(currColumn < 9 && dieVal > 0){
                currColumn++;
                dieVal--;
                boardController.perMoveAnimate();
            }


            if(dieVal > 0){
                currRow--;
                dieVal--;
                boardController.perMoveAnimate();
            }


            while(currColumn > 0 && dieVal > 0){
                currColumn--;
                dieVal--;

                boardController.perMoveAnimate();
            }

        }
        else{

            while(currColumn > 0 && dieVal > 0){
                currColumn--;
                dieVal--;
                boardController.perMoveAnimate();
            }

            if(dieVal > 0){
                currRow--;
                dieVal--;
                boardController.perMoveAnimate();
            }

            while(currColumn < 9 && dieVal > 0) {
                currColumn++;
                dieVal--;
                boardController.perMoveAnimate();
            }

        }
        checkForLadder();
        boardController.perMoveAnimate();
        checkForSnake();
        boardController.perMoveAnimate();
    }

    public void checkForSnake(){
        for(Snake s: Database.snakeList){
            if(s.getHeadY() == currRow && s.getHeadX() == currColumn){
                currRow = s.getTailY();
                currColumn = s.getTailX();

                ArrayList<Integer> toFind = new ArrayList<>();
                toFind.add(currRow);
                toFind.add(currColumn);

                currPoints = Database.pointsFinder.get(toFind);
                break;
            }
        }
    }
    public void checkForLadder(){
        for(Ladder l: Database.LadderList){
            if(l.getStartY() == currRow && l.getStartX() == currColumn){
                currRow = l.getEndY();
                currColumn = l.getEndX();

                ArrayList<Integer> toFind = new ArrayList<>();
                toFind.add(currRow);
                toFind.add(currColumn);

                currPoints = Database.pointsFinder.get(toFind);
                break;
            }
        }
    }

    public void openPlayer(int dieVal) throws InterruptedException {
        if(dieVal == 1){
//            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("BoardScene.fxml")));
//            BoardController boardController = loader.getController();
            if(boardController == null) System.exit(0);
            isOpen = true;
            currRow = 9;
            currColumn = 0;
            currPoints = 1;
            boardController.perMoveAnimate();
        }
    }

    public int getCurrRow() {
        return currRow;
    }

    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }

    public int getCurrColumn() {
        return currColumn;
    }

    public void setCurrColumn(int currColumn) {
        this.currColumn = currColumn;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getCurrPoints() {
        return currPoints;
    }
}
