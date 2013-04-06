package es.com.blogspot.elblogdepicodev.test.tapestry.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.internal.services.MarkupWriterImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import es.com.blogspot.elblogdepicodev.test.tapestry.services.MensajeService;

public class HolaMundoTest {

	@Test
	public void conNombre() {
		// Dependencias
		MensajeService mensajeService = Mockito.mock(MensajeService.class);
		Mockito.when(mensajeService.getMensaje()).thenReturn("Hola mundo {0}!!!");
		
		// Crear el componente
		HolaMundo componente = new HolaMundo();
		
		// Inyectar las dependencias
		componente.nombre = "picodotdev";
		componente.mensajeService = mensajeService; 

		// Ejecutar el sujecto bajo prueba
		MarkupWriter writer = new MarkupWriterImpl();
		componente.beginRender(writer);

		// Comprobar el resultado
		Assert.assertEquals("Hola mundo picodotdev!!!", writer.toString());
	}
}