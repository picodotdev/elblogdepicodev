package es.com.blogspot.elblogdepicodev.pattern.state.compraState;

import es.com.blogspot.elblogdepicodev.pattern.state.Compra;
import es.com.blogspot.elblogdepicodev.pattern.state.CompraStateFactory.Estado;

public class VerificadaCompraState extends AbstractCompraState {

	public VerificadaCompraState(Compra compra) {
		super(compra);
	}
	
	@Override
	public void enviar() {
		getCompra().setEstado(Estado.ENVIADA);
	}
}