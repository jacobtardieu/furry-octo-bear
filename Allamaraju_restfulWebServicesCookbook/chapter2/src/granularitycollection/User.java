package granularitycollection;

import java.util.ArrayList;
import java.util.List;

public class User {
	private long id;
	private String nom;
	private List<User> friends;
	private List<User> followers;
	
	public User() {
		super();
		friends = new ArrayList<User>();
		followers = new ArrayList<User>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<User> getFriends() {
		return friends;
	}

	public List<User> getFollowers() {
		return followers;
	}
	
}
