package controller;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.scene.AMainScene;

public final class Services {

	public Services() {
		// TODO Auto-generated constructor stub
	}

	public static void changeScene(Scene currentScene, AMainScene newScene) {
		Stage thisStage;
		thisStage = (Stage) currentScene.getRoot().getScene().getWindow();
		thisStage.hide();
		thisStage.setScene(newScene);
		thisStage.show();
	}

	public static void quitApp(Scene currentScene) {
		Stage thisStage;
		thisStage = (Stage) currentScene.getRoot().getScene().getWindow();
		thisStage.hide();
		Platform.exit();
	}

}
