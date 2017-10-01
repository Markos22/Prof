package scenes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Modele.Eleve;
import Modele.GroupeClasse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AjouterEleves extends Scene {
	TableView<Eleve> tableauDesEleves;
	Button fermer;
	Button ajouter;
	Stage stage;
	Scene mainScene;
	VBox centerBox;
	HBox boutons;
	GridPane gridForm;
	Label lbl_nom;
	Label lbl_prenom;
	Label lbl_groupeCl;
	Label lbl_dn;
	Label lbl_adresse;
	Label lbl_cp;
	Label lbl_ville;
	Label lbl_tel;
	TextField txt_nom;
	TextField txt_prenom;
	ChoiceBox<String> ch_groupeCl;
	DatePicker dp;
	Text textAge;
	TextField txt_adresse;
	TextField txt_cp;
	TextField txt_ville;
	TextField txt_tel;

	public AjouterEleves(Stage stage, Scene mainScene, BorderPane root, int width, int height) {
		super(root, width, height);
		this.stage = stage;
		this.mainScene = mainScene;
		this.setOnKeyPressed(ke -> {
			if (ke.getCode() == KeyCode.ENTER) {
				ajouter();
			}
		});

		ObservableList<Eleve> listeDesEleves = FXCollections.observableArrayList();

		tableauDesEleves = new TableView<Eleve>(listeDesEleves);
		TableColumn<Eleve, String> nomEleve = new TableColumn<Eleve, String>("Nom");
		TableColumn<Eleve, String> prenomEleve = new TableColumn<Eleve, String>("Prénom");
		TableColumn<Eleve, String> groupeClEleve = new TableColumn<Eleve, String>("Classe");
		TableColumn<Eleve, LocalDate> dateNaissanceEleve = new TableColumn<Eleve, LocalDate>("Date de Naissance");
		TableColumn<Eleve, String> adresseEleve = new TableColumn<Eleve, String>("Adresse");
		TableColumn<Eleve, String> codePostalEleve = new TableColumn<Eleve, String>("Code Postal");
		TableColumn<Eleve, String> villeEleve = new TableColumn<Eleve, String>("Ville");
		TableColumn<Eleve, String> telephoneEleve = new TableColumn<Eleve, String>("Téléphone");

		nomEleve.setCellValueFactory(new PropertyValueFactory<Eleve, String>("Nom"));
		prenomEleve.setCellValueFactory(new PropertyValueFactory<Eleve, String>("Prenom"));
		
		
		groupeClEleve.setCellValueFactory(new PropertyValueFactory<Eleve,String>("GroupeCl"));
		
		dateNaissanceEleve.setCellValueFactory(new PropertyValueFactory<Eleve, LocalDate>("DateNaissance"));
		
		adresseEleve.setCellValueFactory(new PropertyValueFactory<Eleve, String>("Adresse"));
		codePostalEleve.setCellValueFactory(new PropertyValueFactory<Eleve, String>("CodePostal"));
		villeEleve.setCellValueFactory(new PropertyValueFactory<Eleve, String>("Ville"));
		telephoneEleve.setCellValueFactory(new PropertyValueFactory<Eleve, String>("Telephone"));

		boutons = new HBox();
		centerBox = new VBox();
		boutons.setPadding(new Insets(10));
		boutons.setSpacing(15);
		gridForm = new GridPane();

		gridForm.setPadding(new Insets(10));
		gridForm.setVgap(10);
		gridForm.setHgap(10);

		lbl_nom = new Label("Nom");
		lbl_prenom = new Label("Prénom");
		lbl_groupeCl = new Label("Classe");
		lbl_dn = new Label("Date de Naissance");
		lbl_adresse = new Label("Adresse");
		lbl_cp = new Label("Code Postal");
		lbl_ville = new Label("Ville");
		lbl_tel = new Label("Téléphone");

		txt_nom = new TextField();
		txt_prenom = new TextField();
		ch_groupeCl = new ChoiceBox<String>();
		ch_groupeCl.setMinWidth(100.0);;
		
		Tooltip tt_groupeCl = new Tooltip("Choisissez dans la liste");
		
		//Je récupère la liste des classes
		File fichierClasses = new File("classes.dat");
		List<String> liste = new ArrayList<String>();
		ObservableList<String> classes = null;

		if (fichierClasses.exists()) {
			try {
				FileReader fr = new FileReader(fichierClasses);
				BufferedReader br = new BufferedReader(fr);
				String dataString = br.readLine();
				String[] data = dataString.split("[|]");
	
				for(int i=0;i<data.length;i++) {
					liste.add(data[i]);
				}
				
				classes = FXCollections.observableList(liste);
				
				fr.close();
				br.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
		}else {
			classes.add("fichier introuvable");
		}

		ch_groupeCl.setItems(classes);
		ch_groupeCl.setTooltip(tt_groupeCl);
		
		dp = new DatePicker();
		textAge = new Text("");
		txt_adresse = new TextField();
		txt_cp = new TextField();
		txt_ville = new TextField();
		txt_tel = new TextField();

		gridForm.add(lbl_nom, 0, 0);
		gridForm.add(txt_nom, 1, 0);
		gridForm.add(lbl_prenom, 0, 1);
		gridForm.add(txt_prenom, 1, 1);
		gridForm.add(lbl_groupeCl, 0, 2);
		gridForm.add(ch_groupeCl, 1, 2);
		gridForm.add(lbl_dn, 0, 3);
		gridForm.add(dp, 1, 3);
		gridForm.add(textAge, 2, 3);
		
		gridForm.add(lbl_adresse, 0, 4);
		gridForm.add(txt_adresse, 1, 4);
		gridForm.add(lbl_cp, 0, 5);
		gridForm.add(txt_cp, 1, 5);
		gridForm.add(lbl_ville, 2, 5);
		gridForm.add(txt_ville, 3, 5);
		gridForm.add(lbl_tel, 0, 6);
		gridForm.add(txt_tel, 1, 6);

		centerBox.getChildren().addAll(tableauDesEleves, gridForm);

		root.setCenter(centerBox);
		tableauDesEleves.getColumns().addAll(nomEleve, prenomEleve, groupeClEleve, dateNaissanceEleve, adresseEleve,
				codePostalEleve, villeEleve, telephoneEleve);

		boutons.setAlignment(Pos.CENTER);
		root.setBottom(boutons);
		
		dp.setOnAction(e->{
			LocalDate date = dp.getValue();
			int age = date.until(LocalDate.now()).getYears();
			String ageEleve = String.valueOf(age);
			textAge.setText(ageEleve+" ans");
		});

		ajouter = new Button("Ajouter");
		ajouter.setOnAction(e -> ajouter());
		fermer = new Button("Sauvegarder et fermer");
		fermer.setOnAction(e -> save_and_close());

		boutons.getChildren().addAll(ajouter, fermer);
		
		txt_nom.requestFocus();

	}

	private void ajouter() {
		// TODO Auto-generated method stub
		Eleve eleve = new Eleve();
		eleve.setNom(txt_nom.getText());
		eleve.setPrenom(txt_prenom.getText());
		
		//pour la ChoiceBox, je dois transformer le nom en objet GroupeClasse
		GroupeClasse classe = new GroupeClasse();
		classe.setNom(ch_groupeCl.getValue());
		eleve.setGroupeCl(classe);
		
		eleve.setDateNaissance(dp.getValue());
		eleve.setAdresse(txt_adresse.getText());
		eleve.setCodePostal(txt_cp.getText());
		eleve.setVille(txt_ville.getText());
		eleve.setTelephone(txt_tel.getText());

		tableauDesEleves.getItems().add(eleve);

		// et on vide les champs
		txt_nom.setText("");
		txt_prenom.setText("");
		ch_groupeCl.setValue(null);
		dp.setValue(LocalDate.MIN);
		txt_adresse.setText("");
		txt_cp.setText("");
		txt_ville.setText("");
		txt_tel.setText("");

	}

	private void save_and_close() {

		FileOutputStream fichier;
		ObjectOutputStream oos = null;

		try {
			fichier = new FileOutputStream("Eleves.dat");
			oos = new ObjectOutputStream(fichier);

			ObservableList<Eleve> listeEleves = tableauDesEleves.getItems();
			Eleve[] data = new Eleve[listeEleves.size()];
			listeEleves.toArray(data);
			for (Eleve el : data) {
				oos.writeObject(el);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.flush();
					oos.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}

	}

}
