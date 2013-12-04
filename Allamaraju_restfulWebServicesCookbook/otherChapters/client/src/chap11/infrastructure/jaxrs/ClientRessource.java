package chap11.infrastructure.jaxrs;


import org.apache.cxf.jaxrs.client.JAXRSClientFactory;





public class ClientRessource<T> {

	private T proxyRessource;
		
	public ClientRessource(HyperLien<T> k, Class<T> typeInterface){
		proxyRessource = JAXRSClientFactory.create(
				k.getUri(), typeInterface);
	}

	
	
	public T getProxyRessource(){
		return proxyRessource;
	}
}
