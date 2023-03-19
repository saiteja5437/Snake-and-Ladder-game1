package com.example.snakeandladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {

    private static Board gameboard = new Board();
    private String name;

    private int position;

    private Circle coin;

    public void setStart(){
        position = 0;
        coin.setTranslateX(20);
        coin.setTranslateY(snakeAndLadder.tileSize*snakeAndLadder.height+25);
    }

    public Player(String name, int coinSize, Color coinColor){
        this.name = name;
        coin = new Circle(coinSize);
        position = 0 ;
//        movePlayer(0);
        coin.setFill(coinColor);
        setStart();

    }


    private TranslateTransition translatePlayer(){
        TranslateTransition move = new TranslateTransition(Duration.millis(1000),coin);
        move.setToX(gameboard.getXCoordinates(position));
        move.setToY(gameboard.getYCoordinates(position));
        move.setAutoReverse(false);
        return move;

    }

    public void movePlayer(int diceValue){

        if(position+diceValue<=100){

            position+=diceValue;
            TranslateTransition secondMove = null,firstMove = translatePlayer();
            firstMove.play();
            int newposition = gameboard.getsnakesAndLadders(position);
            if(newposition != position){
                position = newposition;
                secondMove = translatePlayer();
            }

            if(secondMove == null){
                firstMove.play();
            }
            else {
                SequentialTransition seq = new SequentialTransition(firstMove,new PauseTransition(Duration.millis(500)),secondMove);
                seq.play();
            }


        }
    }

    public boolean checkWinner(){
        if(position == 100){
            return true;
        }
        else
            return false;

    }





    public static Board getGameboard() {
        return gameboard;
    }

    public static void setGameboard(Board gameboard) {
        Player.gameboard = gameboard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Circle getCoin() {
        return coin;
    }

    public void setCoin(Circle coin) {
        this.coin = coin;
    }
}
