package controleur;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import tool.Enveloppe;

@Path("/annuaire")
public interface Controleur {
	@POST
	@Path("/fusionner")
	Response fusionner(Enveloppe<Friend> friends);
}
