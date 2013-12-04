package chap11.modele;

import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "livre")
public class ImplemLivre implements Livre {
	private String titre;
	private URI uri;
	
	public ImplemLivre() {
		this("", null);
	}

	public ImplemLivre(String titre, URI uri) {
		this.titre = titre;
		this.uri = uri;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public Livre getRepresentation() {
		return this;
	}
}
