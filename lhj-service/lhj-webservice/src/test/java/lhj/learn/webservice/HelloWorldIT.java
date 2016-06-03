package lhj.learn.webservice;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class HelloWorldIT {
	private HttpServer server;
	private WebTarget target;

	@Before
	public void beforeClass() {
		// start the server
//		server = Main.startServer();
		ResourceConfig conf = new ResourceConfig().register(JacksonJsonProvider.class);
		Client client = ClientBuilder.newClient(conf);

		target = client.target("http://localhost:8080/lhj-webservice");
	}

//	@After
//	public void tearDown() throws Exception {
//		server.shutdown();
//	}

	@Test
	public void testPing() throws Exception {
		String responseMsg = target.path("hello/echo/SierraTangoNevada").request("text/plain").get(String.class);
		assertEquals("SierraTangoNevada", responseMsg);
	}

	@Test
	public void testJsonRoundtrip() throws Exception {
		JsonBean inputBean = new JsonBean();
		inputBean.setVal1("Maple");
		Response r = target.path("hello/jsonBean").request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(inputBean, MediaType.APPLICATION_JSON_TYPE));
		JsonBean output = r.readEntity(JsonBean.class);
		assertEquals("Maple", output.getVal2());
	}
}
