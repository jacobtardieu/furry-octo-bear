package chap10.infrastructure;

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

import chap10.infrastructure.annotations.VersionCompteur;

@Provider
@VersionCompteur
@BindingPriority(BindingPriority.HEADER_DECORATOR)
// Non conforme à JAX-RS 2
public class RealiserTransactionOptimiste implements ContainerRequestFilter,
		ContainerResponseFilter {

	private int version;

	public RealiserTransactionOptimiste() {
		version = 1;
		System.out
				.println("Intercepteur initialisé de type " + this.getClass());
	}

	@Override
	public void filter(ContainerRequestContext requete,
			ContainerResponseContext reponse) throws IOException {
		if (requete.getMethod().equalsIgnoreCase("GET")) {
			MultivaluedMap<String, Object> enTetes = reponse.getHeaders();
			enTetes.putSingle(HttpHeaders.ETAG, version);
		}
	}

	@Override
	public void filter(ContainerRequestContext requete) throws IOException {
		if (requete.getMethod().equalsIgnoreCase("PUT")) {
			MultivaluedMap<String, String> enTetes = requete.getHeaders();
			String versionClient = enTetes.getFirst(HttpHeaders.IF_MATCH);
			if (versionClient == null) {
				requete.abortWith(Response.noContent()
						.status(Response.Status.FORBIDDEN).build());
				System.out.println("*** Requête non autorisée ***");
				return;
			}
			String versionServeur = String.valueOf(version);
			if (!versionClient.equals(versionServeur)) {
				Response.StatusType statut = Response.Status.PRECONDITION_FAILED;
				requete.abortWith(Response.noContent().status(statut).build());
				System.out.println("*** Transaction à reprendre ("
						+ Response.Status.PRECONDITION_FAILED.getStatusCode()
						+ " - version client : " + versionClient
						+ " - version serveur : " + versionServeur + ") ***");
				return;
			}
			this.version++;
		}

	}

}
