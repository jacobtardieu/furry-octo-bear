package composition;

import java.util.ArrayList;
import java.util.List;

public class GererWebImpl implements GererWeb{

	@Override
	public Web getWeb(long id) {
		Web web = new Web();
		List<Article> arts = new ArrayList<Article>();
		Article a = new Article();
		a.setId(0);
		a.setTexte("Bravo");
		arts.add(a);
		
		List<Photo> photos = new ArrayList<Photo>();
		Photo p = new Photo();
		p.setId(1);
		p.setName("toto");
		photos.add(p);
		
		web.setId(id);
		web.setArts(arts);
		web.setPhotos(photos);
		return web;
	}

}
