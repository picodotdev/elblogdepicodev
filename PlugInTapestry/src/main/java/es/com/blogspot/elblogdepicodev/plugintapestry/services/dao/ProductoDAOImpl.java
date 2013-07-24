package es.com.blogspot.elblogdepicodev.plugintapestry.services.dao;

import javax.persistence.EntityManager;

import es.com.blogspot.elblogdepicodev.plugintapestry.entities.Producto;

public class ProductoDAOImpl extends GenericDAOImpl<Producto> implements ProductoDAO {

	 public ProductoDAOImpl(Class<Producto> clazz, EntityManager entityManager) {
		  super(clazz, entityManager);
	 }
}
