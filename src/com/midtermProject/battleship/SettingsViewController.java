package com.midtermProject.battleship;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javafx.stage.Modality;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class SettingsViewController {

	
	@FXML
	static public CheckBox soundCheckBox = new CheckBox();
	static boolean tickSoundCheckBox;
	@FXML
	private Button ResetScoreButton;
	@FXML
	private Button backButton;
	

    public void loadTicketSoundCheckBox(boolean _tickSoundCheckBox) {
    	tickSoundCheckBox = _tickSoundCheckBox;
        System.out.println("tickSoundCheckBox " + tickSoundCheckBox);
    }
	
	@FXML
	private void handleSoundCheckBox(ActionEvent event) {
		System.out.println("Clicked sound check box");
		System.out.println(soundCheckBox.isSelected());
		AudioClip note = new AudioClip(this.getClass().getResource("/battleship.wav").toString());
		if (soundCheckBox.isSelected() && tickSoundCheckBox == false) {
			System.out.println("SoundCheckBox is selected");
			tickSoundCheckBox = true;
			note.play();
		}
		
		else if (!soundCheckBox.isSelected()) {
			System.out.println(soundCheckBox.isSelected());
			note.stop();
			tickSoundCheckBox = false;
		}
		else if (tickSoundCheckBox == true) {
			System.out.println("SoundCheckBox was selected");
			soundCheckBox.setSelected(true);
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
		BattleshipMain.storeTickSoundCheckBox(tickSoundCheckBox);
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
