package es.com.blogspot.elblogdepicodev.plugintapestry.services.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import es.com.blogspot.elblogdepicodev.plugintapestry.misc.Pagination;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class GenericDAOImpl<T> implements GenericDAO<T> {

	 private Class clazz;
	 private Session session;

	 public GenericDAOImpl(Class<T> clazz, Session session) {
		  this.clazz = clazz;
		  this.session = session;
	 }

	 @Override
	 public T findById(Serializable id) {
		  return (T) session.get(clazz, id);
	 }

	 @Override
	 public List<T> findAll() {
		  return findAll(null);
	 }

	 @Override
	 public List<T> findAll(Pagination paginacion) {
		  Criteria criteria = session.createCriteria(clazz);

		  if (paginacion != null) {
				List<Order> orders = paginacion.getOrders();
				for (Order order : orders) {
					 criteria.addOrder(order);					 
				}
		  }

		  if (paginacion != null) {
				criteria.setFirstResult(paginacion.getStart());
				criteria.setFetchSize(paginacion.getEnd() - paginacion.getStart() + 1);
		  }

		  return criteria.list();
	 }

	 @Override
	 public long countAll() {
		  Criteria criteria = session.createCriteria(clazz);

		  criteria.setProjection(Projections.rowCount());

		  return (long) criteria.uniqueResult();
	 }

	 @Override
	 public void persist(T object) {
		  session.persist(object);
	 }

	 @Override
	 public void remove(T object) {
		  session.delete(object);
	 }

	 @Override
	 public void removeAll() {
		  String hql = String.format("delete from %s", clazz.getName());
		  Query query = session.createQuery(hql);
		  query.executeUpdate();
	 }
}