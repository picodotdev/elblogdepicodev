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

	 private static Producto producto;

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
	 public void before() throws Exception {
		  entityManager.getTransaction().begin();

		  producto = new Producto(
					 "Tapestry 5 - Rapid web application development in Java",
					 "Rapid web application development in Java is a comprehensive guide, introducing Apache Tapestry and its innovative approach to building modern web applications. The book walks you through Tapestry 5, from a simple Hello World application to rich Ajax-enabled applications. Written by a core committer this book provides deep insight into the architecture of Tapestry 5. It not only shows you how to achieve specific goals but also teaches you the \"why\". You learn how to build modern, scalable Web 2.0 application with a component-oriented approach. This book also shows how Tapestry brings scripting language productivity within reach of Java developers without sacrificing any of Java's inherent speed and power.",
					 10l, new Date());

		  dao.persist(producto);

		  entityManager.getTransaction().commit();
	 }

	 @After
	 public void after() throws Exception {
		  entityManager.getTransaction().begin();
		  dao.removeAll();
		  entityManager.getTransaction().commit();
	 }

	 @Test
	 public void findAll() {
		  Assert.assertEquals(1, dao.findAll().size());
	 }

	 @Test
	 public void countAll() {
		  Assert.assertEquals(1, dao.countAll());
	 }

	 @Test
	 public void remove() {
		  entityManager.getTransaction().begin();
		  dao.remove(producto);
		  entityManager.getTransaction().commit();

		  Assert.assertEquals(0, dao.countAll());
	 }

	 @Test
	 public void removeAll() {
		  entityManager.getTransaction().begin();
		  dao.removeAll();
		  entityManager.getTransaction().commit();

		  Assert.assertEquals(0, dao.countAll());
	 }

	 @Test
	 public void search() throws InterruptedException {
		  sdao.indexAll();

		  Assert.assertEquals(1, sdao.search("Tapestry", "nombre", "descripcion").size());
	 }
}
