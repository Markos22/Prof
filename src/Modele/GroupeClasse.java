package Modele;

import java.util.ArrayList;
import javafx.collections.ObservableList;

public class GroupeClasse {
	private String nom;
	private ObservableList <Eleve>listeEleves;
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
}
