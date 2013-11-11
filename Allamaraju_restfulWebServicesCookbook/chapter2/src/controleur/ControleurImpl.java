package controleur;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import tool.Enveloppe;

public class ControleurImpl implements Controleur{
	private static List<Friend> friends;
	
	public ControleurImpl(){
		if (null == friends){
			friends = new ArrayList<Friend>();
		}
	}
	@Override
	public Response fusionner(Enveloppe<Friend> friends) {
		return Response.ok().build();
	}
}
