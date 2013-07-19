package es.com.blogspot.elblogdepicodev.tapestry.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import es.com.blogspot.elblogdepicodev.tapestry.jpa.misc.Pagination;

public class GenericDAOImpl<T> implements GenericDAO<T> {

	 private Class<T> clazz;
	 private EntityManager entityManager;

	 public GenericDAOImpl(Class<T> clazz, EntityManager entityManager) {
		  this.clazz = clazz;
		  this.entityManager = entityManager;
	 }

	 @Override
	 public T findById(Long id) {
		  return entityManager.find(clazz, id);
	 }

	 @Override
	 public List<T> findAll() {
		  return findAll(null);
	 }

	 @Override
	 public List<T> findAll(Pagination paginacion) {
		  CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		  CriteriaQuery<T> cq = cb.createQuery(clazz);
		  Root<T> root = cq.from(clazz);

		  cq.select(root);
		  if (paginacion != null) {
				cq.orderBy(paginacion.getOrders(root, cb));
		  }

		  Query q = entityManager.createQuery(cq);
		  if (paginacion != null) {
				q.setFirstResult(paginacion.getStart());
				q.setMaxResults(paginacion.getEnd() - paginacion.getStart() + 1);
		  }

		  return q.getResultList();
	 }

	 @Override
	 public long countAll() {
		  CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		  CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		  cq.select(cb.count(cq.from(clazz)));

		  return entityManager.createQuery(cq).getSingleResult().intValue();
	 }

	 @Override
	 public void persist(T object) {
		  entityManager.persist(object);
	 }

	 @Override
	 public void remove(T object) {
		  entityManager.remove(object);
	 }

	 @Override
	 public void removeAll() {
		  String jpql = String.format("delete from %s", clazz.getName());
		  Query query = entityManager.createQuery(jpql);
		  query.executeUpdate();
	 }
}
