package es.com.blogspot.elblogdepicodev.hibernate.dao;

import java.util.List;

import es.com.blogspot.elblogdepicodev.hibernate.Producto;

public interface GenericSearchDAO<T> {

	 void indexAll() throws InterruptedException;

	 List<Producto> search(String q, String... campos);
}