package es.com.blogspot.elblogdepicodev.resteasy;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.com.blogspot.elblogdepicodev.tapestry.resteasy.services.ContadorService;

public class HelloWorldResourceImpl implements HelloWorldResource {

	private ContadorService contadorService;

	public HelloWorldResourceImpl(ContadorService contadorService) {
		this.contadorService = contadorService;
	}

	@Override
	public String getSaluda() {
		contadorService.incrementar();
		return "¡Hola mundo!";
	}

	@Override
	public String getSaludaA(String nombre) {
		contadorService.incrementar();
		return MessageFormat.format("¡Hola {0}!", nombre);
	}

	@Override
	public Mensaje getMensajeJSON(String nombre) {
		contadorService.incrementar();
		return buildMensaje(nombre);
	}

	@Override
	public Mensaje getMensajeXML(String nombre) {
		contadorService.incrementar();
		return buildMensaje(nombre);
	}

	private Mensaje buildMensaje(String nombre) {
		return new Mensaje(nombre, "¡Hola mundo!", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}