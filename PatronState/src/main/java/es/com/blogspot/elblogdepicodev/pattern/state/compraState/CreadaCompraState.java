package es.com.blogspot.elblogdepicodev.pattern.state.compraState;

import java.math.BigDecimal;

import es.com.blogspot.elblogdepicodev.pattern.state.Compra;
import es.com.blogspot.elblogdepicodev.pattern.state.Compra.FormaEnvio;
import es.com.blogspot.elblogdepicodev.pattern.state.Compra.FormaPago;
import es.com.blogspot.elblogdepicodev.pattern.state.CompraStateFactory.Estado;

public class CreadaCompraState extends AbstractCompraState {

	public CreadaCompraState(Compra compra) {
		super(compra);
	}
	
	@Override
	public void comprar(BigDecimal precio, FormaPago formaPago, FormaEnvio formaEnvio) {
		getCompra().setPrecio(precio);
		getCompra().setFormaPago(formaPago);
		getCompra().setFormaEnvio(formaEnvio);
		
		getCompra().setEstado(Estado.EN_ESPERA);
	}

	@Override
	public void cancelar() {
		getCompra().setEstado(Estado.CANCELADA);
	}
}