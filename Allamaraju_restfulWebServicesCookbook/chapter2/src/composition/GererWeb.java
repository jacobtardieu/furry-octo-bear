package composition;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/webs")
public interface GererWeb {
	@GET
	@Path("/{id}")
	Web getWeb(@PathParam("id") long id);
}
