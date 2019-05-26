package com.midtermProject.battleship;

import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class SettingsViewController {

	
	@FXML
	private Button backgroundSoundButton;
	static boolean tickBackgroundSoundButton;
	@FXML
	private Button animationSoundButton;
	static boolean tickAnimationSoundButton;
	@FXML
	private Button backButton;
	@FXML
	public Image backgroundSoundImage;
	public ImageView backgroundSoundImageView;
	@FXML
	public Image animationSoundImage;
	public ImageView animationSoundImageView;

    public void loadTickBackgroundSoundButton(boolean _tickBackgroundSoundButton) {
    	tickBackgroundSoundButton = _tickBackgroundSoundButton;
        System.out.println("tickSoundCheckBox " + tickBackgroundSoundButton);
    }
    public void loadTickAnimationSoundButton(boolean _tickAnimationSoundButton) {
    	tickAnimationSoundButton = _tickAnimationSoundButton;
        System.out.println("tickAnimationSoundButton " + tickAnimationSoundButton);
    }
	
	@FXML
	private void handleBackgroundSoundButton(ActionEvent event) {
		System.out.println("Clicked background sound button");
		AudioClip backgroundAudio = BattleshipMain.backgroundAudio;
		if (tickBackgroundSoundButton == false) {
			backgroundAudio.setCycleCount(AudioClip.INDEFINITE);
			backgroundAudio.play();
			tickBackgroundSoundButton = true;
//	        InputStream inputStream = this.getClass().getResourceAsStream("/resources/sound-img.png");
//	        backgroundSoundImage = new Image(inputStream, 30.0, 30.0, true, true);
//			backgroundSoundImageView.setImage(backgroundSoundImage);
//			System.out.println(soundImage);
//			backgroundSoundImageView.setVisible(true);
		}
		else {
			backgroundAudio.stop();
			tickBackgroundSoundButton = false;
//			backgroundSoundImageView.setVisible(false);
		}
	}

	@FXML
	private void handleAnimationSoundButton(ActionEvent event) {
		System.out.println("Clicked animation sound button");
		if (tickAnimationSoundButton == false) {
			tickAnimationSoundButton = true;
//			animationSoundImageView.setVisible(true);
		}
		else {
			tickAnimationSoundButton = false;
//			animationSoundImageView.setVisible(false);
		}
		
	}
	@FXML
	private void handleBackButton(ActionEvent event) throws IOException {
		System.out.println("Clicked back button!");
//		Parent settings_view_parent = FXMLLoader.load(getClass().getResource("SettingsView.fxml"));
//		Scene settings_view_scene = new Scene(settings_view_parent);
		Stage settings_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		settings_stage.hide();
//		System.out.println(settings_stage);
//		app_stage.setScene(settings_view_scene);
//		app_stage.show();
		BattleshipMain.storeTickBackgroundSoundButton(tickBackgroundSoundButton);
		BattleshipMain.storeAnimationSoundButton(tickAnimationSoundButton);
		BattleshipMain.storeBackgroundSoundImageView(backgroundSoundImageView);
	}
	@FXML
	private void handleDifficultyButton(ActionEvent event) throws IOException {
		Parent difficulties_view_parent = FXMLLoader.load(getClass().getResource("HandleDifficulty.fxml"));
		Scene difficulties_view_scene = new Scene(difficulties_view_parent);
		Stage settings_stage = new Stage();
		settings_stage.setTitle("Difficulties");
		settings_stage.setScene(difficulties_view_scene);
		settings_stage.initModality(Modality.APPLICATION_MODAL);
		settings_stage.show();
	}
}
