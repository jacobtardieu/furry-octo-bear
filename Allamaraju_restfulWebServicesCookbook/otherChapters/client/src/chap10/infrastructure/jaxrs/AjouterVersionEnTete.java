package chap10.infrastructure.jaxrs;

import java.io.IOException;

import javax.ws.rs.BindingPriority;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import chap10.infrastructure.jaxrs.annotations.VersionCompteur;

@Provider
@VersionCompteur
@BindingPriority(BindingPriority.HEADER_DECORATOR)
public class AjouterVersionEnTete implements ClientRequestFilter,
		ClientResponseFilter {

	private int version;

	public AjouterVersionEnTete() {
		version = -1;
		System.out.println("Filtre initialisé de type " + this.getClass());
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.client.ClientResponseFilter#filter(javax.ws.rs.client.
	 * ClientRequestContext, javax.ws.rs.client.ClientResponseContext)
	 * L'argument requete n'est pas entièrement initialisé : par exemple, il est
	 * impossible de récupérer la méthode HTTP.
	 */
	public synchronized void filter(ClientRequestContext requete,
			ClientResponseContext reponse) throws IOException {

		EntityTag baliseVersion = reponse.getEntityTag();
		if (baliseVersion != null) {
			version = Integer.parseInt(baliseVersion.getValue());
			return;
		}
	}

	@Override
	public synchronized void filter(ClientRequestContext requete)
			throws IOException {
		if (requete.getMethod().equalsIgnoreCase("PUT")) {
			MultivaluedMap<String, Object> enTetes = requete.getHeaders();
			enTetes.putSingle(HttpHeaders.IF_MATCH, String.valueOf(version));
		}
		if (requete.getMethod().equalsIgnoreCase("GET")) {
			MultivaluedMap<String, Object> enTetes = requete.getHeaders();
			enTetes.putSingle(HttpHeaders.IF_NONE_MATCH,
					String.valueOf(version));
		}
	}

}
