package es.com.blogspot.elblogdepicodev.hibernate;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.com.blogspot.elblogdepicodev.hibernate.dao.GenericDAO;
import es.com.blogspot.elblogdepicodev.hibernate.dao.GenericDAOImpl;
import es.com.blogspot.elblogdepicodev.hibernate.dao.GenericSearchDAO;
import es.com.blogspot.elblogdepicodev.hibernate.dao.GenericSearchDAOImpl;

public class HibernateSearch {

	 public static void main(String[] args) throws Exception {
		  // Obtener la factoría de sesiones
		  EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("h2");
		  EntityManager entityManager = entityManagerFactory.createEntityManager();

		  try {
				GenericDAO dao = new GenericDAOImpl(Producto.class, entityManager);
				GenericSearchDAO sdao = new GenericSearchDAOImpl(Producto.class, entityManager);

				entityManager.getTransaction().begin();
				dao.persist(new Producto(
						  "Tapestry 5",
						  "Rapid web application development in Java is a comprehensive guide, introducing Apache Tapestry and its innovative approach to building modern web applications. The book walks you through Tapestry 5, from a simple Hello World application to rich Ajax-enabled applications. Written by a core committer this book provides deep insight into the architecture of Tapestry 5. It not only shows you how to achieve specific goals but also teaches you the \"why\". You learn how to build modern, scalable Web 2.0 application with a component-oriented approach. This book also shows how Tapestry brings scripting language productivity within reach of Java developers without sacrificing any of Java's inherent speed and power.",
						  10l, new Date()));
				dao.persist(new Producto(
						  "Raspberry Pi",
						  "The Raspberry Pi is a credit-card sized computer that plugs into your TV and a keyboard. It’s a capable little PC which can be used for many of the things that your desktop PC does, like spreadsheets, word-processing and games. It also plays high-definition video. We want to see it being used by kids all over the world to learn programming.",
						  20l, new Date()));
				dao.persist(new Producto(
						  "Raspberry Pi User Guide",
						  "The essential guide to getting started with Raspberry Pi computing and programming. Originally conceived of as a fun, easy way for kids (and curious adults) to learn computer programming, the Raspberry Pi quickly evolved into a remarkably robust, credit-card-size computer that can be used for everything from playing HD videos and hacking around with hardware to learning to program! Co-authored by one of the creators of the Raspberry Pi, this book fills you in on everything you need to know to get up and running on your Raspberry Pi, in no time.",
						  30l, new Date()));
				entityManager.getTransaction().commit();

				// Realizar la indexación inicial
				sdao.indexAll();

				entityManager.getTransaction().begin();

				String[] terminos = new String[] { "Tapestry", "Pi", "guide" };
				for (String termino : terminos) {
					 // Realizar la búsqueda de texto completo
					 List<Producto> productos = sdao.search(termino, "nombre", "descripcion");
					 print(termino, productos);
				}

				entityManager.getTransaction().commit();
		  } catch (Exception e) {
				e.printStackTrace();
				entityManager.getTransaction().rollback();
		  }

		  entityManager.close();
		  entityManagerFactory.close();
	 }

	 private static void print(String termino, List<Producto> productos) {
		  System.out.printf("Resultados para «%1$s»\n", termino);
		  if (productos.isEmpty()) {
				System.out.println("No se han encontrado resultados\n");
		  } else {
				System.out.printf("  # %1$-20s %2$8s %3$s\n", "Nombre", "Cantidad", "Fecha");
				int i = 1;
				for (Producto producto : productos) {
					 System.out.printf("%1$3s %2$-20s %3$8s %4$tR\n", i, producto.getNombre(), producto.getCantidad(), producto.getFecha());
					 ++i;
				}
		  }
	 }
}
