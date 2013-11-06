package restful;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Link {
	String href;
	String rel;
	String title;
	String hreflang;
	String type;
	int length;
	public Link(String href, String rel, String title, String hreflang,
			String type, int length) {
		super();
		this.href = href;
		this.rel = rel;
		this.title = title;
		this.hreflang = hreflang;
		this.type = type;
		this.length = length;
	}
	public Link() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHreflang() {
		return hreflang;
	}
	public void setHreflang(String hreflang) {
		this.hreflang = hreflang;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((href == null) ? 0 : href.hashCode());
		result = prime * result
				+ ((hreflang == null) ? 0 : hreflang.hashCode());
		result = prime * result + length;
		result = prime * result + ((rel == null) ? 0 : rel.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (href == null) {
			if (other.href != null)
				return false;
		} else if (!href.equals(other.href))
			return false;
		if (hreflang == null) {
			if (other.hreflang != null)
				return false;
		} else if (!hreflang.equals(other.hreflang))
			return false;
		if (length != other.length)
			return false;
		if (rel == null) {
			if (other.rel != null)
				return false;
		} else if (!rel.equals(other.rel))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "XMLLink [href=" + href + ", rel=" + rel + ", title=" + title
				+ ", hreflang=" + hreflang + ", type=" + type + ", length="
				+ length + "]";
	}
	
	
}
