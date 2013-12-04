package chap11.modele;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class ImplemBibliotheque implements Bibliotheque {

	private List<Livre> livres;

	public ImplemBibliotheque() {
		livres = new LinkedList<>();
		for (int i = 0; i < 10; i++) {

			try {
				livres.add(new ImplemLivre("titre " + i, new URI(
						"http://localhost:8080/RestfulWebServicesCookBook/bibliotheque/"
								+ i)));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Initialisation de la bibliothèque de type "
				+ this.getClass());
		ImplemCatalogue.bib = this;
	}

	@Override
	public LinkedList<Livre> getLivres() {
		LinkedList<Livre> copie = new LinkedList<>(livres);
		return copie;
	}

	@Override
	public Livre getLivre(String id) {
		int index = Integer.parseInt(id);
		return livres.get(index);
	}

}
