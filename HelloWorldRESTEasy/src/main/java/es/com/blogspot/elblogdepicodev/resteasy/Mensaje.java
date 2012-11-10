package es.com.blogspot.elblogdepicodev.resteasy;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mensaje")
public class Mensaje {

	private String nombre;
	private String mensaje;
	private String fecha;

	public Mensaje() {		
	}
	
	public Mensaje(String nombre, String mensaje, String fecha) {
		this.nombre = nombre;
		this.mensaje = mensaje;
		this.fecha = fecha;
	}
	
	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@XmlAttribute
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}