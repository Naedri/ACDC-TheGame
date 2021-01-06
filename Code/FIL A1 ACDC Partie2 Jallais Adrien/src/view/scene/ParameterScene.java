/**
 * 
 */
package view.scene;

import java.io.File;

import api.Pioche;
import application.Main;
import controller.Services;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import nls.LanguageApp;
import view.button.AButton;
import view.button.ButtonChangeScene;
import view.button.ButtonQuit;
import view.component.DialogComponent;
import view.constant.ColorApp;
import view.constant.RadiusApp;
import view.constant.Spacing;
import view.exception.BuildDrawPileFromAPI;
import view.label.MainLabel;

/**
 * @author Adrien Jallais
 *
 */
public class ParameterScene extends MainScene {

	private HBox mainHBox;
	private VBox parameterBox;
	private HBox langB;
	private HBox drawB;
	private HBox buttonB;
	private DialogComponent dialogBox;

	private ChoiceBox<String> langCB;
	private LanguageApp languagesApp[] = new LanguageApp[] { LanguageApp.FRENCH, LanguageApp.ENGLISH };;
	private ObservableList<String> languages;

	private FileChooser fChooser;
	private AButton btFile;

	private static String filePath = Main.getPathDeck();
	private static LanguageApp lang = Main.getLang();
	private String filePathTemp;
	private LanguageApp langTemp;

	/**
	 * @param center
	 */
	public ParameterScene() {
		super(new HBox());
		mainHBox = (HBox) (this.getPane());
		Background backg = new Background(new BackgroundFill(ColorApp.INFOL.getColor(),
				new CornerRadii(RadiusApp.MEDIUM.getRadius()), Insets.EMPTY));
		mainHBox.setBackground(backg);
		initDialogBox();
		initParameterBox();
		mainHBox.getChildren().addAll(parameterBox, dialogBox);
	}

	private void initDialogBox() {
		dialogBox = new DialogComponent(Main.d.get("PARAMETERS_welcome"));
	}

	private void initParameterBox() {
		parameterBox = new VBox();
		parameterBox.setMaxSize(400, 100);
		parameterBox.setSpacing(Spacing.HIGH.getSpace());
		parameterBox.setAlignment(Pos.CENTER);
		initLangB();
		initDrawB();
		initButtonB();
		parameterBox.getChildren().addAll(langB, drawB, buttonB);
	}

	private void initLangB() {
		langB = new HBox();
		langB.setSpacing(Spacing.HIGH.getSpace());
		langB.setAlignment(Pos.CENTER_LEFT);
		// label
		MainLabel langL = new MainLabel(Main.d.get("PARAMETERS_langue"));
		langL.setWrapText(true);
		langB.getChildren().add(langL);
		// combo box
		languages = FXCollections.observableArrayList();
		for (LanguageApp lang : languagesApp) {
			languages.add(lang.getName());
		}
		langCB = new ChoiceBox<String>(languages);
		addActionToLangageBox();
		langB.getChildren().add(langCB);
	}

	private void initDrawB() {
		drawB = new HBox();
		drawB.setSpacing(Spacing.HIGH.getSpace());
		drawB.setAlignment(Pos.CENTER_LEFT);
		// label
		MainLabel langL = new MainLabel(Main.d.get("PARAMETERS_draw"));
		langL.setWrapText(true);
		langB.getChildren().add(langL);
		// fileChooser
		btFile = new ButtonQuit(Main.d.get("PARAMETERS_browse"));
		fChooser = new FileChooser();
		fChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fChooser.getExtensionFilters().add(new ExtensionFilter(Main.d.get("PARAMETERS_text"), "*.txt"));
		btFile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				fChooser.setTitle(Main.d.get("PARAMETERS_open_file"));
				File selected = fChooser.showOpenDialog(Main.mainStage);
				if (selected != null) {
					dialogBox.setDialog(Main.d.get("PARAMETERS_choosen_file"));
					openChoosenFile(selected);
				}
			}
		});
		// merge
		drawB.getChildren().addAll(langL, btFile);
	}

	private void initButtonB() {
		// validate button
		AButton bV = new ButtonChangeScene(Main.d.get("COMMON_validate"));
		bV.setOnAction((ActionEvent e) -> {
			if (filePathTemp != null) {
				filePath = filePathTemp;
				Main.setPathDeck(filePath);
			}
			if (langTemp != null) {
				lang = langTemp;
				Main.setLang(lang);
			}
			Services.changeScene(this, new MenuScene());
		});
		// cancel button
		AButton bC = new ButtonChangeScene(Main.d.get("COMMON_cancel"));
		bC.setOnAction((ActionEvent e) -> {
			filePathTemp = null;
			langTemp = null;
			Services.changeScene(this, new MenuScene());
		});
		// merge
		buttonB = new HBox();
		buttonB.getChildren().addAll(bV, bC);
		buttonB.setSpacing(Spacing.HIGH.getSpace());
		buttonB.setAlignment(Pos.CENTER);
	}

	/**
	 * 
	 * @param selected
	 */
	private void openChoosenFile(File selected) {
		try {
			Pioche deckTemp = Pioche.fromFile(selected.getAbsolutePath());
			if (deckTemp != null) {
				this.filePathTemp = selected.getAbsolutePath();
				this.dialogBox.setDialog(Main.d.get("PARAMETERS_choosen_file"));
				this.dialogBox.addDialog(filePathTemp);
			} else {
				throw new BuildDrawPileFromAPI();
			}
		} catch (Exception e) {
			dialogBox.setDialog(e.getMessage());
		}
	}

	private void addActionToLangageBox() {
		langCB.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
					langTemp = languagesApp[new_val.intValue()];
					this.dialogBox.setDialog(Main.d.get("PARAMETERS_choosen_language"));
					this.dialogBox.addDialog(langTemp.getName());
				});
	}

}
