package chap10.client;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import chap10.infrastructure.AjouterVersionEnTete;
import chap10.modele.Ressource;
import chap10.modele.ServiceCompteur;

public class Chap10 {

	public static void main(String[] args) {

		List<Object> filtres = new LinkedList<>();
		filtres.add(new AjouterVersionEnTete());
		final ServiceCompteur proxyCompteur = JAXRSClientFactory.create(
				"http://localhost:8080/ResfulWebServicesCookBook",
				ServiceCompteur.class, filtres);
		for (int i = 0; i < 100; i++) {
			lancerTransaction(proxyCompteur);
		}

		System.exit(0);

	}

	private static void lancerTransaction(ServiceCompteur proxyCompteur) {
		try {
			System.out.println("*** 1. Get ***");

			Ressource s = proxyCompteur.get();
			System.out.println("*** Résultat : " + s.getI());

			System.out.println("*** 2. Set ***");

			s.setI(s.getI() + 1);
			proxyCompteur.set(s);

			System.out.println("*** 3. Get ***");

			s = proxyCompteur.get();
			System.out.println("*** Résultat : " + s.getI());

		} catch (WebApplicationException e) { // (ClientErrorException e) {
			Response r = e.getResponse();
			int statut = r.getStatus();
			if (statut == Response.Status.PRECONDITION_FAILED.getStatusCode()) {
				System.out.println("Reprise de transaction.");
				lancerTransaction(proxyCompteur);
			}
		}

	}
}
