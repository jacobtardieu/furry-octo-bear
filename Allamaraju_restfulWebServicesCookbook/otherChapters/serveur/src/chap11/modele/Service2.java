package chap11.modele;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("service2")
public interface Service2 {
	@GET
	@Path("f")
	@Produces(MediaType.APPLICATION_XML)
	public int f();
}
