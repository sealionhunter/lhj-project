package lhj.learn.restservice;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;

@Provider
@PreMatching
public class ExceptionTestFilter
		implements ContainerRequestFilter, ContainerResponseFilter, ApplicationEventListener, RequestEventListener {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		System.out.println("after request");
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("before request");
	}

	@Override
	public void onEvent(RequestEvent event) {
		if (event.getType() == RequestEvent.Type.ON_EXCEPTION) {
			System.out.println("Error request");
		}
	}

	@Override
	public void onEvent(ApplicationEvent event) {

	}

	@Override
	public RequestEventListener onRequest(RequestEvent requestEvent) {
		// TODO Auto-generated method stub
		return this;
	}

}
