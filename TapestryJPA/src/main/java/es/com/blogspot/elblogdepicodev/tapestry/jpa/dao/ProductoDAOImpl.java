package es.com.blogspot.elblogdepicodev.tapestry.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import es.com.blogspot.elblogdepicodev.tapestry.jpa.entities.Producto;
import es.com.blogspot.elblogdepicodev.tapestry.jpa.misc.Pagination;

public class ProductoDAOImpl implements ProductoDAO {

	private EntityManager entityManager;
	
	public ProductoDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Producto findById(Long id) {
		return entityManager.find(Producto.class, id);
	}
	
	@Override
	public List<Producto> findAll(Pagination paginacion) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
		Root<Producto> root = cq.from(Producto.class);

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
	public void persist(Producto producto) {
		entityManager.persist(producto);
	}
	
	@Override
	public void remove(Producto producto) {
		entityManager.remove(producto);
	}

	@Override
	public void removeAll() {
		Query query = entityManager.createQuery("delete from Producto");
		query.executeUpdate();
	}
}
