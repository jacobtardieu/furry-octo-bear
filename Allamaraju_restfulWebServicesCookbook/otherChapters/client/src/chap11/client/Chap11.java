package chap11.client;

import java.net.URI;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import chap11.infrastructure.jaxrs.ClientRessource;
import chap11.infrastructure.jaxrs.HyperLien;
import chap11.modele.Catalogue;
import chap11.modele.Livre;
import chap11.modele.Service1;
import chap11.modele.Service2;

public class Chap11 {

	public static String URI_SERVEUR = "http://localhost:8080/RestfulWebServicesCookBook";
	public static void main(String[] args) {

		Service1 s1 = JAXRSClientFactory.
				create(
				URI_SERVEUR,
				Service1.class);

		System.out.println("*** 1. Service 1 : récupération d'un hyperlien vers Service 2 ***");
		
		HyperLien<Service2> k = s1.lien();

		URI uri = k.getUri();
		
		System.out.println("uri de Service 2 : " + uri);

		System.out.println("*** 2. Service 2 : Appel de f ***");

		Service2 s2 = (new ClientRessource<Service2>(k, Service2.class)).getProxyRessource();
				
		System.out.println(" f : " + s2.f());
		
		System.out.println("*** 3. Exemple des livres ***");
		
		Catalogue catalogue = JAXRSClientFactory.
				create(
				URI_SERVEUR,
				Catalogue.class);
		
		List<HyperLien<Livre>> hl = catalogue.lister().getLiens();
		
		for(HyperLien<Livre> r : hl){
			System.out.println("uri livre: " + r.getUri());
			Livre proxyLivre = new ClientRessource<Livre>(r, Livre.class).getProxyRessource();
			System.out.println("livre (appel distant) : " + proxyLivre.getTitre()+ " @ " + proxyLivre.getUri());
			Livre copieLivre = proxyLivre.getRepresentation();
			System.out.println("livre (copie locale): " + copieLivre.getTitre() + " @ " + copieLivre.getUri());
		}
		
		
		System.exit(0);

		

	}

}
