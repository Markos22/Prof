package scenes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AjouterClasses extends Scene {	
	ListView<String> mesClasses;
	TextField nouvelleClasse;
	Label lbl_nouvelleClasse;
	Button creer;
	Button suppr;
	Button save;
	Stage stage;
	Scene mainScene;
	
	public AjouterClasses(Stage stage,Scene mainScene, BorderPane root,int width,int height) {
		super(root,width,height);
		this.stage = stage;
		this.mainScene = mainScene;
		this.setOnKeyPressed(ke->{
			if(ke.getCode()==KeyCode.ENTER){
				creer_click();
			}
			else if(ke.getCode()==KeyCode.ESCAPE) {
				annuler_click();
			}
		});

		mesClasses = new ListView<String>();
		mesClasses.setOnKeyPressed(ke->{
			if(ke.getCode()==KeyCode.DELETE){
				suppr_click();
			}
		});

		/**
		 * Récupération de la liste des classes sous forme de tableau de chaînes
		 */
		File fichierClasses = new File("classes.dat");
		if (fichierClasses.exists()) {
			try {
				FileReader fr = new FileReader(fichierClasses);
				BufferedReader br = new BufferedReader(fr);

				String dataString = br.readLine();
				String[] data = dataString.split("[|]");
				
				for(int i=0;i<data.length;i++){
					String s = data[i];
					mesClasses.getItems().add(s);
				}
				fr.close();
				br.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}

		
		mesClasses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		nouvelleClasse = new TextField();
		
		lbl_nouvelleClasse = new Label("Entrez le nom de la classe : ");
		creer = new Button("Ajouter");
		HBox nouveau = new HBox(10,lbl_nouvelleClasse,nouvelleClasse,creer);
		suppr = new Button("Supprimer la sélection");
		save = new Button("Enregistrer la liste");
		HBox boutons = new HBox(10,suppr,save);
		
		VBox vbox = new VBox(30);
		vbox.getChildren().addAll(mesClasses,nouveau,boutons);
		nouveau.setAlignment(Pos.CENTER);
		boutons.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		
		creer.setOnAction(e->creer_click());
		suppr.setOnAction(e->suppr_click());
		save.setOnAction(e->save_click());
		
		root.setCenter(vbox);
		nouvelleClasse.requestFocus();
		
	}

	private void save_click() {
		File fichier = new File("classes.dat");
		try {
			FileWriter fw = new FileWriter(fichier);
			BufferedWriter bw = new BufferedWriter(fw);
			
			List<String> listeClasses = mesClasses.getItems();
			String [] data = new String[listeClasses.size()];
			listeClasses.toArray(data);
			
			String dataString = String.join("|", data);
			bw.write(dataString);
			bw.flush();
			bw.close();
			fw.close();
			
			stage.setScene(mainScene);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void suppr_click() {
		List<String> selection = mesClasses.getSelectionModel().getSelectedItems();
		mesClasses.getItems().removeAll(selection);
	}

	private void creer_click() {
		String creationClasse = nouvelleClasse.getText();
		mesClasses.getItems().add(creationClasse);
		nouvelleClasse.clear();
		nouvelleClasse.requestFocus();
		
	}
	
	private void annuler_click() {
		stage.setScene(mainScene);
	}

}
