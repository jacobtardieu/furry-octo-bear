package granularitycollection;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

public class Client {
	public static void main(String args[]) {
		GererUser serv = JAXRSClientFactory.create(
				"http://localhost:8080/RestIdentifyResources/", GererUser.class);
		
		System.out.println(serv.getFollowers().getList().get(1).getNom());
		System.out.println(serv.getFriends().getList().get(0).getNom());
	}
}
