package es.com.blogspot.elblogdepicodev.hibernate.dao;

import java.util.List;

public interface GenericSearchDAO<T> {

	 void indexAll() throws InterruptedException;

	 List<T> search(String q, String... campos);
}