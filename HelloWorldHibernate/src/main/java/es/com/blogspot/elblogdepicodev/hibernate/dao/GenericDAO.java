package es.com.blogspot.elblogdepicodev.hibernate.dao;

import java.util.List;

public interface GenericDAO<T> {

	 T findById(Long id);

	 List<T> findAll();

	 List<T> findAll(Pagination paginacion);

	 long countAll();

	 void persist(T producto);

	 void remove(T producto);

	 void removeAll();
}