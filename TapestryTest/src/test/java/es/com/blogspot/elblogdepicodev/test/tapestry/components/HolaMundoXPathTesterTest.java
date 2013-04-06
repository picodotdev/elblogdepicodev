package es.com.blogspot.elblogdepicodev.test.tapestry.components;

import org.apache.tapestry5.dom.Document;
import org.jaxen.JaxenException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.formos.tapestry.testify.core.ForComponents;
import com.formos.tapestry.xpath.TapestryXPath;

import es.com.blogspot.elblogdepicodev.test.tapestry.services.MensajeService;
import es.com.blogspot.elblogdepicodev.test.tapestry.test.AbstractTest;

public class HolaMundoXPathTesterTest extends AbstractTest {
	
	@ForComponents("nombre")
	private String nombre;

	@ForComponents
	private MensajeService mensajeService;

	@Before
	public void before() {
		mensajeService = Mockito.mock(MensajeService.class);
		Mockito.when(mensajeService.getMensaje()).thenReturn("Hola mundo {0}!!!");
	}
	
	@Test
	public void sinNombre() throws JaxenException {		
		Document doc = tester.renderPage("test/HolaMundoTest");
		String text = TapestryXPath.xpath("id('componenteSinNombre')").selectSingleElement(doc).getChildMarkup();
		Assert.assertEquals("Hola mundo Tapestry!!!", text);
	}
	
	@Test
	public void conNombre() throws JaxenException {
		nombre = "picodotdev";

		Document doc = tester.renderPage("test/HolaMundoTest");
		String text = TapestryXPath.xpath("id('componenteConNombre')").selectSingleElement(doc).getChildMarkup();
		Assert.assertEquals("Hola mundo picodotdev!!!", text);
	}
}