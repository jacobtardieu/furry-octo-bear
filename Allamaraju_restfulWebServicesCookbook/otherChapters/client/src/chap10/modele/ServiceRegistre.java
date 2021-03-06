package chap10.modele;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import chap10.infrastructure.jaxrs.annotations.VersionCompteur;



@Path("registre")
public interface ServiceRegistre {
	@VersionCompteur
	@PUT
	@Path("set")
	public void set(Ressource n);
	@VersionCompteur
	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_XML)
	public Ressource get();
}


/*
 * Variantes pour set
 * public Response set(int n, @HeaderParam(HttpHeaders.IF_MATCH) String version);
 * public Response set(int n, @Context Request request);
 * ... request.evaluatePreconditions(entityTag) ...
*/