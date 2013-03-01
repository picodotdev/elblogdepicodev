package es.com.blogspot.elblogdepicodev.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloworld")
public interface HelloWorldResource {

	@GET
	@Path("/saluda")
	public String getSaluda();
	
	@GET
	@Path("/saluda/{nombre}")
	public String getSaludaA(@PathParam("nombre") String nombre);
	
	@GET
	@Path("/mensaje/{nombre}")
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje getMensajeJSON(@PathParam("nombre") String nombre);
	
	@GET
	@Path("/mensaje/{nombre}")
	@Produces(MediaType.APPLICATION_XML)
	public Mensaje getMensajeXML(@PathParam("nombre") String nombre);
}