package es.com.blogspot.elblogdepicodev.test.tapestry.test.pages;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.annotations.Property;

public class HolaMundoTest {

	@Inject
    @Service("nombre")
    @Property
	private String nombre;
}