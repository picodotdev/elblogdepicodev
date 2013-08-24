package es.com.blogspot.elblogdepicodev.pattern.state;

import java.math.BigDecimal;

import es.com.blogspot.elblogdepicodev.pattern.state.CompraStateFactory.Estado;
import es.com.blogspot.elblogdepicodev.pattern.state.compraState.CompraState;

public class Compra {

	public enum FormaPago {
		PAYPAL, TARJETA_CREDITO
	}
	
	public enum FormaEnvio {
		UPS, TNT, SEUR
	}
	
	private CompraState estado;

	private BigDecimal precio;
	private FormaPago formaPago;
	private FormaEnvio formaEnvio;
	
	public Compra() {
		setEstado(Estado.CREADA);
	}	
	
	public CompraState getEstado() {
		return estado;
	}

	public void setEstado(CompraState estado) {
		this.estado = estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = CompraStateFactory.buildState(estado, this);
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public FormaEnvio getFormaEnvio() {
		return formaEnvio;
	}

	public void setFormaEnvio(FormaEnvio formaEnvio) {
		this.formaEnvio = formaEnvio;
	}	
}