package com.midtermProject.battleship;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;

public class BattleshipMain extends Application {

    private boolean running = false;
    private Board enemyBoard, playerBoard;
    
    public static Label botDiff;
    
    private int shipsToPlace = 5;

    private boolean enemyTurn = false;

    private Random random = new Random();
    
    public static BorderPane root;

    private Parent createContent() {
        root = new BorderPane();
        // Add padding for playing scene
        root.setPadding(new Insets(15, 15, 15, 15));
        root.setPrefSize(600, 800);
        
        botDiff = new Label("Bot difficulty: Easy");
        
        root.setTop(botDiff);
        //Create a new button for pause
        InputStream inputStream = getClass().getResourceAsStream("pause-img.jpg");
        Image image = new Image(inputStream, 30.0, 30.0, true, true);
        ImageView imageView = new ImageView(image);
        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
        	
        	@Override
        	public void handle(ActionEvent event) {
				try {
					Parent settings_view_parent = FXMLLoader.load(getClass().getResource("SettingsView.fxml"));
	        		Scene settings_view_scene = new Scene(settings_view_parent);
//	        		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//	        		app_stage.hide();
	        		Stage settings_stage = new Stage();
	        		settings_stage.setTitle("Settings");
	        		settings_stage.setScene(settings_view_scene);
	        		settings_stage.initModality(Modality.APPLICATION_MODAL);
	        		settings_stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        
        pauseButton.setGraphic(imageView);
        root.setRight(pauseButton);
        BorderPane.setMargin(pauseButton, new Insets(10, 10, 10, 10));

        
        
        
        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN");
                System.exit(0);
            }

            if (enemyTurn) {
            	if(Ai.botDifficult==Ai.EASY)
            		Ai.enemyMoveEasy(enemyTurn, playerBoard);
            	else if(Ai.botDifficult==Ai.MEDIUM)
            		Ai.enemyMoveMedium(enemyTurn, playerBoard);
            	else if(Ai.botDifficult==Ai.HARD)
            		Ai.enemyMoveHard(enemyTurn, playerBoard);
            }
        });

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }
        });

        Text player = new Text("YOU");
        VBox playerBox = new VBox(50,playerBoard,player);
        VBox.setMargin(player,new Insets(0,0,0,150));

        Text enemy = new Text("ENEMY");
        VBox enemyBox = new VBox(50,enemyBoard,enemy);
        VBox.setMargin(enemy,new Insets(0,0,0,150));

        HBox hbox = new HBox(50, playerBox, enemyBox);
        hbox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(hbox,new Insets(100,0,0,0));

        root.setCenter(hbox);

        return root;
    }

    private Parent settingsScene() {
    	VBox root = new VBox(10);
    	//Horizontal box for sound
    	HBox hboxSound = new HBox(5);

    	//Turn on/off sound
    	//ImageView
    	InputStream inputStream = getClass().getResourceAsStream("sound-img.png");
    	Image soundImage = new Image(inputStream, 30, 20, true, true);
    	ImageView soundImageView = new ImageView(soundImage);
    	
    	CheckBox soundCheckBox = new CheckBox();
    	Label soundText = new Label("Sound");
    	hboxSound.getChildren().addAll(soundImageView, soundCheckBox, soundText);
    	hboxSound.setAlignment(Pos.CENTER);
    	
    	//HBox for reset score
    	HBox hboxResetScore = new HBox(5);
    	//Reset score button
    	Button resetScoreBtn = new Button("Reset score");
    	hboxResetScore.getChildren().add(resetScoreBtn);
    	hboxResetScore.setAlignment(Pos.CENTER);

    	//HBox for back
    	HBox hboxBack = new HBox(5);
    	//Reset score button
    	Button backBtn = new Button("Back");
    	hboxBack.getChildren().add(backBtn);
    	hboxBack.setAlignment(Pos.CENTER);
    	
    	
    	
    	root.getChildren().addAll(hboxSound, hboxResetScore, hboxBack);
    	
    	return root;
    }

    private void startGame() {
        // place enemy ships
        int type = 5;

        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        running = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
//        System.out.println(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
