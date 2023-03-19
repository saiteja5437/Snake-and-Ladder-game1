package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class snakeAndLadder extends Application {

    public static final int tileSize=40, width=10, height=10, buttonLine = tileSize*height+50, infoLine = tileSize*height+20;

    Player playerFirst, playerSecond;
    boolean firstPlayerTurn = true;
    boolean gameStart = false;
    int diceValue;

    //rolling Dice to generate random numbers between 1 to 6
    private int rollDice(){
        return (int)(Math.random()*6+1);
    }

    
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize+100);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Tile tile = new Tile(tileSize);
                tile.setTranslateX(i*tileSize);
                tile.setTranslateY(j*tileSize);
                root.getChildren().addAll(tile);
            }
        }

        Image img = new Image("C:\\Users\\Saite\\eclipse-workspace\\snakeAndLadder\\src\\img.png");
        ImageView boardImage = new ImageView();
        boardImage.setFitHeight(height*tileSize);
        boardImage.setFitWidth(width*tileSize);
        boardImage.setImage(img);

        root.getChildren().addAll(boardImage);


        Button player1 = new Button("player1");
        player1.setTranslateX(10);
        player1.setTranslateY(buttonLine);


        Button startButton = new Button("start");
        startButton.setTranslateX(180);
        startButton.setTranslateY(buttonLine);


        Button player2 = new Button("player2");
        player2.setTranslateX(300);
        player2.setTranslateY(buttonLine);


        Label diceLabel = new Label("start the game");
        diceLabel.setTranslateX(180);
        diceLabel.setTranslateY(infoLine);


        playerFirst = new Player("sai",tileSize/2, Color.AQUA);
        playerSecond = new Player("riaz",tileSize/2-5, Color.BLACK);

        player1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(firstPlayerTurn){
                        diceValue=rollDice();
                        diceLabel.setText("Dice :" + diceValue);
                        playerFirst.movePlayer(diceValue);
                        firstPlayerTurn = !firstPlayerTurn;


                        if(playerFirst.checkWinner()){
                            diceLabel.setText("Winner is "+ playerFirst.getName());
                            startButton.setText("restart");
                            startButton.setDisable(false);
                            firstPlayerTurn = true;
                            gameStart=false;
                        }


                    }
                }

            }
        });

        player2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(!firstPlayerTurn){
                        diceValue=rollDice();
                        diceLabel.setText("Dice :" + diceValue);
                        playerSecond.movePlayer(diceValue);
                        firstPlayerTurn = !firstPlayerTurn;
                        if(playerSecond.checkWinner()){
                            diceLabel.setText("Winner is "+ playerSecond.getName());
                            startButton.setText("restart");
                            startButton.setDisable(false);
                            firstPlayerTurn = true;
                            gameStart=false;
                        }
                    }
                }

            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStart = true;
                startButton.setDisable(true);
                playerFirst.setStart();
                playerSecond.setStart();
            }
        });

        root.getChildren().addAll(startButton, player1, player2, diceLabel, playerFirst.getCoin(),playerSecond.getCoin());

        return root;


    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}