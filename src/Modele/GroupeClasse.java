package Modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.ObservableList;

public class GroupeClasse {
	private static ObservableList<String> listeClasses;
	private String nom;
	private ObservableList <Eleve>listeEleves;
	
	@Override
	public String toString() {
		return nom;
		
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ObservableList<Eleve> getListeEleves() {
		return listeEleves;
	}
	public void setListeEleves(ObservableList<Eleve> listeEleves) {
		this.listeEleves = listeEleves;
	}
	public static ObservableList<String> getListeClasses() {
		File fichierClasses = new File("classes.dat");		
		if (fichierClasses.exists()) {
			try {
				FileReader fr = new FileReader(fichierClasses);
				BufferedReader br = new BufferedReader(fr);

				String dataString = br.readLine();
				String[] data = dataString.split("[|]");
				
				
				fr.close();
				br.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		return listeClasses;
	}
}
