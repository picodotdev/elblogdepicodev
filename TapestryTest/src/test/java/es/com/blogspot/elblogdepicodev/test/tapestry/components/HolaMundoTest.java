package es.com.blogspot.elblogdepicodev.test.tapestry.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.internal.services.MarkupWriterImpl;
import org.junit.Assert;
import org.junit.Test;

import es.com.blogspot.elblogdepicodev.test.tapestry.components.HolaMundo;

public class HolaMundoTest {

	@Test
	public void conNombre() {		
		HolaMundo componente = new HolaMundo();
		componente.nombre = "picodotdev";

		MarkupWriter writer = new MarkupWriterImpl();
		componente.beginRender(writer);

		Assert.assertEquals("Hola mundo picodotdev!!!", writer.toString());
	}
}