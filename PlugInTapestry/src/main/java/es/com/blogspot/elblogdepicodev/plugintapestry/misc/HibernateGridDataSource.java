package es.com.blogspot.elblogdepicodev.plugintapestry.misc;

import java.util.List;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

@SuppressWarnings({ "rawtypes" })
public abstract class HibernateGridDataSource implements GridDataSource {

	private Session session;
	private Class clazz;

	private int start;
	private List results;

	public HibernateGridDataSource(Session session, Class clazz) {
		this.session = session;
		this.clazz = clazz;
	}

	@Override
	public int getAvailableRows() {
		Criteria criteria = session.createCriteria(clazz);

		criteria.setProjection(Projections.rowCount());

		return ((Number) criteria.uniqueResult()).intValue();
	}

	@Override
	public void prepare(int start, int end, List<SortConstraint> sort) {
		Pagination pagination = new Pagination(start, end, Sort.fromSortConstraint(sort));

		this.start = start;

		results = find(pagination);
	}

	public abstract List find(Pagination pagination);

	@Override
	public Object getRowValue(int i) {
		return results.get(i - this.start);
	}

	@Override
	public Class getRowType() {
		return clazz;
	}
}