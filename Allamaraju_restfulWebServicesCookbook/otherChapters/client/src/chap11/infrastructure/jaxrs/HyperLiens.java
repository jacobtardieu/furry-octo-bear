package chap11.infrastructure.jaxrs;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "liste")
public class HyperLiens<T> {

	private List<HyperLien<T>> liens;

	public HyperLiens() {
	}

	public HyperLiens(List<HyperLien<T>> l) {
		this.liens = l;
	}

	@XmlElement(name="hyperlien")
	public List<HyperLien<T>> getLiens() {
		return liens;
	}

	public void setLiens(List<HyperLien<T>> l) {
		this.liens = l;
	}

}