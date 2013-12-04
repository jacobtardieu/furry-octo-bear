package chap11.modele;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import chap11.infrastructure.jaxb.TraductionLivre;
import chap11.infrastructure.jaxb.TraductionURI;

public interface Livre {
	
	@GET
	@Path("titre")
	public String getTitre();
	
	@GET
	@Path("uri")
	@Produces(MediaType.APPLICATION_XML)
	@XmlJavaTypeAdapter(TraductionURI.class)
	public URI getUri();

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_XML)
	@XmlJavaTypeAdapter(TraductionLivre.class)
	public Livre getRepresentation();
}
