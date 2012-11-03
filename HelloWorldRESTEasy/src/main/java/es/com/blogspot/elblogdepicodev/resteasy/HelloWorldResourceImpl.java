package es.com.blogspot.elblogdepicodev.resteasy;

import java.text.MessageFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/helloworld")
public class HelloWorldResourceImpl {

	@GET
	@Path("/saluda")
	public String getSaluda() {
		return "¡Hola mundo!";
	}
	
	@GET
	@Path("/saluda/{nombre}")
	public String getSaludaA(@PathParam("nombre") String nombre) {
		return MessageFormat.format("¡Hola {0}!", nombre);
	}
}