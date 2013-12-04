package granularitycollection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import tool.Enveloppe;

@Path("/users")
public interface GererUser {
	
	@GET  
    @Path("/friends")  
    Enveloppe<User> getFriends();
	
	@GET  
    @Path("/followers")  
	Enveloppe<User> getFollowers();
	
	@GET  
    @Path("/friends_followers")  
	Enveloppe<User> getFFs();

}
