package chap11.modele;




import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import chap11.infrastructure.jaxrs.HyperLiens;

@Path("catalogue")
public interface Catalogue {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public HyperLiens<Livre> lister();

	
}
