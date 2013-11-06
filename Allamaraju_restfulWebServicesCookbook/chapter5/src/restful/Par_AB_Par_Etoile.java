package restful;

public class Par_AB_Par_Etoile implements Automate {

	private int compteur;
	private int numeroSession;

	public Par_AB_Par_Etoile() {
		numeroSession = 0;
		compteur = 0;
	}

	public Session initier() {
		Session res = new Session();
		res.setValide(true);
		res.setId(numeroSession);
		numeroSession++;
		res.setExecution(Etat.UN);
		return res;
	}

	public Session accepter(char x, Session id) {
		compteur++;
		System.out.println("************** requête accepter numéro " + compteur + " *************");
		if (!id.isValide())
			return id;
		Etat e = id.getExecution();
		if (e.equals(Etat.UN) && (x == 'a')) {
			id.setExecution(Etat.DEUX);
			return id;
		}
		if (e.equals(Etat.DEUX) && (x == 'b')) {
			id.setExecution(Etat.UN);
			return id;
		}
		id.setValide(false);
		return id;
	}
	
	public Link displayxmllink() {
		Link link = new Link();
		link.setHref("http://burymewithmymoney.com/");
		link.setRel("alternate");
		link.setTitle("Bury me with my moneeeeeeyyy !");
		link.setType("url");
		link.setLength(12);
		return link;
	}
	
	public Link displayjsonlink() {
		Link link = new Link();
		link.setHref("http://republiquedesmangues.fr/");
		link.setRel("http://www.example.org/rels/sitebidon");
		link.setTitle("Live free with your fellow mangos !");
		link.setType("url");
		link.setLength(12);
		return link;
	}
	
	public Link displayauth() {
		Link link = new Link();
		link.setHref("http://bananashop.fr/purchase-req/auth");
		link.setRel("http://bananashop.fr/auth/ASBV_05_11_2013_1/22_02_?k=a1191fd35d23");
		link.setTitle("Buy exquisite bananas !");
		link.setType("url");
		link.setLength(12);
		return link;
	}
}
