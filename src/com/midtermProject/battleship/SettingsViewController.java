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
import javafx.stage.Stage;

public class SettingsViewController {

	
	@FXML
	private CheckBox soundCheckBox;
	@FXML
	private Button ResetScoreButton;
	@FXML
	private Button backButton;
	
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
	}
}
