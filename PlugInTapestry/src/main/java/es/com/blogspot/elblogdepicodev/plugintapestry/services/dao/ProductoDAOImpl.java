package es.com.blogspot.elblogdepicodev.plugintapestry.services.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import es.com.blogspot.elblogdepicodev.plugintapestry.entities.Producto;

public class ProductoDAOImpl extends GenericDAOImpl<Producto> implements ProductoDAO {

	@Autowired
	public ProductoDAOImpl(SessionFactory sessionFactory) {
		super(Producto.class, sessionFactory);
	}
}