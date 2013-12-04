package chap10.client;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import chap10.infrastructure.jaxrs.AjouterVersionEnTete;
import chap10.modele.Ressource;
import chap10.modele.ServiceRegistre;

public class Chap10 {

	public static void main(String[] args) {

		List<Object> filtres = new LinkedList<>();
		filtres.add(new AjouterVersionEnTete());
		final ServiceRegistre proxyRegistre = JAXRSClientFactory.create(
				"http://localhost:8080/RestfulWebServicesCookBook",
				ServiceRegistre.class, filtres);
		for (int i = 0; i < 100; i++) {
			lancerTransaction(proxyRegistre);
		}

		System.exit(0);

	}

	private static String convertirFlotEnMot(Object flot) {
		String mot = "";
		if(flot instanceof java.io.InputStream){
			java.io.InputStream flotReel = (java.io.InputStream)flot;
			try {
				byte[] lettres = new byte[flotReel.available()];
				flotReel.read(lettres);
				mot = new String(lettres);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return mot;
	}
	
	private static void lancerTransaction(ServiceRegistre proxyRegistre) {
		try {
			System.out.println("*** 1. Get ***");

			Ressource s = proxyRegistre.get();
			System.out.println("*** Résultat : " + s.getI());

			System.out.println("*** 2. Set ***");

			s.setI(s.getI() + 1);
			proxyRegistre.set(s);

			System.out.println("*** 3. Get ***");

			s = proxyRegistre.get();
			System.out.println("*** Résultat : " + s.getI());

		} catch (WebApplicationException e) { // (ClientErrorException e) {
			Response r = e.getResponse();
			Object contenu = r.getEntity();
			String msg = convertirFlotEnMot(contenu);
			int statut = r.getStatus();
			if (statut == Response.Status.PRECONDITION_FAILED.getStatusCode()) {
				System.out.println("Erreur : " + msg);
				System.out.println("Reprise de transaction.");
				lancerTransaction(proxyRegistre);
			}
		}

	}
}
