package lhj.learn.webservice;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {
	// Base URI the Grizzly HTTP server will listen on
	public static final String BASE_URI = "http://localhost:8089/myapp/";

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer() {
		// create a resource config that scans for JAX-RS resources and
		// providers
		// in lhj.learn.restservice package
		final ResourceConfig rc = new ResourceConfig().packages("lhj.learn.webservice").register(JacksonFeature.class)
				.register(LoggingFilter.class)
				/* .property(name, value) */;

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
//		final HttpServer server = startServer();
//		System.out.println(String.format(
//				"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...",
//				BASE_URI));
//		System.in.read();
//		server.shutdown();
	    
	    Map<String, String> a = new LinkedHashMap<String, String>();
	    for (int i = 0; i < 10; i++) {
	        a.put("" + i, "" + i);
	    }
	    
	    for (String s : a.keySet()) {
	        System.out.println(s);
	    }
	}
}
