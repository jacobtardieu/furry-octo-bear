package composition;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

public class Client {
	public static void main(String args[]) {
		GererWeb serv = JAXRSClientFactory.create(
				"http://localhost:8080/RestIdentifyResources/", GererWeb.class);
		
		System.out.println(serv.getWeb(0).getArts().get(0).getTexte());
	}
}
