package fonction;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public interface CalcuDistance {
	@GET
	@Path("/distance_calc")
	float distance_calc(@PathParam("") Distance d);
}
