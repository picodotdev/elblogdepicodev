package es.com.blogspot.elblogdepicodev.plugintapestry.services.dao;

import java.io.Serializable;
import java.util.List;

import es.com.blogspot.elblogdepicodev.plugintapestry.misc.Pagination;

public interface GenericDAO<T> {

	T findById(Serializable id);

	List<T> findAll();

	List<T> findAll(Pagination paginacion);

	long countAll();

	void persist(T entity);

	void remove(T entity);

	void removeAll();
}