package chap11.modele;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import chap11.infrastructure.jaxrs.HyperLien;


@Path("service1")
public interface Service1 {
	@GET
	@Path("lien")
	@Produces(MediaType.APPLICATION_XML)
	public HyperLien<Service2> lien();
}
