package es.com.blogspot.elblogdepicodev.plugintapestry.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.com.blogspot.elblogdepicodev.plugintapestry.services.dao.ProductoDAO;

/**
 * @tapestrydoc
 */
public class NumeroProductos {

	@Inject
	ProductoDAO dao;

	@BeginRender
	boolean beginRender(MarkupWriter writer) {
		long numero = dao.countAll();
		writer.write(String.format("Hay %d productos", numero));
		return false;
	}
}
