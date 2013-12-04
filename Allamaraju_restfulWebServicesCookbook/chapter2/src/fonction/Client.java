package fonction;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

public class Client {
	public static void main(String args[]) {
		CalcuDistance serv = JAXRSClientFactory.create(
				"http://localhost:8080/RestIdentifyResources/", CalcuDistance.class);
		
		Distance d = new Distance();
		System.out.println(serv.distance_calc(d));
	}
}
