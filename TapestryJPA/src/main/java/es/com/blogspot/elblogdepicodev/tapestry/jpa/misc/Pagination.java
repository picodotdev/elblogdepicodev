package es.com.blogspot.elblogdepicodev.tapestry.jpa.misc;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public class Pagination {
	private int start;
	private int end;
	private List<Sort> sort;

	public Pagination(int start, int end, List<Sort> sort) {
		this.start = start;
		this.end = end;
		this.sort = sort;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public List<Sort> getSort() {
		return sort;
	}

	public void setSort(List<Sort> sort) {
		this.sort = sort;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Order> getOrders(Root root, CriteriaBuilder cb) {
		List<Order> orders = new ArrayList<Order>();
		for (Sort s : sort) {
			Order o = s.getOrder(root, cb);
			if (o != null) {
				orders.add(o);
			}
		}
		return orders;
	}
}