package com.example.snl;

import java.util.ArrayList;

public class Snake {
    private int headX, headY, tailX, tailY;
    ArrayList<ArrayList<Integer>> sankePath;

    public Snake(int headX, int headY, int tailX, int tailY) {
        this.headX = headX;
        this.headY = headY;
        this.tailX = tailX;
        this.tailY = tailY;
        this.sankePath = new ArrayList<>();
    }

    public int getHeadX() {
        return headX;
    }

    public void setHeadX(int headX) {
        this.headX = headX;
    }

    public int getHeadY() {
        return headY;
    }

    public void setHeadY(int headY) {
        this.headY = headY;
    }

    public int getTailX() {
        return tailX;
    }

    public void setTailX(int tailX) {
        this.tailX = tailX;
    }

    public int getTailY() {
        return tailY;
    }

    public void setTailY(int tailY) {
        this.tailY = tailY;
    }

    public ArrayList<ArrayList<Integer>> getSankePath() {
        return sankePath;
    }

    public void setSankePath(ArrayList<ArrayList<Integer>> sankePath) {
        this.sankePath = sankePath;
    }
}
