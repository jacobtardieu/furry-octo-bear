package chap10.modele;


public class Compteur implements ServiceCompteur {

	private Ressource n;
	
	public Compteur() {
		this(new Ressource(0));
	}

	public Compteur(Ressource i) {
		System.out.println("Initialisation du compteur de type " + this.getClass());
		n = new Ressource(i.getI());
	}

	@Override
	public synchronized void set(Ressource n) {
		this.n = new Ressource(n.getI());
	}

	@Override
	public synchronized Ressource get() {
		return n;
	}

}



