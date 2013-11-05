package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Personne")
public class Personne {
	String firstName;
	String familyName;
	int age;
	String gender;
	String link;
	
	public Personne(){
		firstName = "";
		age = 0;
		gender = "";
		link = "";
		familyName ="";
	}
	
	public Personne(String firstName, String familyName, int age, String gender) {
		super();
		this.firstName = firstName;
		this.age = age;
		this.gender = gender;
		this.familyName = familyName;
		this.link = "http://localhost:8080/"+familyName+"/"+firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	} 
	
	
}
