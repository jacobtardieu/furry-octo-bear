package composition;

import java.util.List;

public class Web {
	private long id;
	private List<Article> arts;
	private List<Photo>	photos;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Article> getArts() {
		return arts;
	}
	public void setArts(List<Article> arts) {
		this.arts = arts;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	
}
