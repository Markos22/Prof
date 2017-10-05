import Modele.GroupeClasse;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scenes.AjouterClasses;
import scenes.AjouterEleves;

public class Main extends Application {

	/**
	 * ============= LOGICIEL PROF =============
	 * 
	 * @author Marc MOTTEZ
	 * @param args
	 */
	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Image icone = new Image("file:Prof.jpg");
		primaryStage.getIcons().add(icone);

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 700, 500);

		/*********************
		 * LES MENUS *
		 *********************/

		Menu mFichier = new Menu("_Fichier");
		Menu mDonnees = new Menu("_Données personnelles");
		Menu mNotes = new Menu("_Notes");
		Menu mAide = new Menu("?");

		/**********************
		 * LES ITEMS DE MENUS *
		 **********************/

		MenuItem mClasses = new MenuItem("Ajouter des _classes");
		mClasses.setAccelerator(KeyCombination.keyCombination("Shift+F2"));
		mClasses.setOnAction(e -> {
			ajouterClasses(primaryStage, scene);

		});

		MenuItem mEleves = new MenuItem("Ajout_er des élèves");
		mEleves.setAccelerator(KeyCombination.keyCombination("F2"));
		mEleves.setOnAction(e -> {
			ajouterEleves(primaryStage, scene);
		});

		SeparatorMenuItem sep = new SeparatorMenuItem();

		/*
		 * Pour quitter l'application
		 */
		MenuItem mQuitter = new MenuItem("_Quitter");
		mQuitter.setAccelerator(KeyCombination.keyCombination("Esc"));
		ImageView exitImage = new ImageView(new Image(getClass().getResourceAsStream("exit.png")));
		mQuitter.setGraphic(exitImage);
		mQuitter.setOnAction(e -> {
			primaryStage.close();
		});

		MenuItem mHelp = new MenuItem("Ouvrir la _documentation");
		mHelp.setAccelerator(KeyCombination.keyCombination("F1"));
		mHelp.setOnAction(e -> {
			this.getHostServices().showDocument("Help.pdf");
		});

		mFichier.getItems().addAll(mClasses, mEleves, sep, mQuitter);
		mAide.getItems().add(mHelp);

		VBox top = new VBox();
		root.setTop(top);

		MenuBar mb = new MenuBar(mFichier, mDonnees, mNotes, mAide);
		top.getChildren().add(mb);

		/*
		 * ============== BARRE D'OUTILS ==============
		 */
		Button btnQuitter = new Button();
		exitImage = new ImageView(new Image(getClass().getResourceAsStream("exit.png")));
		btnQuitter.setGraphic(exitImage);
		btnQuitter.setTooltip(new Tooltip("Quitter"));
		btnQuitter.setOnAction(e -> Platform.exit());

		Button btnAjouterClasses = new Button();
		ImageView classe = new ImageView(new Image(getClass().getResourceAsStream("classe.png")));
		btnAjouterClasses.setGraphic(classe);
		btnAjouterClasses.setTooltip(new Tooltip("Ajouter des classes"));
		btnAjouterClasses.setOnAction(e->{
			ajouterClasses(primaryStage, scene);
		});

		ToolBar tb = new ToolBar(btnQuitter, btnAjouterClasses);

		top.getChildren().add(tb);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	/*
	 * *********
	 * ACTIONS *
	 * *********
	 */
	
	private void ajouterClasses(Stage stage, Scene scene) {
		BorderPane rootClasses = new BorderPane();
		AjouterClasses creerClasses = new AjouterClasses(stage, scene, rootClasses, 600, 400);
		rootClasses.setPadding(new Insets(10));
		stage.setScene(creerClasses);
	}
	
	private void ajouterEleves(Stage stage, Scene scene) {
		BorderPane rootEleves = new BorderPane();
		AjouterEleves creerEleves = new AjouterEleves(stage, scene, rootEleves, 800,600);
		rootEleves.setPadding(new Insets(10));
		stage.setScene(creerEleves);
	}
}
