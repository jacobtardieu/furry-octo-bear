package granularitycollection;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/{id}")
public interface GererUser {
	
	@GET  
    @Path("/friends")  
    List<User> getFriends(@PathParam("id") long id);
	
	@GET  
    @Path("/followers")  
    List<User> getFollowers(@PathParam("id") long id);
	
	@GET  
    @Path("/friends_followers")  
    List<User> getFFs(@PathParam("id") long id);

}
