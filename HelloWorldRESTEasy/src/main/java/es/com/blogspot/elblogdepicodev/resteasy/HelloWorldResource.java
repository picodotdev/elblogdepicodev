package es.com.blogspot.elblogdepicodev.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/helloworld")
public interface HelloWorldResource {

	@GET
	@Path("/saluda")
	public String getSaluda();
	
	@GET
	@Path("/saluda/{nombre}")
	public String getSaludaA(@PathParam("nombre") String nombre);
}