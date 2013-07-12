package es.com.blogspot.elblogdepicodev.tapestry.jpa.pages.admin;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.BeanModelSource;

import es.com.blogspot.elblogdepicodev.tapestry.jpa.dao.ProductoDAO;
import es.com.blogspot.elblogdepicodev.tapestry.jpa.entities.Producto;

public class EjemploJPA {

	@Inject
	@Symbol(SymbolConstants.TAPESTRY_VERSION)
	@Property
	private String tapestryVersion;
	
	@Inject
	private ProductoDAO dao;
	
	@Inject
	private BeanModelSource beanModelSource;
	
	@Inject
	private ComponentResources resources;
	
	@Property
	private Producto producto;

	public BeanModel<Producto> getModel() {
		BeanModel<Producto> model = beanModelSource.createDisplayModel(Producto.class, resources.getMessages());
		// Columnas sin ordenación
		for (String name :model.getPropertyNames()) {
			model.get(name).sortable(false);
		}
		// Ocultar columna id
		model.exclude("id");
		// Añadir columna de acción
		model.add("action", null).label("").sortable(false);
		return model;
	}
	
	void onNuevo() throws Exception {
		Producto producto = new Producto(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1l, new Date());
		dao.persist(producto);
		throw new Exception();
	}
	
	void onEliminar(Long id) {		
		Producto producto = dao.findById(id);
		dao.remove(producto);
	}

	@Cached
	public List<Producto> getProductos() {
		return dao.findAll(null);
	}
}