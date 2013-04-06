package es.com.blogspot.elblogdepicodev.test.tapestry.components;

import org.apache.tapestry5.dom.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.formos.tapestry.testify.core.ForComponents;

import es.com.blogspot.elblogdepicodev.test.tapestry.services.MensajeService;
import es.com.blogspot.elblogdepicodev.test.tapestry.test.AbstractTest;

public class HolaMundoTesterTest extends AbstractTest {
	
	@ForComponents("nombre")
	private String nombre;
	
	@ForComponents
	private MensajeService mensajeService;

	@Before
	public void before() {
		// Crear el mock del servicio
		mensajeService = Mockito.mock(MensajeService.class);
		Mockito.when(mensajeService.getMensaje()).thenReturn("Hola mundo {0}!!!");
	}
	
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