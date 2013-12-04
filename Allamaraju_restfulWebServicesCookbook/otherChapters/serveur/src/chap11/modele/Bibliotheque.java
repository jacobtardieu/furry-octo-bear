package chap11.modele;

import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("bibliotheque")
public interface Bibliotheque {
	
	List<Livre> getLivres();
	
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	Livre getLivre(@PathParam("id") String id);
	
}
