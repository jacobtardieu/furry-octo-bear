package chap11.modele;


import java.util.LinkedList;
import java.util.List;

import chap11.infrastructure.jaxrs.HyperLien;
import chap11.infrastructure.jaxrs.HyperLiens;

public class ImplemCatalogue implements Catalogue {
	
	public static Bibliotheque bib;
	
	public ImplemCatalogue() {
		System.out.println("Initialisation du catalogue de type " + this.getClass());
	}
	
	
	public HyperLiens<Livre> lister() {
		List<Livre> livres = bib.getLivres();
		List<HyperLien<Livre>> resultat = new LinkedList<>();
		for(Livre l : livres){
			resultat.add(new HyperLien<Livre>(l.getUri()));
		}
		return new HyperLiens<Livre>(resultat);
	}

}
