package chap11.modele;

import java.net.URI;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import chap11.infrastructure.jaxrs.HyperLien;

public class ImplemService1 implements Service1 {
	
	@Context 
	private UriInfo infoUri;
	
	public ImplemService1(){
		System.out.println("Initialisation du service 1 de type " + this.getClass());
	}
	
	public HyperLien<Service2> lien(){
		URI uri = infoUri.getBaseUriBuilder().path(Service2.class).build();
		HyperLien<Service2> k = new HyperLien<>(uri);
		return k;
	}




}
