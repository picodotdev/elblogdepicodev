package es.com.blogspot.elblogdepicodev.tapestry.jpa.misc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;

@SuppressWarnings("rawtypes")
public abstract class JPAGridDataSource<T> implements GridDataSource {

	private EntityManager entityManager;
	private Class type;

	private int start;
	private List<T> results;

	public JPAGridDataSource(EntityManager entityManager, Class type) {
		this.entityManager = entityManager;
		this.type = type;
	}

	@Override
	public int getAvailableRows() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(type)));

		return entityManager.createQuery(cq).getSingleResult().intValue();
	}

	@Override
	public void prepare(int start, int end, List<SortConstraint> sort) {
		Pagination pagination = new Pagination(start, end, Sort.fromSortConstraint(sort));
		
		this.start = start;
		
		results = find(pagination);
	}
	
	public abstract List<T> find(Pagination pagination);

	@Override
	public Object getRowValue(int i) {
		return results.get(i - this.start);
	}

	@Override
	public Class getRowType() {
		return type;
	}
}