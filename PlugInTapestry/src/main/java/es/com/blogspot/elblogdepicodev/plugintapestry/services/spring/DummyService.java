package es.com.blogspot.elblogdepicodev.plugintapestry.services.spring;

import es.com.blogspot.elblogdepicodev.plugintapestry.entities.Producto;

public class DummyService {

	public void process(String action, Object entity) {
		if (entity instanceof Producto) {
			Producto p = (Producto) entity;
			System.out.println(String.format("Action: %s, Id: %d", action, p.getId()));
		}
	}
}