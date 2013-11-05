package howToDesignUriAndResponses;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class QueryDesignAndResponse {
	
	public static List<String> executePost(String targetURL, String urlParameters)
	  {
	    URL url;
	    HttpURLConnection connection = null;  
	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Content-Type", 
	           "application/x-www-form-urlencoded");
				
	      connection.setRequestProperty("Content-Length", "" + 
	               Integer.toString(urlParameters.getBytes().length));
	      connection.setRequestProperty("Content-Language", "en-US");  
				
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      wr.writeBytes (urlParameters);
	      wr.flush ();
	      wr.close ();
	      
	      
	      //Get Response	
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      rd.close();
	      ArrayList<String> l = new ArrayList<String>();
	      l.add(getHeader(connection));
	      l.add(response.toString());
	      return l;

	    } catch (Exception e) {

	      e.printStackTrace();
	      return null;

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	  }
	
	public static String getHeader(URLConnection conn){
		try {
			Map<String, List<String>> map = conn.getHeaderFields();
		 
			String s="";
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				s+="Key : " + entry.getKey() 
		                           + " ,Value : " + entry.getValue()+"\n";
			}
		 
			System.out.println("\nGet Response Header By Key ...\n");
			String server = conn.getHeaderField("Server");
		 
			if (server == null) {
				System.out.println("Key 'Server' is not found!");
				return null;
			} else {
				return s+="Server - " + server;
			}
		 
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		return null;
		 
	}
	
	public static void ecrire(String adressedufichier, String texte){
		//on met try si jamais il y a une exception
		try
		{
			/**
			 * BufferedWriter a besoin d un FileWriter, 
			 * les 2 vont ensemble, on donne comme argument le nom du fichier
			 * true signifie qu on ajoute dans le fichier (append), on ne marque pas par dessus 
			 
			 */
			FileWriter fw = new FileWriter(adressedufichier, true);
			
			// le BufferedWriter output auquel on donne comme argument le FileWriter fw cree juste au dessus
			BufferedWriter output = new BufferedWriter(fw);
			
			//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
			output.write(texte);
			//on peut utiliser plusieurs fois methode write
			
			output.flush();
			//ensuite flush envoie dans le fichier, ne pas oublier cette methode pour le BufferedWriter
			
			output.close();
			//et on le ferme
			System.out.println("fichier créé");
		}
		catch(IOException ioe){
			System.out.print("Erreur : ");
			ioe.printStackTrace();
			}

	}

	public static void main(String[] args){
	/*String urlParameters =
	        "fName=" + URLEncoder.encode("???", "UTF-8") +
	        "&lName=" + URLEncoder.encode("???", "UTF-8");*/
	String adressedufichier = "responses" + "/" + "responseExample1.html";
	List l=executePost("http://www.leboncoin.fr/consoles_jeux_video/offres/pays_de_la_loire", "q=playstation%204");
	ecrire(adressedufichier,(String) l.get(1));
	adressedufichier = "responses" + "/" + "headerExample1.txt";
	ecrire(adressedufichier,(String) l.get(0));
	
	adressedufichier = "responses" + "/" + "responseExample2.html";
	l=executePost("http://www.leboncoin.fr/consoles_jeux_video/offres/pays_de_la_loire", "q=playstation%204&sp=1");
	ecrire(adressedufichier,(String) l.get(1));
	adressedufichier = "responses" + "/" + "headerExample2.txt";
	ecrire(adressedufichier,(String) l.get(0));
	
	adressedufichier = "responses" + "/" + "responseExample3.html";
	l=executePost("http://www.leboncoin.fr/consoles_jeux_video/offres/pays_de_la_loire", "q=playstation%204&sp=1&th=0");
	ecrire(adressedufichier,(String) l.get(1));
	adressedufichier = "responses" + "/" + "headerExample3.txt";
	ecrire(adressedufichier,(String) l.get(0));
	}
}
