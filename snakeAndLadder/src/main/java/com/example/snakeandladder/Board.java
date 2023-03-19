package com.example.snakeandladder;

import javafx.util.Pair;

import java.net.StandardSocketOptions;
import java.util.ArrayList;

public class Board {

    private ArrayList<Pair<Integer,Integer>> positionCoordinates;
    private ArrayList<Integer> snakesAndLadders;

    public Board(){
        populatePositionCoordinates();
        setSnakesAndLadders();
    }

    private void populatePositionCoordinates(){


        positionCoordinates = new ArrayList<>();
        positionCoordinates.add(new Pair<>(0,0));
        for (int i = 0; i < snakeAndLadder.height; i++) {
            for (int j = 0; j < snakeAndLadder.width; j++) {
                int xChord = 0;
                if (i%2 == 0){
                    xChord = j*snakeAndLadder.tileSize + snakeAndLadder.tileSize/2;
                }
                else{
                    xChord = snakeAndLadder.height*snakeAndLadder.tileSize - j*snakeAndLadder.tileSize - snakeAndLadder.tileSize/2;
                }

                int yChord = snakeAndLadder.height*snakeAndLadder.tileSize - i*snakeAndLadder.tileSize - snakeAndLadder.tileSize/2;

                positionCoordinates.add(new Pair<>(xChord,yChord));
            }

        }
    }


    private void setSnakesAndLadders(){
        snakesAndLadders = new ArrayList<>();

        for (int i = 0; i < 101; i++) {
            snakesAndLadders.add(i);
        }
        snakesAndLadders.set(1,38);
        snakesAndLadders.set(4,14);
        snakesAndLadders.set(9,31);
        snakesAndLadders.set(21,42);
        snakesAndLadders.set(28,84);
        snakesAndLadders.set(51,67);
        snakesAndLadders.set(80,99);
        snakesAndLadders.set(72,91);

        snakesAndLadders.set(17,7);
        snakesAndLadders.set(54,34);
        snakesAndLadders.set(62,19);
        snakesAndLadders.set(64,60);
        snakesAndLadders.set(87,36);
        snakesAndLadders.set(93,73);
        snakesAndLadders.set(94,75);
        snakesAndLadders.set(98,79);

    }




    public int getXCoordinates(int position){
        if (position>0 && position <= 100){
            return positionCoordinates.get(position).getKey();
        }
        return -1;
    }

    public int getYCoordinates(int position){
        if (position>0 && position <= 100){
            return positionCoordinates.get(position).getValue();
        }
        return -1;
    }

    public int getsnakesAndLadders(int position){
        return snakesAndLadders.get(position);
    }

    public static void main(String[] args) {
        Board b = new Board();

        for (int i = 0; i < b.positionCoordinates.size(); i++) {
            System.out.println(i + " " + b.positionCoordinates.get(i).getKey() +" "+ b.positionCoordinates.get(i).getValue());
        }
    }

}
