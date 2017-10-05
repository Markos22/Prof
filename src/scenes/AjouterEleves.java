package scenes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Modele.Eleve;
import Modele.GroupeClasse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.CheckBoxTableCell;
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
	Button saveAndClose;
	Button ajouter;
	Button annuler;
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
	CheckBox redoublant;
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
				ajouter_OnAction();
			}
			else if(ke.getCode() == KeyCode.ESCAPE) {
				annuler_OnAction();
			}
		});

		ObservableList<Eleve> listeDesEleves = FXCollections.observableArrayList();

		tableauDesEleves = new TableView<Eleve>(listeDesEleves);
		TableColumn<Eleve, String> nomEleve = new TableColumn<Eleve, String>("Nom");
		TableColumn<Eleve, String> prenomEleve = new TableColumn<Eleve, String>("Prénom");
		TableColumn<Eleve, String> groupeClEleve = new TableColumn<Eleve, String>("Classe");
		TableColumn<Eleve, Boolean> RedoublantEleve = new TableColumn<Eleve, Boolean>("Redoublant");
		TableColumn<Eleve, LocalDate> dateNaissanceEleve = new TableColumn<Eleve, LocalDate>("Date de Naissance");
		TableColumn<Eleve, String> adresseEleve = new TableColumn<Eleve, String>("Adresse");
		TableColumn<Eleve, String> codePostalEleve = new TableColumn<Eleve, String>("Code Postal");
		TableColumn<Eleve, String> villeEleve = new TableColumn<Eleve, String>("Ville");
		TableColumn<Eleve, String> telephoneEleve = new TableColumn<Eleve, String>("Téléphone");

		nomEleve.setCellValueFactory(new PropertyValueFactory<Eleve, String>("Nom"));
		prenomEleve.setCellValueFactory(new PropertyValueFactory<Eleve, String>("Prenom"));
		groupeClEleve.setCellValueFactory(new PropertyValueFactory<Eleve,String>("GroupeCl"));
		
		RedoublantEleve.setCellFactory(tc->new CheckBoxTableCell<Eleve, Boolean>());
		RedoublantEleve.setCellValueFactory(new PropertyValueFactory<Eleve, Boolean>("Redoublant"));
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
		}

		ch_groupeCl.setItems(classes);
		ch_groupeCl.setTooltip(tt_groupeCl);
		redoublant = new CheckBox("Redoublant");
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
		gridForm.add(redoublant, 2, 2);
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
		tableauDesEleves.getColumns().addAll(nomEleve, prenomEleve, groupeClEleve, RedoublantEleve, dateNaissanceEleve, adresseEleve,
				codePostalEleve, villeEleve, telephoneEleve);

		boutons.setAlignment(Pos.CENTER);
		root.setBottom(boutons);
		
		//Calcul de l'âge
		dp.setOnAction(e->{
			LocalDate date = dp.getValue();
			int age = date.until(LocalDate.now()).getYears();
			String ageEleve = String.valueOf(age);
			textAge.setText(ageEleve+" ans");
		});

		ajouter = new Button("Ajouter");
		ajouter.setOnAction(e -> ajouter_OnAction());
		
		saveAndClose = new Button("Sauvegarder et fermer");
		saveAndClose.setOnAction(e -> saveAndClose_OnAction());
		
		annuler = new Button("Annuler");
		annuler.setOnAction(e-> annuler_OnAction());

		boutons.getChildren().addAll(ajouter, saveAndClose, annuler);
		
		txt_nom.requestFocus();

	}

	private void ajouter_OnAction() {
		// TODO Auto-generated method stub
		Eleve eleve = new Eleve();
		eleve.setNom(txt_nom.getText());
		eleve.setPrenom(txt_prenom.getText());
		
		//pour la ChoiceBox, je dois transformer le nom en objet GroupeClasse
		GroupeClasse classe = new GroupeClasse();
		classe.setNom(ch_groupeCl.getValue());
		eleve.setGroupeCl(classe);
		eleve.setRedoublant(redoublant.isSelected());
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
		redoublant.setSelected(false);
		dp.setValue(LocalDate.now());
		txt_adresse.setText("");
		txt_cp.setText("");
		txt_ville.setText("");
		txt_tel.setText("");
		
		txt_nom.requestFocus();

	}

	private void saveAndClose_OnAction() {

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
			stage.setScene(mainScene);
		}

	}
	
	private void annuler_OnAction() {
		stage.setScene(mainScene);
	}

}
