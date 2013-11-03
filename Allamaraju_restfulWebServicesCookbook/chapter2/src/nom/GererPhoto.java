package nom;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/photos")
public interface GererPhoto {
	@GET  
    @Path("/{id}")  
    Photo getPhoto(@PathParam("id") long id);
	
	@PUT
    @Path("/{id}")  
	Response updatePhoto(@PathParam("id") long id, Photo photo);
	
	@POST
    @Path("/")  
	Response addPhoto(Photo photo);
	
	@DELETE
    @Path("/{id}")  
	Response deletePhoto(@PathParam("id") long id);
}
