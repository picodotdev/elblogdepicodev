package es.com.blogspot.elblogdepicodev.test.tapestry.services;

public class MensajeServiceImpl implements MensajeService {

	@Override
	public String getMensaje() {
		return "Hola mundo {0}!!!";
	}
}