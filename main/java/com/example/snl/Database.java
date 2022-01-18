package com.example.snl;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    public static ArrayList<Snake> snakeList = new ArrayList<>();
    public static ArrayList<Ladder> LadderList = new ArrayList<>();
    public static HashMap<ArrayList<Integer>, Integer> pointsFinder = new HashMap<>();

    public void init(){

//      Snakes
        Snake snake1 = new Snake(9, 8, 8, 9);
        Snake snake2 = new Snake(4, 6, 6, 8);
        Snake snake3 = new Snake(4, 4, 2, 8);
        Snake snake4 = new Snake(2, 5, 1, 7);
        Snake snake5 = new Snake(9, 1, 9, 5);
        Snake snake6 = new Snake(6, 0, 7, 4);
        Snake snake7 = new Snake(5, 2, 6, 4);
        Snake snake8 = new Snake(0, 1, 2, 3);
        Snake snake9 = new Snake(4, 0, 4, 3);
        Snake snake10 = new Snake(1, 0, 2, 2);

//      Ladders
        Ladder ladder1 = new Ladder(3, 9, 4, 7);
        Ladder ladder2 = new Ladder(7, 9, 9, 6);
        Ladder ladder3 = new Ladder(0, 7, 0, 4);
        Ladder ladder4 = new Ladder(7, 7, 5, 5);
        Ladder ladder5 = new Ladder(8, 6, 7, 5);
        Ladder ladder6 = new Ladder(1, 5, 0, 2);
        Ladder ladder7 = new Ladder(8, 4, 7, 3);
        Ladder ladder8 = new Ladder(2, 4, 3, 2);
        Ladder ladder9 = new Ladder(8, 3, 7, 0);
        Ladder ladder10 = new Ladder(3, 1, 2, 0);


//      Adding Snakes into the ArrayList
        snakeList.add(snake1);
        snakeList.add(snake2);
        snakeList.add(snake3);
        snakeList.add(snake4);
        snakeList.add(snake5);
        snakeList.add(snake6);
        snakeList.add(snake7);
        snakeList.add(snake8);
        snakeList.add(snake9);
        snakeList.add(snake10);

//      Adding ladders into the ArrayList
        LadderList.add(ladder1);
        LadderList.add(ladder2);
        LadderList.add(ladder3);
        LadderList.add(ladder4);
        LadderList.add(ladder5);
        LadderList.add(ladder6);
        LadderList.add(ladder7);
        LadderList.add(ladder8);
        LadderList.add(ladder9);
        LadderList.add(ladder10);


//      HashMap Addition
        int cellNum = 0;
        for (int row = 9; row >= 0; row--) {
            if (row%2 == 1) {
                for (int col = 0; col <= 9; col++) {
                    cellNum++;
                    ArrayList<Integer> cell = new ArrayList<>();
                    cell.add(row);
                    cell.add(col);
                    pointsFinder.put(cell, cellNum);
                }
            }
            else {
                for (int col = 9; col >= 0; col--) {
                    cellNum++;
                    ArrayList<Integer> cell = new ArrayList<>();
                    cell.add(row);
                    cell.add(col);
                    pointsFinder.put(cell, cellNum);
                }
            }
        }


    }
}
