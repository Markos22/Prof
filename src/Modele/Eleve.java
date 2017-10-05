package Modele;

import java.io.Serializable;
import java.time.LocalDate;

public class Eleve implements Serializable {
	/**
	 * Elèves
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String prenom;
	private transient boolean fille = false;
	private GroupeClasse groupeCl = null;
	private transient boolean redoublant = false;
	private LocalDate dateNaissance;
	private String adresse;
	private String CodePostal;
	private String Ville;
	private String telephone;
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the groupeCl
	 */
	public GroupeClasse getGroupeCl() {
		return groupeCl;
	}
	/**
	 * @param groupeCl the groupeCl to set
	 */
	public void setGroupeCl(GroupeClasse groupeCl) {
		this.groupeCl = groupeCl;
	}
	/**
	 * @return the fille
	 */
	public boolean isFille() {
		return fille;
	}
	/**
	 * @param fille the fille to set
	 */
	public void setFille(boolean fille) {
		this.fille = fille;
	}
	/**
	 * @return the redoublant
	 */
	public boolean isRedoublant() {
		return redoublant;
	}
	/**
	 * @param redoublant the redoublant to set
	 */
	public void setRedoublant(boolean redoublant) {
		this.redoublant = redoublant;
	}
	/**
	 * @return the dateNaissance
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	/**
	 * @param LocalDate the dateNaissance to set
	 */
	public void setDateNaissance(LocalDate localDate) {
		this.dateNaissance = localDate;
	}
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return CodePostal;
	}
	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		CodePostal = codePostal;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return Ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		Ville = ville;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	
}
