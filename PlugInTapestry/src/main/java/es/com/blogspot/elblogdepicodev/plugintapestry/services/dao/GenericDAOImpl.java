package es.com.blogspot.elblogdepicodev.plugintapestry.services.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.com.blogspot.elblogdepicodev.plugintapestry.misc.Pagination;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class GenericDAOImpl<T> implements GenericDAO<T> {

	private Class clazz;
	protected SessionFactory sessionFactory;

	public GenericDAOImpl(Class<T> clazz, SessionFactory sessionFactory) {
		this.clazz = clazz;
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional(readOnly = true)
	public T findById(Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return findAll(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll(Pagination paginacion) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);

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
	@Transactional(readOnly = true)
	public long countAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);

		criteria.setProjection(Projections.rowCount());

		return (long) criteria.uniqueResult();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void persist(T object) {
		sessionFactory.getCurrentSession().saveOrUpdate(object);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(T object) {
		sessionFactory.getCurrentSession().delete(object);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeAll() {
		String hql = String.format("delete from %s", clazz.getName());
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
}