package es.com.blogspot.elblogdepicodev.activiti.misc;

import java.io.Serializable;

public class Producto implements Serializable {

	private static final long serialVersionUID = -2340188855153751355L;

	private String nombre;
	private Long existencias;

	public Producto() {
	}

	public Producto(String nombre, Long existencias) {
		this.nombre = nombre;
		this.existencias = existencias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getExistencias() {
		return existencias;
	}

	public void setExistencias(Long existencias) {
		this.existencias = existencias;
	}

	public boolean hayExistencias() {
		return existencias > 0;
	}
}