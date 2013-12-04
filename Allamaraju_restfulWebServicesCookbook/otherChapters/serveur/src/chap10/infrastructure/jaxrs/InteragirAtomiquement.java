package chap10.infrastructure.jaxrs;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.BindingPriority;

import chap10.infrastructure.jaxrs.annotations.AtomiciteRequeteReponseServeur;

@Provider
@AtomiciteRequeteReponseServeur
@BindingPriority(BindingPriority.AUTHORIZATION)
public class InteragirAtomiquement implements ContainerRequestFilter,
		ContainerResponseFilter {

	private Semaphore verrou;

	public InteragirAtomiquement() {
		verrou = new Semaphore(1, true);
		System.out
				.println("Initialisation du filtre de type " + this.getClass());
	}

	@Override
	public void filter(ContainerRequestContext requete,
			ContainerResponseContext reponse) throws IOException {
		verrou.release();
	}

	@Override
	public void filter(ContainerRequestContext requete) throws IOException {
		verrou.acquireUninterruptibly();
	}

}
