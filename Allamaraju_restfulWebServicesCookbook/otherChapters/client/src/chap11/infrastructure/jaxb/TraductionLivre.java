package chap11.infrastructure.jaxb;

import chap11.modele.ImplemLivre;
import chap11.modele.Livre;



public class TraductionLivre extends javax.xml.bind.annotation.adapters.XmlAdapter<ImplemLivre, Livre>{

	/*
	 * inutile car ne sert pas à la traduction objet -> document
	 * qui utilise le type dynamique, nécessairement une classe d'implémentation.
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public ImplemLivre marshal(Livre l) throws Exception {
		return null;
	}

	@Override
	public Livre unmarshal(ImplemLivre l) throws Exception {
		return l;
	}

}
