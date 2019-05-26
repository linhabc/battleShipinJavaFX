package com.midtermProject.battleship;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import com.midtermProject.battleship.GameMenu;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class BattleshipMain extends Application {

    private boolean running = false;
    private Board enemyBoard, playerBoard;
    
    public static Label botDiff;
    public static Label winLoseText;
    public static Label LoseText;
    
//    private int shipsToPlace = 6;
    private int[] shipsToPlace = {5, 4, 3, 3, 2};
    private int i = 0;

    private boolean enemyTurn = false;

    private Random random = new Random();
    
    public static BorderPane root;
    public static Pane introRoot;
    
    public static GameMenu gameMenu;

    static boolean tickBackgroundSoundButton = true;
    static boolean tickAnimationSoundButton = true;
    //Audio
    public static AudioClip backgroundAudio = new AudioClip(BattleshipMain.class.getResource("/resources/battleship.wav").toString());
    public static ImageView backgroundSoundImageView;
    
    private static boolean firstTimePlayingSound = true;
    
    public static void storeTickBackgroundSoundButton(boolean _tickBackgroundSoundButton) {
    	tickBackgroundSoundButton = _tickBackgroundSoundButton;
        System.out.println("tickBackgroundSoundButton" + tickBackgroundSoundButton);
    }

    public static void storeAnimationSoundButton(boolean _tickAnimationSoundButton) {
    	tickAnimationSoundButton = _tickAnimationSoundButton;
        System.out.println("tickAnimationSoundButton" + tickAnimationSoundButton);
    }
    

    public static void storeBackgroundSoundImageView(ImageView _backgroundSoundImageView) {
    	backgroundSoundImageView = _backgroundSoundImageView;
        System.out.println("backgroundSoundImageView" + backgroundSoundImageView);
    }
    
    private Parent introContent() throws IOException {
    	introRoot = new Pane();
        System.out.println(introRoot);
    	introRoot.setPrefSize(800, 600);
		
		InputStream is = Files.newInputStream(Paths.get("src/resources/ship.jpg"));
		Image img = new Image(is);
		is.close();
		
		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(800);
		imgView.setFitHeight(600);
		
		gameMenu = new GameMenu();
//		gameMenu.setVisible(false);
		
		introRoot.getChildren().addAll(imgView,gameMenu);
		
//		scene.setOnKeyPressed(event -> {
//			if (event.getCode() == KeyCode.ESCAPE) {
//				if (!gameMenu.isVisible()) {
//					FadeTransition ft = new FadeTransition (Duration.seconds(0.5),gameMenu);
//					ft.setFromValue(0);
//					ft.setToValue(1);
//					
//					gameMenu.setVisible(true);
//					ft.play();
//				} else {
//					FadeTransition ft = new FadeTransition (Duration.seconds(0.5),gameMenu);
//					ft.setFromValue(1);
//					ft.setToValue(0);
//					ft.setOnFinished(evt -> gameMenu.setVisible(false));
//					ft.play();
//				}
//			}
//		});
        System.out.println("tickBackgroundSoundButton1" + tickBackgroundSoundButton);
        System.out.println("tickAnimationSoundButton1" + tickAnimationSoundButton);
        if (firstTimePlayingSound == true) {
        	backgroundAudio.setCycleCount(AudioClip.INDEFINITE);
    		backgroundAudio.play();
    		firstTimePlayingSound = false;
        }
		return introRoot;
    }
    
    public Parent createContent() {
        root = new BorderPane();
        // Add padding for playing scene
        root.setPadding(new Insets(15, 15, 15, 15));
        root.setPrefSize(600, 800);

        botDiff = new Label("Bot difficulty: Easy");
        BattleshipMain.root.setBottom(new Label("Place 5 ship to play, right click for horizontal, left click for vertical ship."));
        winLoseText = new Label();
        winLoseText.setVisible(false);
        HBox hBoxTop = new HBox(50, botDiff, winLoseText);
//        WinText = new Label("YOU WIN! Congratulation!");
//        LoseText = new Label("YOU LOSE! Don't worry!");
        
        root.setTop(hBoxTop);
        //Create a new button for pause
        InputStream inputStream = this.getClass().getResourceAsStream("/resources/settings-img.jpeg");

        Image image = new Image(inputStream, 30.0, 30.0, true, true);
        ImageView imageView = new ImageView(image);
        Button settingsButton = new Button("Settings");
        settingsButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
        	
        	@Override
        	public void handle(ActionEvent event) {
				try {
					Parent settings_view_parent = FXMLLoader.load(getClass().getResource("SettingsView.fxml"));
	        		Scene settings_view_scene = new Scene(settings_view_parent);
	        		SettingsViewController settingsViewController = new SettingsViewController();
	        		settingsViewController.loadTickBackgroundSoundButton(tickBackgroundSoundButton);
	        		settingsViewController.loadTickAnimationSoundButton(tickAnimationSoundButton);
//	        		System.out.println("1" + settingsViewController.backgroundSoundImage);
//	    	        if (tickBackgroundSoundButton == true) {
//		        		InputStream inputStream = this.getClass().getResourceAsStream("/resources/sound-img.png");
//		    	        settingsViewController.backgroundSoundImage = new Image(inputStream, 30.0, 30.0, true, true);
//		    	        settingsViewController.backgroundSoundImageView.setImage(settingsViewController.backgroundSoundImage);
//	    	        }
//	        		System.out.println("1" + backgroundSoundImageView);
//	        		settingsViewController.backgroundSoundImageView.setVisible(false);
//	        		settingsviewcontroller.modifySoundCheckBox(tickSoundCheckBox);
//	        		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//	        		app_stage.hide();
	        		Stage settings_stage = new Stage();
	        		settings_stage.setTitle("Settings");
	        		settings_stage.setScene(settings_view_scene);
	        		settings_stage.initModality(Modality.APPLICATION_MODAL);
	        		settings_stage.show();
	        		settings_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        	          public void handle(WindowEvent we) {
	        	              System.out.println("Stage is closing");
	        	              BattleshipMain.storeTickBackgroundSoundButton(SettingsViewController.tickBackgroundSoundButton);
	        	          }
	        	    });    
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        
        settingsButton.setGraphic(imageView);
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(15, 20, 10, 10));
        
        Button exitButton = new Button("Back");
        exitButton.setPrefSize(105, 40);
        exitButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		try {
					Scene scene = new Scene(introContent());
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setScene(scene);
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        vBox.getChildren().addAll(settingsButton, exitButton);
        
        root.setRight(vBox);
        BorderPane.setMargin(settingsButton, new Insets(10, 10, 10, 10));

        
        
        
        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN! Congratulation!");
                winLoseText.setText("YOU WIN! Congratulation!");
                winLoseText.setVisible(true);
//                System.exit(0);
            }

            if (enemyTurn) {
            	if(Ai.botDifficult==Ai.EASY)
            		Ai.enemyMoveEasy(enemyTurn, playerBoard);
            	else if(Ai.botDifficult==Ai.NOTEASY)
            		Ai.enemyMoveNotEasy(enemyTurn, playerBoard);
            }
        });

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace[i], event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (shipsToPlace[i++] == 2) {
                	BattleshipMain.root.setBottom(new Label("HIT ENEMY NOW!!!!"));
                    startGame();
                }else {
                	BattleshipMain.root.setBottom(new Label("Place "+shipsToPlace[i]+" more ship to play"));
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


    private void startGame() {
        // place enemy ships
//        int type = 6;
        int[] type = {5, 4, 3, 3, 2};
        int i = 0;

        while (i < type.length) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(type[i], Math.random() < 0.5), x, y)) {
                i++;
            }
        }

        running = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(introContent());
		scene.setOnKeyPressed(event -> {
		if (event.getCode() == KeyCode.ESCAPE) {
			if (!gameMenu.isVisible()) {
				FadeTransition ft = new FadeTransition (Duration.seconds(0.5),gameMenu);
				ft.setFromValue(0);
				ft.setToValue(1);
				
				gameMenu.setVisible(true);
				ft.play();
			} else {
				FadeTransition ft = new FadeTransition (Duration.seconds(0.5),gameMenu);
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setOnFinished(evt -> gameMenu.setVisible(false));
				ft.play();
			}
		}
		});
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
//        System.out.println("tickBackgroundSoundButton" + tickBackgroundSoundButton);
//        System.out.println(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
