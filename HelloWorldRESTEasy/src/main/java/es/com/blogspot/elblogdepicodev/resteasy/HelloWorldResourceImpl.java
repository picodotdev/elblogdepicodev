package es.com.blogspot.elblogdepicodev.resteasy;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/helloworld")
public class HelloWorldResourceImpl implements HelloWorldResource {

	@Override
	public String getSaluda() {
		return "¡Hola mundo!";
	}
	
	@Override
	public String getSaludaA(@PathParam("nombre") String nombre) {
		return MessageFormat.format("¡Hola {0}!", nombre);
	}
	
	@Override
	public Mensaje getMensajeJSON(@PathParam("nombre") String nombre) {
		return buildMensaje(nombre);
	}
	
	@Override
	public Mensaje getMensajeXML(@PathParam("nombre") String nombre) {
		return buildMensaje(nombre);
	}
	
	private Mensaje buildMensaje(String nombre) {
		return new Mensaje(nombre, "¡Hola mundo!", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));		
	}
}