package model;

import java.util.HashMap;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Famille")
public class Famille {

	String familyName;
	HashMap<String,Personne> familyMembers;
	int size;
	String link;
	
	public Famille(){
		familyName = "";
		familyMembers = new HashMap<String,Personne>();
		size = 0;
		link = "";
	}
	
	public Famille(String familyName, int size, HashMap<String,Personne> familyMembers) {
		super();
		this.familyName = familyName;
		this.familyMembers = familyMembers;
		this.size = size;
		this.link = "http://localhost:8080/REST/"+familyName;
	}
	
	
	
	public HashMap<String, Personne> getFamilyMembers() {
		return familyMembers;
	}



	public void setFamilyMembers(HashMap<String, Personne> familyMembers) {
		this.familyMembers = familyMembers;
	}



	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
