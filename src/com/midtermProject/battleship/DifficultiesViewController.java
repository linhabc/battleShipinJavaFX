package com.midtermProject.battleship;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DifficultiesViewController {

	
	@FXML
	private Button easy;
	@FXML
	private Button medium;

	@FXML
	private void handleEasyButton(ActionEvent event) throws IOException {
		Ai.botDifficult = Ai.EASY;
		BattleshipMain.botDiff = new Label("Bot difficulty: Easy");
		BattleshipMain.root.setTop(BattleshipMain.botDiff);
		Stage settings_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		settings_stage.hide();
	}
	@FXML
	private void handleMediumButton(ActionEvent event) throws IOException {
		Ai.botDifficult = Ai.NOTEASY;
		BattleshipMain.botDiff = new Label("Bot difficulty: Not easy");
		BattleshipMain.root.setTop(BattleshipMain.botDiff);
		Stage settings_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		settings_stage.hide();
	}
	
}
