package controleur;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/annuaire")
public interface Controleur {
	@POST
	@Path("/fusionner")
	Response fusionner(List<Friend> friends);
}
