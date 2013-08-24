package es.com.blogspot.elblogdepicodev.pattern.state.compraState;

import java.math.BigDecimal;
import java.text.MessageFormat;

import es.com.blogspot.elblogdepicodev.pattern.state.Compra;
import es.com.blogspot.elblogdepicodev.pattern.state.Compra.FormaEnvio;
import es.com.blogspot.elblogdepicodev.pattern.state.Compra.FormaPago;

public abstract class AbstractCompraState implements CompraState {

	private Compra compra;
	
	public AbstractCompraState(Compra compra) {
		this.compra = compra;
	}
	
	public Compra getCompra() {
		return compra;
	}

	@Override
	public void comprar(BigDecimal precio, FormaPago formaPago, FormaEnvio formaEnvio) {
		throw new IllegalStateException(MessageFormat.format("La compra en estado {0} no puede comprarse", compra.getEstado().getClass().getSimpleName()));
	}

	@Override
	public void verificar() {
		throw new IllegalStateException(MessageFormat.format("La compra en estado {0} no puede verificarse", compra.getEstado().getClass().getSimpleName()));
	}

	@Override
	public void cancelar() {
		throw new IllegalStateException(MessageFormat.format("La compra en estado {0} no puede cancelarse", compra.getEstado().getClass().getSimpleName()));
	}

	@Override
	public void enviar() {
		throw new IllegalStateException(MessageFormat.format("La compra en estado {0} no puede enviarse", compra.getEstado().getClass().getSimpleName()));
	}
}