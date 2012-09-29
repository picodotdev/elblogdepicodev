package es.com.blogspot.elblogdepicodev.activiti.misc;

import java.io.Serializable;

public class Cliente implements Serializable {
	
	private static final long serialVersionUID = -1041162141646175002L;

	Tipo tipo;
	
	public Cliente() {
		this(Tipo.NORMAL);
	}
	
	public Cliente(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public enum Tipo {
		VIP, NORMAL
	}
}