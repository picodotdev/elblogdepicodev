package es.com.blogspot.elblogdepicodev.plugintapestry.services.dao;

import org.hibernate.Session;

import es.com.blogspot.elblogdepicodev.plugintapestry.entities.Producto;

public class ProductoDAOImpl extends GenericDAOImpl<Producto> implements ProductoDAO {

	public ProductoDAOImpl(Session session) {
		super(Producto.class, session);
	}
}