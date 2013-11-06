package restful;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public interface Automate {
	@GET
	@Path("automate/initier")
	@Produces(MediaType.APPLICATION_XML)
    Session initier();
	@GET
	@Path("automate/accepter")
	@Produces(MediaType.APPLICATION_XML)
    Session accepter(@QueryParam("lettre") char x, @QueryParam("") Session id);
	@GET
	@Path("automate/displayxmllink")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	Link displayxmllink();
	@GET
	@Path("automate/displayjsonlink")
	@Produces(MediaType.APPLICATION_JSON)
	Link displayjsonlink();
	@GET
	@Path("automate/displayauth")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	Link displayauth();
}
