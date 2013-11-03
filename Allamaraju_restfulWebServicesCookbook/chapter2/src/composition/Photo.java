package composition;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Photo")
public class Photo {
	private long id;
	private String name;
	public Photo() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
