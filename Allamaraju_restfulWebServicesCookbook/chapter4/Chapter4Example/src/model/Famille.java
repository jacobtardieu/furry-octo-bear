package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Famille")
public class Famille {

	String familyName;
	HashMap<String,Personne> familyMembers;
	int size;
	
	public Famille(String familyName, int size, HashMap<String,Personne> familyMembers) {
		super();
		this.familyName = familyName;
		this.familyMembers = familyMembers;
		this.size = size;
	}
	
	
	
	public HashMap<String, Personne> getFamilyMembers() {
		return familyMembers;
	}



	public void setFamilyMembers(HashMap<String, Personne> familyMembers) {
		this.familyMembers = familyMembers;
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
