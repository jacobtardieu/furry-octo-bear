package test;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.writer.Writer;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.provider.atom.AtomFeedProvider;



public class Atom {
	
	
	@XmlRootElement(name = "Book", namespace="www.example.com")
	@XmlType(name="Book",namespace="www.example.com",
			propOrder={"name","description","published","addedToSystem","author"})
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Book{
		public String name;
		public String author="";
		public String description="";
		public Date   published=new Date();
		public Date   addedToSystem=new Date();
		public Book(){
			
		}
		public Book(String name,String author,String description){
			this.name=name;
			this.author=author;
			this.description=description;
		}
		
		public void setFeedEntry(Entry e){
			e.addAuthor(this.author);
			e.setTitle(this.name);
			e.setContent(this.description);
			e.setPublished(this.published);
			e.setEdited(this.addedToSystem);
		}
	}
	
	
	public Atom(){
		Book testBook=new Book("Test Book","John Mc. Jones","not a very interesting book");
		testBook.description="Not a very interesting book";
		testBook.published  =new Date(1970,12,1);
		database.put("Test Book", testBook);
		Book secondBook=new Book("Another Book","Anonymous","More interesting");
		secondBook.published=new Date(1989,10,5);
		secondBook.addedToSystem=new Date(2011,1,1);
		database.put("Another Book", secondBook);
	}
	
	//database to store our books 
	Map<String,Book> database= new HashMap<String,Book>();
	
	
	
	Abdera a = new Abdera();
	@GET
	@Path("feed")
	@Produces("application/atom+xml;type=feed")
	public Feed manualFeedCreation(){
		Feed f=a.newFeed();
		f.setLanguage("English");
		for(Book b:database.values()){
			Entry e=f.addEntry();
			b.setFeedEntry(e);
		}
		
		return f;
		
	}
	
	
	//this feed is just a list of elements, non-conformant to atom schema file
	@GET 
	@Path("simpleFeed")
	@Produces("application/atom+xml;type=feed")
	public Collection<Book> anotherAttempt(){
		return database.values();
	}
	@GET
	@Path("get/{name}")
	@org.apache.abdera.ext.serializer.annotation.Entry
	@Produces("application/atom+xml;type=entry")
	public Feed getBook(@PathParam("name") String name){
		Feed f=a.newFeed();
		Entry e=f.addEntry();
		database.get(name).setFeedEntry(e);
		return f;
	}
	
	public static void main(String[] arg){
		
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		AtomFeedProvider a=new AtomFeedProvider();
		a.setFormattedOutput(true);
		sf.setAddress("http://localhost:9000/");
		sf.create();
	}
}
