package es.com.blogspot.elblogdepicodev.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

public class GenericSearchDAOImpl<T> implements GenericSearchDAO<T> {

	 private Class<T> clazz;
	 private FullTextEntityManager fullTextEntityManager;

	 public GenericSearchDAOImpl(Class<T> clazz, EntityManager entityManager) {
		  this.clazz = clazz;
		  this.fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
	 }

	 @Override
	 public void indexAll() throws InterruptedException {
		  fullTextEntityManager.createIndexer().startAndWait();
	 }

	 @Override
	 public List<T> search(String q, String... campos) {
		  QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(clazz).get();
		  Query query = qb.keyword().onFields(campos).matching(q).createQuery();
		  javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, clazz);
		  return persistenceQuery.getResultList();
	 }
}