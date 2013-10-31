package chap10.modele;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ressource {
	private int i;

	public Ressource(){
		this(0);
	}
	
	public Ressource(int j) {
		this.i = j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
}
