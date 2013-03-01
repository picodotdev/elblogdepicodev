package es.com.blogspot.elblogdepicodev.tapestry.resteasy.services;

public class ContadorServiceImpl implements ContadorService {

	private long cuenta;
	
	@Override
	public void incrementar() {
		cuenta++;
	}
	
	@Override
	public long getCuenta() {
		return cuenta;
	}
}
