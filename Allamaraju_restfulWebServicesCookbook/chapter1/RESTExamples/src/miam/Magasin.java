package miam;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

public interface Magasin {
	
	@GET
	@Path("magasin/sandwiches")
	@Produces("application/json")
	Sandwich getSandwiches();
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("magasin/sandwiche")
	Sandwich update(Sandwich s);
	


}
