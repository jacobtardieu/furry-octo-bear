package controleur;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import tool.Enveloppe;

public class Client {
	public static void main(String args[]) {
		Controleur serv = JAXRSClientFactory.create(
				"http://localhost:8080/RestIdentifyResources/", Controleur.class);
		
		List<Friend> friends = new ArrayList<Friend>();
		
		serv.fusionner(new Enveloppe<Friend>(friends));
	}
}
