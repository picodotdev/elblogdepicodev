package es.com.blogspot.elblogdepicodev.test.tapestry.components;

import java.text.MessageFormat;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;

public class HolaMundo {

	@Parameter(value = "literal:Tapestry")
	String nombre;

	@BeginRender
    boolean beginRender(MarkupWriter writer) {
		String text = MessageFormat.format("Hola mundo {0}!!!", nombre);
	    writer.write(text);
		return false;
	}
}
