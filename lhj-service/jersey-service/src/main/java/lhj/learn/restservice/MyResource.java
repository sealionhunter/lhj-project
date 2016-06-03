package lhj.learn.restservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	@Path("json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JsonBean getJson() {
		JsonBean jsonBean = new JsonBean();
		jsonBean.setVal1("val1");
		jsonBean.setVal2("val2");
		if (true) {
			throw new IllegalStateException("Exception");
		}
		return jsonBean;
	}
}
