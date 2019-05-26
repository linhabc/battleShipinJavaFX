package com.midtermProject.battleship;

import com.midtermProject.battleship.MenuButton;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GameMenu extends Parent {
	public GameMenu() {
		VBox menu0 = new VBox (15);
		VBox menu1 = new VBox (15);
		
		menu0.setTranslateX(100);
		menu0.setTranslateY(200);
		
		menu1.setTranslateX(100);
		menu1.setTranslateY(200);
		
		final int offset = 400;
		
		menu1.setTranslateX(offset);
		
//		MenuButton btnResume = new MenuButton ("Resume");
//		btnResume.setOnMouseClicked(event -> {
//			FadeTransition ft = new FadeTransition (Duration.seconds(0.5),this);
//			ft.setFromValue(1);
//			ft.setOnFinished(evt -> setVisible(false));
//			ft.play();
//		});
//		
//		MenuButton btnOptions = new MenuButton ("Options");
//		btnOptions.setOnMouseClicked (event -> {
//			
//		});
		
		MenuButton btnPlay = new MenuButton ("Play");
		btnPlay.setOnMouseClicked (event -> {
//			System.out.println("Go!");
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			BattleshipMain battleshipmain = new BattleshipMain();
			Scene scene = new Scene(battleshipmain.createContent());
			stage.setScene(scene);
		});
		
		MenuButton btnExit = new MenuButton ("Exit");
		btnExit.setOnMouseClicked (event -> {
			System.exit(0);
		});
		
//		MenuButton btnBack = new MenuButton ("Back");
//		btnBack.setOnMouseClicked (event -> {
//			
//		});
		
//		MenuButton btnSound = new MenuButton("SOUND");
//		MenuButton btnVideo = new MenuButton("VIDEO");
		
		menu0.getChildren().addAll(btnPlay,btnExit);
//		menu1.getChildren().addAll(btnBack,btnSound,btnVideo);
		
		Rectangle bg = new Rectangle (800,600);
		bg.setFill(Color.GREY);
		bg.setOpacity(0.4);
		
		getChildren().addAll(bg,menu0);
	}
}
