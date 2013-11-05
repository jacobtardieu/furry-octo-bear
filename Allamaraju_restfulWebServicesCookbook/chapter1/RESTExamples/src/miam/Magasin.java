package miam;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public interface Magasin {
	
	@GET
	@Path("magasin/sandwiches")
	@Produces("application/json")
	List<Sandwich> getSandwiches();
	
	@GET
	@Path("magasin/sandwiches/{id}")
	@Produces("application/json")
	Sandwich getSandwich(@PathParam("id") int id);
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	@Path("magasin/sandwiches/update")
	Sandwich update(Sandwich s);
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("magasin/sandwiches/create")
	Response create(Sandwich s);
	
	@DELETE
	@Produces("application/json")
	@Path("magasin/sandwiches/delete/{id}")
	Response delete(@PathParam("id") int id);
	


}
