package granularitycollection;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import tool.Enveloppe;

public class GererUserImpl implements GererUser{
	private static List<User> friends;
	private static List<User> followers;
	
	public GererUserImpl(){
		if (null == friends){
			friends = new ArrayList<User>();
			User u = new User();
			u.setId(0);
			u.setNom("toto");
			friends.add(u);
		}
		if (null == followers){
			followers = new ArrayList<User>();
			User u = new User();
			u.setId(1);
			u.setNom("tata");
			followers.add(u);
		}
	}

	@Override
	public Enveloppe<User> getFriends() {
		return new Enveloppe<User>(friends);
	}

	@Override
	public Enveloppe<User> getFollowers() {
		return new Enveloppe<User>(followers);
	}

	@Override
	public Enveloppe<User> getFFs() {
		List<User> users = friends.subList(0, friends.size()-1);
		users.addAll(followers);
		return new Enveloppe<User>(users);
	}

}
