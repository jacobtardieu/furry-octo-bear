package howToStoreQueries;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Hashtable;

import org.simpleframework.http.Path;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.Server;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class ServerSimulation implements Container {
	private Hashtable<String,String> map=new Hashtable<String,String>();
   public void handle(Request request, Response response) {
      try {
         PrintStream body = response.getPrintStream();
         long time = System.currentTimeMillis();
         //Query q =request.getQuery();
         Path path = request.getPath(); 
         String name = path.getName();
         String[] s=path.getSegments();
        
         if(s.length>=1){ 
        	 for(int i=0; i<s.length;i++){
        		 System.out.println(s[i]);
        	 }
        	 switch(s[0]){
	         case "create": 
	        	 if(s.length==1){
		        	 int i=map.size();
		        	 map.put("http://localhost:8080/created/"+i, "Page créée suite à la soumission d'un formulaire POST. Cette page est désormais accessible via GET est peut-être mise en cache. </br> L'uri correspondant est: "+"http://localhost:8080/created/"+i+" . </br> <form action='/create' method='post'><input type='hidden' name= 'test' value='test'><input type='submit' value='Request via POST'></form>");
		        	 System.out.println(map.toString());
		        	 response.setValue("Content-Type", "text/html");
			         response.setValue("Server", "HelloWorld/1.0 (Simple 4.0)");
			         response.setValue("Location", map.get(i));
			         response.setCode(201);
			         response.setDescription("created");
			         //response.setContentLength(0);
			         response.setDate("Date", time);
			         response.setDate("Last-Modified", time);
			         body.println("Une page a été créée suite à la soumission de votre formulaire POST: "+"http://localhost:8080/created/"+i+"<form action='create' method='post'><input type='hidden' name= 'test' value='test'><input type='submit' value='Request via POST'></form>");
			         body.close();
	        	 }
	        	 else{
	        		 response.setCode(404);
	        		 response.setDescription("not found");
	        	 }
		         break;
	         case "created":
	        	 response.setValue("Content-Type", "text/html");
		         response.setValue("Server", "HelloWorld/1.0 (Simple 4.0)");
		         response.setDate("Date", time);
		         response.setDate("Last-Modified", time);
		         System.out.println(s.toString());
		         if(s.length==2){
		        	 body.println(map.get("http://localhost:8080/created/"+s[1]));
			         body.close();
		         }
		         else{
		        	 response.setCode(404);
	        		 response.setDescription("not found");
		         }
	        	 break;
	        /* default:
	        	 response.setValue("Content-Type", "text/html");
		         response.setValue("Server", "HelloWorld/1.0 (Simple 4.0)");
		         response.setDate("Date", time);
		         response.setDate("Last-Modified", time);
		         
		         body.println("<form action='create' method='post'><input type='hidden' name= 'test' value='test'><input type='submit' value='Request via POST'></form>");
		         body.close();
	        	 break;*/
	         }
         }else{
        	 System.out.println("no specific page requested");
        	 response.setValue("Content-Type", "text/html");
	         response.setValue("Server", "HelloWorld/1.0 (Simple 4.0)");
	         response.setDate("Date", time);
	         response.setDate("Last-Modified", time);
	         
	         body.println("<form action='create' method='post'><input type='hidden' name= 'test' value='test'><input type='submit' value='Request via POST'></form>");
	         body.close();
         }
         System.out.println(response.toString());
      } catch(Exception e) {
         e.printStackTrace();
      }
   } 

   public static void main(String[] list) throws Exception {
      Container container = new ServerSimulation();
      Server server = new ContainerServer(container);
      Connection connection = new SocketConnection(server);
      SocketAddress address = new InetSocketAddress(8080);

      connection.connect(address);
   }
}