import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import scenes.AjouterClasses;

public class Main extends Application{

	public Scene scene;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Image icone = new Image("file:Prof.jpg");
		primaryStage.getIcons().add(icone);
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		
		BorderPane root = new BorderPane();
		scene = new Scene(root,700,500);
		
		Menu mFichier = new Menu("_Fichier");
		Menu mDonnees = new Menu("_Données personnelles");
		Menu mNotes = new Menu("_Notes");
		Menu mAide = new Menu("?");
		
		MenuItem mClasses = new MenuItem("Ajouter des _classes");
		mClasses.setAccelerator(KeyCombination.keyCombination("Shift+F2"));
		mClasses.setOnAction(e->{
			BorderPane rootClasses = new BorderPane();
			AjouterClasses creerClasses = new AjouterClasses(primaryStage,scene,rootClasses,600,400);
			rootClasses.setPadding(new Insets(10));
			primaryStage.setScene(creerClasses);
			
		});
		
		MenuItem mEleves = new MenuItem("Ajout_er des élèves");
		mEleves.setAccelerator(KeyCombination.keyCombination("F2"));
		
		SeparatorMenuItem sep = new SeparatorMenuItem();
		
		/*
		 * Pour quitter l'application
		 */
		MenuItem mQuitter = new MenuItem("_Quitter");
		mQuitter.setAccelerator(KeyCombination.keyCombination("Esc"));
		mQuitter.setOnAction(e->{
			primaryStage.close();
		});
		
		MenuItem mHelp = new MenuItem("Ouvrir la _documentation");
		mHelp.setAccelerator(KeyCombination.keyCombination("F1"));
		mHelp.setOnAction(e->{
			this.getHostServices().showDocument("Help.pdf");
		});
		
		/*
		 * Mon menu Fichier
		 */
		mFichier.getItems().addAll(mClasses,mEleves,sep,mQuitter);
		mAide.getItems().add(mHelp);
		
		/*
		 * J'ajoute les menus à la barre de menus
		 */
		MenuBar mb = new MenuBar(mFichier,mDonnees,mNotes,mAide);
		root.setTop(mb);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void btn_OK_Click() {
	}

}
