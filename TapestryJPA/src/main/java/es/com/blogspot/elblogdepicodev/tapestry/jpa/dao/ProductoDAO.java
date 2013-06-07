package es.com.blogspot.elblogdepicodev.tapestry.jpa.dao;

import java.util.List;

import org.apache.tapestry5.jpa.annotations.CommitAfter;

import es.com.blogspot.elblogdepicodev.tapestry.jpa.entities.Producto;
import es.com.blogspot.elblogdepicodev.tapestry.jpa.misc.Pagination;

public interface ProductoDAO {

	Producto findById(Long id);
	List<Producto> findAll(Pagination paginacion);

	@CommitAfter
	void persist(Producto producto);
	@CommitAfter
	void remove(Producto producto);
	@CommitAfter
	void removeAll();
}