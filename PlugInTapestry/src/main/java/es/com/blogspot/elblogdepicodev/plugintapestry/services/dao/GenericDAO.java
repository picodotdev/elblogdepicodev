package es.com.blogspot.elblogdepicodev.plugintapestry.services.dao;

import java.io.Serializable;
import java.util.List;

import es.com.blogspot.elblogdepicodev.plugintapestry.misc.Pagination;
import es.com.blogspot.elblogdepicodev.plugintapestry.services.transaction.Propagation;
import es.com.blogspot.elblogdepicodev.plugintapestry.services.transaction.Transactional;

public interface GenericDAO<T> {

	 @Transactional(propagation = Propagation.SUPPORTS, readonly = true)
	 T findById(Serializable id);

	 @Transactional(propagation = Propagation.SUPPORTS, readonly = true)
	 List<T> findAll();

	 @Transactional(propagation = Propagation.SUPPORTS, readonly = true)
	 List<T> findAll(Pagination paginacion);

	 @Transactional(propagation = Propagation.SUPPORTS, readonly = true)
	 long countAll();

	 @Transactional(propagation = Propagation.REQUIRED)
	 void persist(T entity);

	 @Transactional(propagation = Propagation.REQUIRED)
	 void remove(T entity);

	 @Transactional(propagation = Propagation.REQUIRED)
	 void removeAll();
}