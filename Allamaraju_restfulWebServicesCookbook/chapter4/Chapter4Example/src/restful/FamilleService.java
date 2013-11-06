package restful;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import javax.ws.rs.core.Response;

import model.Famille;
import model.Personne;

public class FamilleService {
	
	HashMap<String,Famille> families = new HashMap<String,Famille>();
	
	public FamilleService() {
		super();
		HashMap<String,Personne> famille = new HashMap<String,Personne>();
		Personne morticia = new Personne("Morticia","ADAMS", 53, "Femme");
		famille.put("Morticia",morticia);
		Personne gomez = new Personne("Gomez","ADAMS", 60, "Homme");
		famille.put("Gomez",gomez);
		Personne mercredi = new Personne("Mercredi","ADAMS", 15, "Femme");
		famille.put("Mercredi",mercredi);
		Personne pugsley = new Personne("Pugsley","ADAMS", 17, "Homme");
		famille.put("Pugsley",pugsley);
		Personne fetide = new Personne("Fetide","ADAMS", 55, "Homme");
		famille.put("Fetide",fetide);
		
		families.put("ADAMS",new Famille("ADAMS",5, famille));
	}
	
	@GET
	@Path("/")
	public void testRoot (){
		System.out.println("Root Succeded");
	}

	@GET
	@Path("REST/{famillyName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFamily(@PathParam("famillyName") String familyName) {
		System.out.println("Family Service Activated");
		System.out.println("Nom de Famille : "+familyName);
		Famille toReturn = families.get(familyName);
		System.out.println("Objet : "+toReturn);
		if (toReturn == null)
			return Response.noContent().build();
		else
			return Response.ok(toReturn).build();
	}
	
	@GET
	@Path("REST/{famillyName}/{personName}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPersonne(@PathParam("famillyName") String famillyName, @PathParam("personName") String personName){
		System.out.println("Person Service Activated");
		Personne toReturn = families.get(famillyName).getFamilyMembers().get(personName);
		if (toReturn == null)
			return Response.noContent().build();
		else
			return Response.ok(toReturn).build();
	}
	
	@GET
	@Path("/familly_service")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFamily2 (@QueryParam("famillyName") String familyName) {
		return getFamily(familyName);
	}
}
