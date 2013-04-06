package es.com.blogspot.elblogdepicodev.test.tapestry.test.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;

public class HolaMundoTest {

	// Parámetro que recibirá el componete.
	// El valor se obtiene como si se tratase de un servicio mediante
	// la anotación @Inject/@Service, como el tipo es String para distinguir
	// entre varios se le da un nombre.
	@Inject
	@Service("nombre")
	@Property
	private String nombre;
}