package chap10.infrastructure.jaxrs;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.BindingPriority;

import chap10.infrastructure.jaxrs.annotations.CacheServeur;

@Provider
@CacheServeur
@BindingPriority(BindingPriority.HEADER_DECORATOR - 10)
public class Cacher implements ContainerRequestFilter, ContainerResponseFilter {

	private Response cache;

	public Cacher() {
		cache = null;
		System.out
				.println("Initialisation du filtre de type " + this.getClass());
	}

	@Override
	public void filter(ContainerRequestContext requete,
			ContainerResponseContext reponse) throws IOException {
		if (requete.getMethod().equalsIgnoreCase("GET")) {
			cache = Response.ok(reponse.getEntity())
					.replaceAll(reponse.getHeaders()).build();
		}
	}

	@Override
	public void filter(ContainerRequestContext requete) throws IOException {
		if (requete.getMethod().equalsIgnoreCase("PUT")) {
			cache = null;
			return;
		}
		if (requete.getMethod().equalsIgnoreCase("GET")) {
			if (cache == null)
				return;
			MultivaluedMap<String, String> enTetes = requete.getHeaders();
			String versionClient = enTetes.getFirst(HttpHeaders.IF_NONE_MATCH);
			if (versionClient == null) {
				return;
			}
			String versionCache = cache.getEntityTag().getValue();
			if (versionClient.equals(String.valueOf(versionCache))) {
				requete.abortWith(Response.fromResponse(cache).build());
				System.out.println("*** Réponse en cache ***");
				return;
			}
			return;
		}
	}

}
