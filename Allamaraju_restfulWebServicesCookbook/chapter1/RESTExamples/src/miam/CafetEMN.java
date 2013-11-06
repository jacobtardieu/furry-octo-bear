package miam;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

public class CafetEMN implements Magasin {

	Sandwich s1 = new Sandwich(1, "Jambon beurre", 2, "Très très bon, avec du jambon et du beurre. Et aussi du pain autour");
	Sandwich s2 = new Sandwich(2, "Jambon fromage", 1000, "Jambon + fromage mais pas de beurre");
	
	List<Sandwich> l = new ArrayList<Sandwich>();

	@Override
	public List<Sandwich> getSandwiches() {
		l.add(s1);
		l.add(s2);
		return l;
	}
	
	@Override
	public Sandwich getSandwich(int id) {
		if (id == 1) return s1;
		if (id == 2) return s2;
		return null;
	}
	
	@Override
	public Sandwich update(Sandwich s) {
		return s;
	}
	
	@Override
	public Response create(Sandwich s) {
		try {
			return Response.created(new URI("http://localhost:8080/restexamples/magasin/sandwiches/"+s.getId())).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response delete(int id) {
		if (id != 1 && id != 2) {
			return Response.status(Response.Status.NOT_FOUND).entity("The sandwich doesn't exist: "+id).build();
		}
		else {
			return Response.ok().build();
		}
	}

}
