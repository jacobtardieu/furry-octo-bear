package chap11.infrastructure.jaxb;

import java.net.URI;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

public class TraductionURI extends javax.xml.bind.annotation.adapters.XmlAdapter<TraductionURI.RepresentationURI, URI>{

		@Override
	public URI unmarshal(TraductionURI.RepresentationURI v) throws Exception {
		return new URI(v.getUri());
	}

		@Override
		public TraductionURI.RepresentationURI marshal(URI v) throws Exception {
			// TODO Auto-generated method stub
			return new TraductionURI.RepresentationURI(v);
		}
		
		@XmlRootElement(name="uri")
		static class RepresentationURI {
			private String uri;

			public RepresentationURI(){}
			
			public RepresentationURI(URI uri){
				this.uri = uri.toString();
			}
			
			@XmlAttribute(name="texte")
			public String getUri() {
				return uri;
			}

			public void setUri(String uri) {
				this.uri = uri;
			}
			
		}
		
}
