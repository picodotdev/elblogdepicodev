package es.com.blogspot.elblogdepicodev.plugintapestry.components;

import org.apache.tapestry5.dom.Document;
import org.jaxen.JaxenException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.formos.tapestry.testify.core.ForComponents;
import com.formos.tapestry.xpath.TapestryXPath;

import es.com.blogspot.elblogdepicodev.plugintapestry.services.dao.ProductoDAO;
import es.com.blogspot.elblogdepicodev.plugintapestry.test.AbstractTest;

public class NumeroProductosXPathTesterTest extends AbstractTest {

	// @ForComponents("nombre")
	// private String nombre;

	@ForComponents
	private ProductoDAO dao;

	@Before
	public void before() {
		dao = Mockito.mock(ProductoDAO.class);
		Mockito.when(dao.countAll()).thenReturn(0l);
	}

	@Test
	public void ceroProductos() throws JaxenException {
		Document doc = tester.renderPage("test/NumeroProductosTest");
		String text = TapestryXPath.xpath("id('componente')").selectSingleElement(doc).getChildMarkup();
		Assert.assertEquals("Hay 0 productos", text);
	}
}