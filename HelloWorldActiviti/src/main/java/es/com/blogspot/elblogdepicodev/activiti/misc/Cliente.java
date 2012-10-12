package es.com.blogspot.elblogdepicodev.activiti.misc;

import java.io.Serializable;

public class Cliente implements Serializable {

	private static final long serialVersionUID = -1041162141646175002L;

	public enum Tipo {
		VIP, NORMAL
	}

	Tipo tipo;

	public Cliente() {
		this(Tipo.NORMAL);
	}

	public Cliente(Tipo tipo) {
		this.tipo = tipo;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
}