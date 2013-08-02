package es.com.blogspot.elblogdepicodev.hibernate;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.com.blogspot.elblogdepicodev.hibernate.dao.GenericDAO;
import es.com.blogspot.elblogdepicodev.hibernate.dao.GenericDAOImpl;
import es.com.blogspot.elblogdepicodev.hibernate.dao.GenericSearchDAO;
import es.com.blogspot.elblogdepicodev.hibernate.dao.GenericSearchDAOImpl;

public class HibernateTest {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	private static GenericDAO dao;
	private static GenericSearchDAO sdao;
	
	@BeforeClass
	public static void beforeClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("h2");
		entityManager = entityManagerFactory.createEntityManager();
		
		dao = new GenericDAOImpl(Producto.class, entityManager);
		sdao = new GenericSearchDAOImpl(Producto.class, entityManager);
	}
	
	@AfterClass
	public static void afterClass() {
		entityManager.close();
		entityManagerFactory.close();		
	}
	
	@Before
	public void before() {
		entityManager.getTransaction().begin();
		
		dao.persist(new Producto(
				"Tapestry 5 - Rapid web application development in Java",
				"Rapid web application development in Java is a comprehensive guide, introducing Apache Tapestry and its innovative approach to building modern web applications. The book walks you through Tapestry 5, from a simple Hello World application to rich Ajax-enabled applications. Written by a core committer this book provides deep insight into the architecture of Tapestry 5. It not only shows you how to achieve specific goals but also teaches you the \"why\". You learn how to build modern, scalable Web 2.0 application with a component-oriented approach. This book also shows how Tapestry brings scripting language productivity within reach of Java developers without sacrificing any of Java's inherent speed and power.",
				10l, new Date()));
		
		entityManager.getTransaction().commit();
	}
	
	@After
	public void after() throws Exception {
		entityManager.getTransaction().begin();
		dao.removeAll();
		sdao.indexAll();
		entityManager.getTransaction().commit();
	}
	
	@Test
	public void findAll() {
		Assert.assertEquals(1, dao.findAll().size());
	}
	
	@Test
	public void search() throws InterruptedException {
		Assert.assertEquals(1, sdao.search("Tapestry", "nombre", "descripcion").size());
	}
}
