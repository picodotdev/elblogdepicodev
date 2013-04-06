package es.com.blogspot.elblogdepicodev.test.tapestry.components;

import java.text.MessageFormat;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.com.blogspot.elblogdepicodev.test.tapestry.services.MensajeService;

public class HolaMundo {

	@Parameter(value = "literal:Tapestry")
	String nombre;
	
	@Inject
	MensajeService mensajeService;

	@BeginRender
    boolean beginRender(MarkupWriter writer) {
		String mensaje = mensajeService.getMensaje();
		String text = MessageFormat.format(mensaje, nombre);
	    writer.write(text);
		return false;
	}
}
