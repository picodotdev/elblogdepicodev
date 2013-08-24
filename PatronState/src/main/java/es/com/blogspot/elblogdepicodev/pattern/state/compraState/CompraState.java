package es.com.blogspot.elblogdepicodev.pattern.state.compraState;

import java.math.BigDecimal;

import es.com.blogspot.elblogdepicodev.pattern.state.Compra.FormaEnvio;
import es.com.blogspot.elblogdepicodev.pattern.state.Compra.FormaPago;

public interface CompraState {

	void comprar(BigDecimal precio, FormaPago formaPago, FormaEnvio formaEnvio);
	void verificar();
	void cancelar();
	void enviar();
}