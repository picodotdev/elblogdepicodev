package es.com.blogspot.elblogdepicodev.plugintapestry.services.dao;

import java.io.Serializable;
import java.util.List;

import es.com.blogspot.elblogdepicodev.plugintapestry.misc.Pagination;
import es.com.blogspot.elblogdepicodev.plugintapestry.services.transaction.Propagation;
import es.com.blogspot.elblogdepicodev.plugintapestry.services.transaction.Transactional;

public interface GenericDAO<T> {

	 T findById(Serializable id);

	 List<T> findAll();

	 List<T> findAll(Pagination paginacion);

	 long countAll();

	 @Transactional(propagation = Propagation.REQUIRED)
	 void persist(T entity);

	 @Transactional(propagation = Propagation.REQUIRED)
	 void remove(T entity);

	 @Transactional(propagation = Propagation.REQUIRED)
	 void removeAll();
}