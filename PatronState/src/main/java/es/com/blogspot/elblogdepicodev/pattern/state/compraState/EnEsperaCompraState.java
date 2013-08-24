package es.com.blogspot.elblogdepicodev.pattern.state.compraState;

import es.com.blogspot.elblogdepicodev.pattern.state.Compra;
import es.com.blogspot.elblogdepicodev.pattern.state.CompraStateFactory.Estado;

public class EnEsperaCompraState extends AbstractCompraState {

	 public EnEsperaCompraState(Compra compra) {
		  super(compra);
	 }

	 @Override
	 public void verificar() {
		  getCompra().setEstado(Estado.VERIFICADA);
	 }

	 @Override
	 public void cancelar() {
		  getCompra().setEstado(Estado.CANCELADA);
	 }
}