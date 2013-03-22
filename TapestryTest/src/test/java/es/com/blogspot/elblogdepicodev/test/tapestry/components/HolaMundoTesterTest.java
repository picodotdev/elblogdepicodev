package es.com.blogspot.elblogdepicodev.test.tapestry.components;

import org.apache.tapestry5.dom.Document;
import org.junit.Assert;
import org.junit.Test;

import com.formos.tapestry.testify.core.ForComponents;

import es.com.blogspot.elblogdepicodev.test.tapestry.test.AbstractTest;

public class HolaMundoTesterTest extends AbstractTest {
	
	@ForComponents("nombre")
	private String nombre;

	@Test
	public void sinNombre() {
		Document doc = tester.renderPage("test/HolaMundoTest");		
		Assert.assertEquals("Hola mundo Tapestry!!!", doc.getElementById("componenteSinNombre").getChildMarkup());
	}
	
	@Test
	public void conNombre() {
		nombre = "picodotdev";

		Document doc = tester.renderPage("test/HolaMundoTest");
		Assert.assertEquals("Hola mundo picodotdev!!!", doc.getElementById("componenteConNombre").getChildMarkup());
	}
}