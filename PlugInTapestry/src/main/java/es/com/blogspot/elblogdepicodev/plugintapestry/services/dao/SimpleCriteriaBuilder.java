package es.com.blogspot.elblogdepicodev.plugintapestry.services.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public interface SimpleCriteriaBuilder<T> {

	 void build(CriteriaBuilder cb, CriteriaQuery<T> cq, Root<T> c);
}