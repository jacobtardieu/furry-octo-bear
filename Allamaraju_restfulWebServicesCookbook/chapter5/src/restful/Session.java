package restful;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Session {
	private Etat execution;
	private int id;
	private boolean valide;

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean estValide) {
		this.valide = estValide;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Session))
			return false;
		Session s = (Session) o;
		return (this.getId() == s.getId());
	}

	public int hashCode() {
		return this.getId();
	}

	public String toString() {
		return this.getId() + "-" + this.isValide();
	}

	public Etat getExecution() {
		return execution;
	}

	public void setExecution(Etat execution) {
		this.execution = execution;
	}
}
