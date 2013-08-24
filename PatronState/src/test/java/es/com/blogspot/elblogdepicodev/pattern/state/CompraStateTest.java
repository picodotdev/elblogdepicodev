package es.com.blogspot.elblogdepicodev.pattern.state;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import es.com.blogspot.elblogdepicodev.pattern.state.compraState.EnviadaCompraState;

public class CompraStateTest {

	@Test
	public void test1() {
		Compra compra = new Compra();
		compra.getEstado().comprar(new BigDecimal("10.50"), Compra.FormaPago.PAYPAL, Compra.FormaEnvio.UPS);
		compra.getEstado().verificar();
		compra.getEstado().enviar();
		
		Assert.assertTrue(compra.getEstado() instanceof EnviadaCompraState);
	}
}