package miam;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class LancementCafet {
	
	public static void main(String[] args) {
		
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(miam.CafetEMN.class);
		sf.setResourceProvider(miam.CafetEMN.class,
				new SingletonResourceProvider(new miam.CafetEMN()));
		sf.setAddress("http://localhost:8080/restexamples");
		sf.create();
		
	}

}
