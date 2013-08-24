package es.com.blogspot.elblogdepicodev.pattern.state;

import es.com.blogspot.elblogdepicodev.pattern.state.compraState.CanceladaCompraState;
import es.com.blogspot.elblogdepicodev.pattern.state.compraState.CompraState;
import es.com.blogspot.elblogdepicodev.pattern.state.compraState.CreadaCompraState;
import es.com.blogspot.elblogdepicodev.pattern.state.compraState.EnEsperaCompraState;
import es.com.blogspot.elblogdepicodev.pattern.state.compraState.EnviadaCompraState;
import es.com.blogspot.elblogdepicodev.pattern.state.compraState.VerificadaCompraState;

public class CompraStateFactory {

	public enum Estado {
		CREADA, EN_ESPERA, VERIFICADA, CANCELADA, ENVIADA
	}
	
	public static CompraState buildState(Estado estado, Compra compra) {
		CompraState cs = null;
		switch (estado) {
			case CREADA:
				cs = new CreadaCompraState(compra);
				break;
			case EN_ESPERA:
				cs = new EnEsperaCompraState(compra);
				break;
			case VERIFICADA:
				cs =  new VerificadaCompraState(compra);
				break;
			case CANCELADA:
				cs = new CanceladaCompraState(compra);
				break;
			case ENVIADA:
				cs = new EnviadaCompraState(compra);
				break;
			default:
				throw new IllegalArgumentException();
		}
		return cs;
	}
}