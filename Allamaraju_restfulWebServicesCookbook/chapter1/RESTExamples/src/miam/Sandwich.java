package miam;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sandwich {
	
	private int id;
	private String nom;
	private int prix;
	private String description;
	
	public Sandwich() {
		
	}
	
	public Sandwich(int id, String nom, int prix, String description) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.description = description;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
