package miam;

import java.util.ArrayList;
import java.util.List;

public class CafetEMN implements Magasin {

	@Override
	public Sandwich getSandwiches() {
		Sandwich s1 = new Sandwich(1, "Jambon beurre", 2, "Très très bon, avec du jambon et du beurre. Et aussi du pain autour");
		Sandwich s2 = new Sandwich(2, "Jambon fromage", 1000, "Jambon + fromage mais pas de beurre");
		
		List<Sandwich> l = new ArrayList<Sandwich>();
		l.add(s1);
		l.add(s2);
		return s1;
	}
	
	@Override
	public Sandwich update(Sandwich s) {
		return s;
	}

}
