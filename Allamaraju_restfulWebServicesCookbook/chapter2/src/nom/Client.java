package nom;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

public class Client {
	public static void main(String args[]) {
		GererPhoto serv = JAXRSClientFactory.create(
				"http://localhost:8080/RestIdentifyResources/", GererPhoto.class);
		Photo p = new Photo();
		p.setId(1);
		p.setName("toto");
		serv.addPhoto(p);
		System.out.println(serv.getPhoto(1).getName());
		
	}
}
